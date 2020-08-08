package com.zzp.cloud.business;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class BusinessSystemApplicationTests {
    @Test
    void test1(){
        var map = new HashMap<String, Object>();
        map.put("d","true");
        System.out.println(Boolean.TRUE.equals(map.get("d")));
    }
}
