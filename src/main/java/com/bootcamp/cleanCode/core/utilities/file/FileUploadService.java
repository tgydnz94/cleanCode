package com.bootcamp.cleanCode.core.utilities.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String upload(MultipartFile file);
}
