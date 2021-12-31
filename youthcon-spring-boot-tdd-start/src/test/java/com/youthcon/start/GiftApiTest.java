package com.youthcon.start;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GiftApiTest {

    GiftApi giftApi = new GiftApi();

    @DisplayName("선물하기")
    @Test
    void gift() {
        boolean result = giftApi.send("010-1111-2222");

        assertThat(result).isTrue();
    }
}
