package com.amr.project.service.impl;

import com.amr.project.converter.mappers.ImageMapper;
import com.amr.project.dao.ImageRepository;
import com.amr.project.model.dto.ImageDto;
import com.amr.project.model.entity.Image;
import com.amr.project.service.abstracts.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;


    @Override
    public List<ImageDto> getAllImages() {
        List<Image> images = imageRepository.findAll();
        return imageMapper.toDtoList(images);
    }

    @Override
    public ImageDto getImageById(Long id) {
        Image image = imageRepository.getById(id);
        return imageMapper.toDto(image);
    }

    @Override
    public void saveImage(ImageDto imageDto) {
        Image image = imageMapper.toEntity(imageDto);
        imageRepository.saveAndFlush(image);
    }

    @Override
    public void updateImage(ImageDto imageDto) {
        Image image = imageMapper.toEntity(imageDto);
        imageRepository.saveAndFlush(image);
    }

    @Override
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }
}
