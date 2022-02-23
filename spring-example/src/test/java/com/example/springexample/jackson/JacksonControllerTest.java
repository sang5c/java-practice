package com.example.springexample.jackson;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JacksonControllerTest {

    // @TestConfiguration
    // public class JacksonConfig {
    //
    //     @Bean
    //     public MappingJackson2HttpMessageConverter converter() {
    //         final var converter = new MappingJackson2HttpMessageConverter();
    //         final var objectMapper = new com.example.springexample.jackson.JacksonConfig.CustomObjectMapper();
    //         objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    //         converter.setObjectMapper(objectMapper);
    //
    //         return converter;
    //     }
    //
    //     public static class CustomObjectMapper extends ObjectMapper {
    //
    //         public CustomObjectMapper() {
    //             this.registerModule(new JavaTimeModule());
    //             this.getSerializerProvider()
    //                     .setNullValueSerializer(new com.example.springexample.jackson.JacksonConfig.NullToEmptyStringSerializer());
    //         }
    //     }
    //
    //     public static class NullToEmptyStringSerializer extends JsonSerializer<Object> {
    //
    //         @Override
    //         public void serialize(final Object value, final JsonGenerator gen,
    //                               final SerializerProvider serializers) throws IOException {
    //
    //             gen.writeString("");
    //         }
    //     }
    // }

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    void test() {
        RestAssured.given().log().all()
                .when()
                .get("/j")
                .then().log().all();
    }
}
