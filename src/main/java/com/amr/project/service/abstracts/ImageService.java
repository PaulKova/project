package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ImageDto;

import java.util.List;

public interface ImageService {
    List<ImageDto> getAllImages();

    ImageDto getImageById(Long id);

    void saveImage(ImageDto imageDto);

    void updateImage(ImageDto imageDto);

    void deleteImage(Long id);
}
