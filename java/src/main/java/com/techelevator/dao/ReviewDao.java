package com.techelevator.dao;

import com.techelevator.model.Review;

import java.util.List;

public interface ReviewDao {

    List<Review> getReviews();

    List<Review> getReviewsByBeerId(int beerId);

}
