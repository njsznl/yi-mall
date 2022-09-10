package com.yi.mall.order;

import com.yi.mall.order.entity.OrderEntity;
import com.yi.mall.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MallOrderApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    void contextLoads() {
        OrderEntity entity = new OrderEntity();
        entity.setReceiverName("xiao");
        entity.setOrderSn("123456789");
        orderService.save(entity);
    }

}
