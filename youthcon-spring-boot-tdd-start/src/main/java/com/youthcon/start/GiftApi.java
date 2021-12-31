package com.youthcon.start;

import com.youthcon.start.dto.SendGiftRequestDto;
import com.youthcon.start.dto.SendGiftResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GiftApi {

    private static final String API_URL = "http://youthcon.seok2.dev/apis/v1/send";
    private final RestTemplate restTemplate = new RestTemplate();;

    public boolean send(String phoneNumber) {
        ResponseEntity<SendGiftResponseDto> response = restTemplate.postForEntity(
                API_URL,
                SendGiftRequestDto.of(phoneNumber, 1000),
                SendGiftResponseDto.class
        );

        return response.getStatusCode().is2xxSuccessful();
    }

}
