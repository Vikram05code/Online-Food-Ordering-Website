package com.vikram.service;

import com.vikram.Exception.ReviewException;
import com.vikram.model.Review;
import com.vikram.model.User;
import com.vikram.request.ReviewRequest;

import java.util.List;

public interface ReviewService {

    public Review submitReview(ReviewRequest review, User user);
    public void deleteReview(Long reviewId) throws ReviewException;
    public double calculateAverageRating(List<Review> reviews);
}