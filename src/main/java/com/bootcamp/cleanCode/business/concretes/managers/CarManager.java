package com.bootcamp.cleanCode.business.concretes.managers;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bootcamp.cleanCode.business.abstracts.CarService;
import com.bootcamp.cleanCode.business.concretes.requests.carRequests.CreateCarRequest;
import com.bootcamp.cleanCode.business.concretes.requests.carRequests.UpdateCarRequest;
import com.bootcamp.cleanCode.business.concretes.responses.carResponses.GetAllCarsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.carResponses.GetByIdCarResponse;
import com.bootcamp.cleanCode.business.concretes.rules.CarBusinessRules;
import com.bootcamp.cleanCode.core.utilities.file.FileUploadService;
import com.bootcamp.cleanCode.core.utilities.mappers.ModelMapperService;
import com.bootcamp.cleanCode.dataAccess.abstracts.CarImageRepository;
import com.bootcamp.cleanCode.dataAccess.abstracts.CarRepository;
import com.bootcamp.cleanCode.entities.Car;
import com.bootcamp.cleanCode.entities.CarImage;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private CarRepository carRepository;
    private CarImageRepository carImageRepository;
    private ModelMapperService modelMapperService;
    private FileUploadService fileUploadService;
    private CarBusinessRules businessRules;

    @Override
    public List<GetAllCarsResponse> getAll() {
        List<Car> cars = carRepository.findAll();
        List<GetAllCarsResponse> carsResponse = cars.stream()
				.map(car-> this.modelMapperService.forResponse()
				.map(car, GetAllCarsResponse.class))
				.collect(Collectors.toList());
        return carsResponse;
    }

    @Override
    public void add(CreateCarRequest createCarRequest, List<MultipartFile> images) throws IOException {
       businessRules.checkIfPlateExists(createCarRequest.getPlate());
       businessRules.checkIfCompanyCarLimitExceeded(createCarRequest.getCompanyId());

       if (images.size() > 4) {
        throw new RuntimeException("Bir araç için en fazla 4 resim eklenebilir.");
        }

        Car car = this.modelMapperService.forRequest()
				.map(createCarRequest, Car.class);
        this.carRepository.save(car);

        for (MultipartFile image : images) {
            String imagePath = fileUploadService.upload(image); // Resmi yüklüyoruz
            CarImage carImage = new CarImage();
            carImage.setCar(car); 
            carImage.setImageUrl(imagePath);  // Resmin yolunu kaydediyoruz
            carImage.setTimeStamp(LocalDateTime.now());

            carImageRepository.save(carImage); // Resim kaydını veritabanına ekliyoruz
        }
    }

    @Override
    public GetByIdCarResponse getById(int id) {
      businessRules.checkIfCarIdExists(id);

        Car car = this.carRepository.findById(id).orElseThrow();
        GetByIdCarResponse response = this.modelMapperService.forResponse()
				.map(car, GetByIdCarResponse.class);
        return response;
    }

    @Override
    public void update(UpdateCarRequest updateCarRequest, List<MultipartFile> images) throws IOException{
      businessRules.checkIfCarIdExists(updateCarRequest.getId());

        Car car = this.modelMapperService.forRequest()
				.map(updateCarRequest, Car.class);
         this.carRepository.save(car);

         if (images.size() > 4) {
        throw new RuntimeException("Bir araç için en fazla 4 resim eklenebilir.");
    }

      
        // Yeni resimleri ekliyoruz
        for (MultipartFile image : images) {
            String imagePath = fileUploadService.upload(image); // Yeni resmi yüklüyoruz
            CarImage newCarImage = new CarImage();
            newCarImage.setCar(car); 
            newCarImage.setImageUrl(imagePath);  // Resmin yolunu kaydediyoruz
            newCarImage.setTimeStamp(LocalDateTime.now());

            carImageRepository.save(newCarImage); // Yeni resim kaydını veritabanına ekliyoruz
        }
    }

    @Override
    public void deleteById(int id) {
        this.carRepository.deleteById(id);
    }


    @Override
public void deleteImage(int carId, int imageId) throws IOException {
    // Araç ve resim kontrolü
    Car car = carRepository.findById(carId)
            .orElseThrow(() -> new RuntimeException("Araç bulunamadı"));
    
    CarImage carImage = car.getCarImages().stream()
            .filter(image -> image.getId() == imageId)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Resim bulunamadı"));

    String uploadDirectory = System.getProperty("user.dir") + "/uploads/images/";
    // Resim dosyasını silme
    File file = new File(uploadDirectory + carImage.getImageUrl());
    if (file.exists()) {
        boolean deleted = file.delete();
        if (!deleted) {
            throw new RuntimeException("Resim dosyası silinemedi.");
        }
    }

    // Veritabanından resmi silme
    car.getCarImages().remove(carImage);
    carRepository.save(car);
}
    
}

