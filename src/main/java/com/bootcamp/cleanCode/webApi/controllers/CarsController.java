package com.bootcamp.cleanCode.webApi.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bootcamp.cleanCode.business.abstracts.CarService;
import com.bootcamp.cleanCode.business.concretes.requests.carRequests.CreateCarRequest;
import com.bootcamp.cleanCode.business.concretes.requests.carRequests.UpdateCarRequest;
import com.bootcamp.cleanCode.business.concretes.responses.carResponses.GetAllCarsResponse;
import com.bootcamp.cleanCode.business.concretes.responses.carResponses.GetByIdCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarsController {
    private CarService carService;

    @GetMapping("/getall")
    public List<GetAllCarsResponse> getAll() {
        return carService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public GetByIdCarResponse getById(@PathVariable int id) {
        return carService.getById(id);
    }

    @PreAuthorize("hasRole('COMPANY')")
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() @Valid() CreateCarRequest createCarRequest,
    @RequestParam("images") List<MultipartFile> images) throws IOException {
        this.carService.add(createCarRequest,images);

    }

    @PreAuthorize("hasRole('COMPANY')")
    @PutMapping("/update/{id}")
    public void updateCar(@PathVariable("id") int id,
    @RequestBody() UpdateCarRequest updateCarRequest,
     @RequestParam("images") List<MultipartFile> images) throws IOException{
        updateCarRequest.setId(id);
        this.carService.update(updateCarRequest,images);
    }

    @PreAuthorize("hasRole('COMPANY')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.carService.deleteById(id);
    }


    @GetMapping("/image/{fileName}")
    public ResponseEntity<byte[]> getCarImage(@PathVariable String fileName) {
        try {
            String uploadDirectory ="uploads/images/";
            File imageFile = new File(uploadDirectory + fileName);
            if(!imageFile.exists()) {
                return ResponseEntity.notFound().build();
            }

            byte[] imageBytes = Files.readAllBytes(imageFile.toPath());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        }

        catch (IOException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    @PreAuthorize("hasRole('COMPANY')")
    @DeleteMapping("/delete-image/{carId}/{imageId}")
    public void deleteCarImage(@PathVariable int carId, @PathVariable int imageId) throws IOException{
    carService.deleteImage(carId, imageId);
    }
    
}

