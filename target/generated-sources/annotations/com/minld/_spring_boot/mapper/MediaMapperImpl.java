package com.minld._spring_boot.mapper;

import com.minld._spring_boot.dto.request.MediaUpdateRequest;
import com.minld._spring_boot.dto.response.MediaResponse;
import com.minld._spring_boot.entity.MediaFile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class MediaMapperImpl implements MediaMapper {

    @Override
    public MediaFile toMediaFile(MediaUpdateRequest request) {
        if ( request == null ) {
            return null;
        }

        MediaFile.MediaFileBuilder mediaFile = MediaFile.builder();

        return mediaFile.build();
    }

    @Override
    public MediaResponse toMediaResponse(MediaFile mediaFile) {
        if ( mediaFile == null ) {
            return null;
        }

        MediaResponse.MediaResponseBuilder mediaResponse = MediaResponse.builder();

        return mediaResponse.build();
    }

    @Override
    public void updateMedia(MediaFile mediaFile, MediaUpdateRequest request) {
        if ( request == null ) {
            return;
        }
    }
}
