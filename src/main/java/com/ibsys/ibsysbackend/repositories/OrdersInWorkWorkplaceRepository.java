package com.ibsys.ibsysbackend.repositories;

import com.ibsys.ibsysbackend.entities.OrdersInWorkWorkplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersInWorkWorkplaceRepository extends JpaRepository<OrdersInWorkWorkplace, Integer> {

    Optional<OrdersInWorkWorkplace> findByItem(final int item);

}
