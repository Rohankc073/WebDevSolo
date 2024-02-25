package com.system.TrendThreads.services.impl;

import com.system.TrendThreads.entity.Review;
import com.system.TrendThreads.pojo.ReviewPojo;
import com.system.TrendThreads.repo.ProductRepo;
import com.system.TrendThreads.repo.ReviewRepo;
import com.system.TrendThreads.repo.UserRepo;
import com.system.TrendThreads.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepo reviewRepo;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;

    @Override
    public void addReview(ReviewPojo reviewPojo) {

        Review review = new Review();
        review.setReview(reviewPojo.getReview());
        review.setRate(reviewPojo.getRate());
        review.setProduct(productRepo.findById(reviewPojo.getProduct()).orElseThrow());
        review.setUser(userRepo.findById(reviewPojo.getUser()).orElseThrow());
        review.setDate(Date.valueOf(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now())));

        reviewRepo.save(review);
    }

    @Override
    public List<Review> getAllReviews(Integer productId) {
        return reviewRepo.findAllByProduct(productId).orElseThrow();
    }

    @Override
    public void deleteReview(Integer reviewId) {
        reviewRepo.deleteById(reviewId);
    }
}
