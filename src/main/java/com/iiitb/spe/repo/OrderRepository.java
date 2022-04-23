package com.iiitb.spe.repo;

import com.iiitb.spe.model.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    List<OrderEntity> findByUserId(long id);
}
