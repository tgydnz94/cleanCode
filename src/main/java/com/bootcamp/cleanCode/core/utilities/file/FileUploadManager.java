package com.bootcamp.cleanCode.core.utilities.file;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadManager implements FileUploadService {
    private String uploadDirectory = "uploads/images/";

    @Override
    public String upload(MultipartFile file) {
        try {
            // MIME tip kontrolü (görsel değilse reddet)
            if (!file.getContentType().startsWith("image/")) {
                throw new RuntimeException("Sadece görsel dosyaları yüklenebilir.");
            }

            // Görsel olup olmadığını kontrol et
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                throw new RuntimeException("Geçersiz görsel.");
            }

            // Benzersiz isim oluştur
            String fileName = UUID.randomUUID() + ".jpg";

            
            File directory = new File(uploadDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Hedef dosya
            File outputFile = new File(uploadDirectory + fileName);

            // Görseli JPEG olarak kaydet
            boolean success = ImageIO.write(bufferedImage, "jpg", outputFile);
            if (!success) {
                throw new RuntimeException("Görsel yazılamadı.");
            }

            return fileName;

        } catch (IOException e) {
            throw new RuntimeException("Dosya yüklenirken hata oluştu: " + e.getMessage());
        }
    }

    
}
