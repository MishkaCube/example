package com.example.webhub.services;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileService {

    public String saveFile(MultipartFile file, String uploadPath) throws IOException {
        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String idFile = UUID.randomUUID().toString();

        String resultFilename = file.getOriginalFilename() + "." + idFile;
        file.transferTo(new File(uploadPath + "/" + resultFilename));

        return resultFilename;
    }
}
