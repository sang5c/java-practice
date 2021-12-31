package com.youthcon.start;

import com.youthcon.start.exception.ReviewNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    private static final long ID = 1L;
    private static final String CONTENT = "재밌어요";
    private static final String PHONE_NUMBER = "010-1111-2222";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @DisplayName("후기 조회 성공")
    @Test
    void success() throws Exception {
        // 준비
        given(reviewService.getById(ID))
                .willReturn(new Review(ID, CONTENT, PHONE_NUMBER));

        // 실행
        ResultActions perform = mockMvc.perform(get("/reviews/" + ID));

        // 검증
        perform
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(ID))
                .andExpect(jsonPath("content").value(CONTENT))
                .andExpect(jsonPath("phoneNumber").value(PHONE_NUMBER));
    }

    @DisplayName("후기 조회 실패")
    @Test
    void fail() throws Exception {
        // 준비
        given(reviewService.getById(1000L))
                .willThrow(ReviewNotFoundException.class);

        // 실행
        ResultActions perform = mockMvc.perform(get("/reviews/" + 1000));

        // 검증
        perform
                .andExpect(status().isNotFound());
    }

    @DisplayName("선물하기")
    @Test
    void gift() throws Exception {
        long id = 2L;
        given(reviewService.sendGift(id))
                .willReturn(new Review(id, CONTENT, PHONE_NUMBER, true));

        ResultActions perform = mockMvc.perform(put("/reviews/" + 2));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("sent").value(true));
    }

}
