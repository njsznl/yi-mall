package com.yi.mall.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yi.mall.search.config.MallElasticSearchConfiguration;
import lombok.Data;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class MallSearchApplicationTests {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    void contextLoads() {
        System.out.println("---------> " + restHighLevelClient);
    }

    @Test
    void testSaveIndex() throws IOException {

        IndexRequest indexRequest = new IndexRequest("system");
        indexRequest.id("1");
        User user = new User();
        user.setName("yi");
        user.setAge(18);
        user.setGender("男");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        indexRequest.source(json, XContentType.JSON);
        IndexResponse index = restHighLevelClient.index(indexRequest, MallElasticSearchConfiguration.COMMON_OPTIONS);

        System.out.println(index);
    }

    @Data
    class User {
        private String name;
        private Integer age;
        private String gender;
    }


}
