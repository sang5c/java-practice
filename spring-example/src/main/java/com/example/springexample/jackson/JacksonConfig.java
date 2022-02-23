package com.example.springexample.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;

@Configuration
public class JacksonConfig {

    @Bean
    public MappingJackson2HttpMessageConverter converter() {
        final var converter = new MappingJackson2HttpMessageConverter();
        final var objectMapper = new CustomObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        converter.setObjectMapper(objectMapper);

        return converter;
    }

    public static class CustomObjectMapper extends ObjectMapper {

        public CustomObjectMapper() {
            this.registerModule(new JavaTimeModule());
            this.getSerializerProvider()
                    .setNullValueSerializer(new NullToEmptyStringSerializer());
        }
    }

    public static class NullToEmptyStringSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(final Object value, final JsonGenerator gen,
                              final SerializerProvider serializers) throws IOException {

            gen.writeString("");
        }
    }
}
