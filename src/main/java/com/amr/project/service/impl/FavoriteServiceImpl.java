package com.amr.project.service.impl;

import com.amr.project.converter.mappers.FavoriteMapper;
import com.amr.project.dao.FavoriteRepository;
import com.amr.project.model.dto.FavoriteDto;
import com.amr.project.model.entity.Favorite;
import com.amr.project.service.abstracts.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final FavoriteMapper favoriteMapper;

    @Override
    public List<FavoriteDto> getAllFavorites() {
        List<Favorite> favorites = favoriteRepository.findAll();
        return favoriteMapper.toDtoList(favorites);
    }

    @Override
    public FavoriteDto getFavoriteById(Long id) {
        Favorite favorite = favoriteRepository.getById(id);
        return favoriteMapper.toDto(favorite);
    }

    @Override
    public void saveFavorite(FavoriteDto favoriteDto) {
        Favorite favorite = favoriteMapper.toEntity(favoriteDto);
        favoriteRepository.saveAndFlush(favorite);
    }

    @Override
    public void updateFavorite(FavoriteDto favoriteDto) {
        Favorite favorite = favoriteMapper.toEntity(favoriteDto);
        favoriteRepository.saveAndFlush(favorite);
    }

    @Override
    public void deleteFavorite(Long id) {
        favoriteRepository.deleteById(id);
    }
}
