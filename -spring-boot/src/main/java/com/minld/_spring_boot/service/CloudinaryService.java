package com.minld._spring_boot.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.minld._spring_boot.entity.MediaFile;
import com.minld._spring_boot.exception.AppException;
import com.minld._spring_boot.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public MediaFile updateFile(MultipartFile file, String relatedName, String relatedId) throws IOException {
        // Định danh file trên Cloudinary (Ví dụ: lesson_1234_timestamp.mp4)
        String fileName = relatedName + "_" + relatedId + "_" + System.currentTimeMillis();
        try {
            // Upload file lên Cloudinary
            Map uploadResult = cloudinary
                    .uploader()
                    .upload(file.getBytes(), ObjectUtils.asMap("public_id", fileName, "folder", "thecraftld"));

            // Lấy URL cơ bản của file
            String publicId = (String) uploadResult.get("public_id");
            String url = (String) uploadResult.get("url");
            long fileSize = file.getSize();

            // Tạo bản ghi MediaFile
            MediaFile mediaFile = new MediaFile();

            mediaFile.setUrl(url);
            mediaFile.setPublicId(publicId);
            mediaFile.setFileSize(fileSize);
            return mediaFile;

        } catch (AppException e) {
            throw new AppException(ErrorCode.UPLOAD_FAILED);
        }
    }
}
