package com.youthcon.start;

import com.youthcon.start.exception.DuplicateSendGiftException;
import com.youthcon.start.exception.ReviewNotFoundException;
import com.youthcon.start.exception.SendGiftInternalException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final GiftApi giftApi;

    public ReviewService(ReviewRepository reviewRepository, GiftApi giftApi) {
        this.reviewRepository = reviewRepository;
        this.giftApi = giftApi;
    }

    @Transactional(readOnly = true)
    public Review getById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("no review: " + id));
    }

    @Transactional
    public Review sendGift(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("no review: " + id));

        if (review.isSent()) {
            throw new DuplicateSendGiftException("id: " + id);
        }

        boolean sendSuccess = giftApi.send(review.getPhoneNumber());

        if (!sendSuccess) {
            throw new SendGiftInternalException("id: " + id);
        }

        review.makeSentTrue();
        return review;
    }

}
