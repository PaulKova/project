package com.amr.project.service.abstracts;

import com.amr.project.model.dto.FavoriteDto;

import java.util.List;
import java.util.Optional;

public interface FavoriteService {
    List<FavoriteDto> getAllFavorites();

    FavoriteDto findById(Long id);

    void saveFavorite(FavoriteDto favoriteDto);

    void updateFavorite(FavoriteDto favoriteDto);

    void deleteFavorite(Long id);
}
