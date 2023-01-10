package com.semester3.davines.repository;

import com.semester3.davines.repository.entity.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductsRepository extends JpaRepository<OrderProducts, Long> {
}
