package com.example.demo;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.github.houbb.opencc4j.util.ZhConverterUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    RestTemplate restTemplate;

    @Test
    public void postTest() throws Exception {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        String url = "https://www.lddgo.net/api/Transfer";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //cookie没啥影响
        headers.set("Cookie","Hm_lvt_75a21f203d323a988b1d8ce0eeae4de5=1682061510,1682502700; __gads=ID=e1e874a51af72a11-228c4dbc5adf0058:T=1682061510:RT=1682061510:S=ALNI_MbTk7xlj6xlGb5Ebg7Vj_XQgxC-Xw; __gpi=UID=00000be41b17467d:T=1682061510:RT=1682561787:S=ALNI_MZiGnDUg3_TijSYd3095C-FN9jHZQ; Hm_lpvt_75a21f203d323a988b1d8ce0eeae4de5=1682561787");
        JSONObject body = new JSONObject();
        body.put("data","礼貌");
        body.put("from","s");
        body.put("to","t");
        HttpEntity request = new HttpEntity<>(body, headers);
        ResponseEntity<JSONObject> response = restTemplate.exchange(url, HttpMethod.POST, request, JSONObject.class);
        System.out.println(response.getBody().get("data"));
    }
    @Test
    public  void test1() {
        String original = "礼貌";
        String result = ZhConverterUtil.toTraditional(original);
        System.out.println(result);
    }
}
