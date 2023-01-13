package com.semester3.davines.repository;

import com.semester3.davines.repository.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Page<OrderEntity> findAllByEmail(Pageable pageable, String email);

    List<OrderEntity> findAllByEmail(String email);
}
