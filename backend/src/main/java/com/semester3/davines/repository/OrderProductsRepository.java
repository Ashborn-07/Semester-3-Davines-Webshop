package com.semester3.davines.repository;

import com.semester3.davines.repository.entity.OrderProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderProductsRepository extends JpaRepository<OrderProductsEntity, Long> {
    Optional<OrderProductsEntity> findByOrderId(Long id);
}
