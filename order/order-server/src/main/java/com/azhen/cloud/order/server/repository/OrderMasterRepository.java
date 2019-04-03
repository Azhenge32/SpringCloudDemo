package com.azhen.cloud.order.server.repository;


import com.azhen.cloud.order.server.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
