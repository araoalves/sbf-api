package com.desafio.sbf.services;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.desafio.sbf.service.consumer.Consumer;

import lombok.Data;
import lombok.ToString;

@SpringBootTest
public class ConsumerTest {

    @Autowired
    private Consumer consumer;

    @Test
    public void deveConsumirApiExterna() throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("header1","value1");
        headers.put("header2", "value2");

        TestJson o = (TestJson) consumer.get("http://echo.jsontest.com/key/value/one/two", TestJson.class, MediaType.APPLICATION_JSON, headers);
        Assertions.assertNotNull(o);
    }	
}

@Data
@ToString
class TestJson{
    private String one;
    private String key;
}

