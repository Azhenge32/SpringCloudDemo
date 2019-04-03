package com.azhen.cloud.order.server.repository;

import com.azhen.cloud.order.server.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
