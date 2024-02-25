package com.system.TrendThreads.services;

import com.system.TrendThreads.entity.Review;
import com.system.TrendThreads.pojo.ReviewPojo;

import java.util.List;

public interface ReviewService {
    void addReview(ReviewPojo reviewPojo);
    List<Review> getAllReviews(Integer productId);
    void deleteReview(Integer reviewId);
}
