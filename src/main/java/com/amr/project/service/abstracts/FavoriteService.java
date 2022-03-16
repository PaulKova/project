package com.amr.project.service.abstracts;

import com.amr.project.model.dto.FavoriteDto;

import java.util.List;

public interface FavoriteService {
    List<FavoriteDto> getAllFavorites();

    FavoriteDto getFavoriteById(Long id);

    void saveFavorite(FavoriteDto favoriteDto);

    void updateFavorite(FavoriteDto favoriteDto);

    void deleteFavorite(Long id);
}
