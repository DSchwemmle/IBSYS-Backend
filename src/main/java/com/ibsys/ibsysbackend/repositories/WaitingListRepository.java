package com.ibsys.ibsysbackend.repositories;

import com.ibsys.ibsysbackend.entities.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaitingListRepository extends JpaRepository<WaitingList, Integer> {

    List<WaitingList> findWaitingListsByPeriod(final Integer period);
}
