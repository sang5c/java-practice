package com.youthcon.start;

import com.youthcon.start.exception.DuplicateSendGiftException;
import com.youthcon.start.exception.ReviewNotFoundException;
import com.youthcon.start.exception.SendGiftInternalException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ReviewServiceTest {

    private static final long ID = 1L;
    private static final String CONTENT = "재밌어요";
    private static final String PHONE_NUMBER = "010-1111-2222";

    private GiftApi giftApi = mock(GiftApi.class);
    private ReviewRepository reviewRepository = mock(ReviewRepository.class);
    private ReviewService reviewService = new ReviewService(reviewRepository, giftApi);

    @DisplayName("후기 조회 성공")
    @Test
    void success() {
        // 준비
        given(reviewRepository.findById(1L))
                .willReturn(Optional.of(new Review(ID, CONTENT, PHONE_NUMBER)));

        // 실행
        Review review = reviewService.getById(ID);

        // 검증
        assertThat(review.getId()).isEqualTo(ID);
        assertThat(review.getContent()).isEqualTo(CONTENT);
        assertThat(review.getPhoneNumber()).isEqualTo(PHONE_NUMBER);
    }

    @DisplayName("후기 조회 실패")
    @Test
    void fail() {
        // 준비
        given(reviewRepository.findById(ID))
                .willReturn(Optional.empty());

        assertThatThrownBy(() ->
                reviewService.getById(1000L))
                .isInstanceOf(ReviewNotFoundException.class);
    }

    @DisplayName("선물하기")
    @Test
    void gift() {
        // 준비
        long id = 2L;
        given(reviewRepository.findById(id))
                .willReturn(Optional.of(new Review(id, CONTENT, PHONE_NUMBER, false)));
        given(giftApi.send(PHONE_NUMBER))
                .willReturn(true);
        given(reviewRepository.save(any(Review.class)))
                .willReturn(new Review(id, CONTENT, PHONE_NUMBER, true));

        // 실행
        Review review = reviewService.sendGift(id);

        // 검증
        assertThat(review.isSent()).isTrue();
    }

    @DisplayName("선물하기 중복지급시 실패")
    @Test
    void giftDuplicated() {
        // 준비
        given(reviewRepository.findById(ID))
                .willReturn(Optional.of(new Review(ID, CONTENT, PHONE_NUMBER, true)));

        // 실행
        assertThatThrownBy(() -> reviewService.sendGift(ID))
                .isInstanceOf(DuplicateSendGiftException.class);
    }

    @DisplayName("선물하기 외부 요청 실패")
    @Test
    void failExternalApi(){
        // 준비
        given(reviewRepository.findById(ID))
                .willReturn(Optional.of(new Review(ID, CONTENT, PHONE_NUMBER, false)));
        given(giftApi.send(PHONE_NUMBER))
                .willReturn(false);

        assertThatThrownBy(() ->
                // 실행
                reviewService.sendGift(ID))
                // 검증
                .isInstanceOf(SendGiftInternalException.class);
    }

}
