package com.ibsys.ibsysbackend.repositories;
//ToDo: Check if the import is correct
import com.ibsys.ibsysbackend.entities.WaitingListStockWaitingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface WaitingliststockWaitlinglistRepository extends JpaRepository<WaitingListStockWaitingList, Long> {

    ArrayList<WaitingListStockWaitingList> findByItem(final int item);

}
