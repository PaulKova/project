package com.amr.project.service.impl;

import com.amr.project.converter.mappers.ItemMapper;
import com.amr.project.converter.mappers.ReviewMapper;
import com.amr.project.converter.mappers.ShopMapper;
import com.amr.project.converter.mappers.UserMapper;
import com.amr.project.dao.ItemRepository;
import com.amr.project.dao.ReviewRepository;
import com.amr.project.dao.ShopRepository;
import com.amr.project.dao.UserRepository;
import com.amr.project.model.dto.ReviewDto;
import com.amr.project.model.entity.Review;
import com.amr.project.service.abstracts.ReviewService;
import com.amr.project.service.email.MailSender;
import com.amr.project.util.EmailReviewAssistant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.amr.project.converter.CycleAvoidingMappingContext;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final ShopRepository shopRepository;
    private final UserMapper userMapper;
    private final ShopMapper shopMapper;
    private final ItemMapper itemMapper;
    private final ReviewMapper reviewMapper;
    private final MailSender mailSender;
    private final EmailReviewAssistant emailReviewAssistant;

    @Override
    public List<ReviewDto> getAllReview() {
        return reviewMapper.toDtoList(reviewRepository.findAll(), new CycleAvoidingMappingContext()).stream()
                .map(x -> {
                    Review review = reviewRepository.getById(x.getId());
                    x.setUserId(review.getUser().getId());
                    x.setShopId(review.getShop().getId());
                    x.setItemId(review.getItem().getId());
                    return x;
                }).collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(Long id) {
        ReviewDto reviewDto = reviewMapper.toDto(reviewRepository.getById(id), new CycleAvoidingMappingContext());
        Review review = reviewRepository.getById(id);
        reviewDto.setUserId(review.getUser().getId());
        reviewDto.setShopId(review.getShop().getId());
        reviewDto.setItemId(review.getItem().getId());
        return reviewDto;
    }

    @Override
    public void saveReview(ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto, new CycleAvoidingMappingContext());
        mailSender.send(emailReviewAssistant.trackedEmailReviewSave(review));
        reviewRepository.saveAndFlush(review);
    }

    @Override
    public Review saveReviewAsNoModerated(ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto, new CycleAvoidingMappingContext());
        review.setUser(userRepository.getById(reviewDto.getUserId()));
        review.setShop(shopRepository.getById(reviewDto.getUserId()));
        review.setItem(itemRepository.getById(reviewDto.getItemId()));
        review.setModerated(false);
        return reviewRepository.saveAndFlush(review);
    }

    @Override
    public void updateReview(ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto, new CycleAvoidingMappingContext());
        review.setUser(userRepository.getById(reviewDto.getUserId()));
        review.setShop(shopRepository.getById(reviewDto.getUserId()));
        review.setItem(itemRepository.getById(reviewDto.getItemId()));
        reviewRepository.saveAndFlush(review);
    }

    @Override
    public void deleteReview(Long id) {
        mailSender.send(emailReviewAssistant.trackedEmailReviewDelete(reviewRepository.getById(id)));
        reviewRepository.deleteById(id);
    }

    @Override
    public List<ReviewDto> getAllNoModeratedReview() {
        return reviewMapper.toDtoList(reviewRepository.findByIsModeratedIsFalseOrderByDateAsc(), new CycleAvoidingMappingContext()).stream()
                .map(x -> {
                    Review review = reviewRepository.getById(x.getId());
                    x.setUserId(review.getUser().getId());
                    x.setShopId(review.getShop().getId());
                    x.setItemId(review.getItem().getId());
                    return x;
                }).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getAllUserReviewById(Long id) {
        return reviewMapper.toDtoList(
                reviewRepository
                        .findByUser_IdIsAndIsModeratedIsTrueAndIsModerateAcceptIsTrueOrderByDateAsc(id), new CycleAvoidingMappingContext()).stream()
                .map(x -> {
                    Review review = reviewRepository.getById(x.getId());
                    x.setUserId(review.getUser().getId());
                    x.setShopId(review.getShop().getId());
                    x.setItemId(review.getItem().getId());
                    return x;
                }).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getAllShopReviewById(Long id) {
        return reviewMapper.toDtoList(
                reviewRepository
                        .findByShop_IdIsAndIsModeratedIsTrueAndIsModerateAcceptIsTrueOrderByDateAsc(id), new CycleAvoidingMappingContext()).stream()
                .map(x -> {
                    Review review = reviewRepository.getById(x.getId());
                    x.setUserId(review.getUser().getId());
                    x.setShopId(review.getShop().getId());
                    x.setItemId(review.getItem().getId());
                    return x;
                }).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getAllItemReviewById(Long id) {
        return reviewMapper.toDtoList(
                reviewRepository
                        .findByItem_IdIsAndIsModeratedIsTrueAndIsModerateAcceptIsTrueOrderByDateAsc(id), new CycleAvoidingMappingContext()).stream()
                .map(x -> {
                    Review review = reviewRepository.getById(x.getId());
                    x.setUserId(review.getUser().getId());
                    x.setShopId(review.getShop().getId());
                    x.setItemId(review.getItem().getId());
                    return x;
                }).collect(Collectors.toList());
    }

}
