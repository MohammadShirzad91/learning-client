package com.example.learningclient.provider;

import com.example.learningclient.config.FeignConfig;
import com.example.learningclient.data.CardEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "learning-server", url = "${learning-server.base-url}", configuration = FeignConfig.class)
public interface LearningServerFeignClient {

    @GetMapping(value = "/get-card-by-pan")
    CardEntity getCardByPan(@RequestParam("pan") String pan);
}
