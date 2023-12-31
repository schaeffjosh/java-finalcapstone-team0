package com.techelevator.controller;

import com.techelevator.dao.ReviewDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Review;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Path;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ReviewController {

    private ReviewDao reviewDao;

    public ReviewController(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "beers/{beerId}/reviews", method = RequestMethod.POST)
    public Review addReview(@Valid @RequestBody Review newReview, @PathVariable int beerId) {
        Review review = null;
        try {
            review = reviewDao.createReview(newReview);
            if (review == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Review submission failed.");
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Review submission failed.");
        }
        return review;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)


    @RequestMapping(path = "reviews/random/{beerId}", method = RequestMethod.GET)
    public Review getRandomReviewByBeerId(@PathVariable int beerId) {
        return reviewDao.getRandomReviewByBeerId(beerId);
    }

    @RequestMapping(path = "beers/{beerId}/reviews", method = RequestMethod.GET)
    public List<Review> getReviews(@PathVariable int beerId) {
        return reviewDao.getReviewsByBeerId(beerId);
    }

    @RequestMapping(path = "reviews/{reviewId}", method = RequestMethod.GET)
    public Review getReviewByReviewId(@PathVariable int reviewId) {
        return reviewDao.getReviewById(reviewId);
    }

}
