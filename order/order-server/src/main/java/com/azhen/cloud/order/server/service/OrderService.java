package com.azhen.cloud.order.server.service;


import com.azhen.cloud.order.server.dto.OrderDTO;

public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 只能卖家操作
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);
}
