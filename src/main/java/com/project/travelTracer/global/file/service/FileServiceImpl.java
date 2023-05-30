package com.project.travelTracer.global.file.service;

import com.project.travelTracer.global.file.exception.FileException;
import com.project.travelTracer.global.file.exception.FileExceptionType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    @Value("${file.dir")
    private String fileDir;

    @Override
    public String save(MultipartFile multipartFile)  {
        String filePath = fileDir + UUID.randomUUID();
        try {
            multipartFile.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new FileException(FileExceptionType.FILE_CAN_NOT_DELETE);
        }
        return filePath;
    }

    @Override
    public void delete(String filePath) {
        File file = new File(filePath);

        if(!file.exists()) return;

        if(!file.delete()) throw new FileException(FileExceptionType.FILE_CAN_NOT_DELETE);
    }
}
