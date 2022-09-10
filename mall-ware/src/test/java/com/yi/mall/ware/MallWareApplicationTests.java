package com.yi.mall.ware;

import com.yi.mall.ware.entity.WareInfoEntity;
import com.yi.mall.ware.service.WareInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MallWareApplicationTests {

    @Autowired
    private WareInfoService wareInfoService;

    @Test
    void contextLoads() {
        WareInfoEntity entity = new WareInfoEntity();
        entity.setAddress("北京市海淀区某某仓库");
        entity.setName("北京仓库");
        wareInfoService.save(entity);
    }

}
