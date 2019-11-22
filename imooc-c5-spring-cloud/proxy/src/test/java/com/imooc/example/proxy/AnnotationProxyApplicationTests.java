package com.imooc.example.proxy;

import com.imooc.example.proxy.service.BizService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnotationProxyApplicationTests {

    @Autowired
    private BizService bizService;

    @Test
    public void contextLoads() {
        String str = bizService.getName("pepsi");
        System.out.println("success："+str);
    }

}
