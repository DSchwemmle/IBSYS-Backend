package com.ibsys.ibsysbackend.repositories;
//ToDo: Check if the import is correct
import com.ibsys.ibsysbackend.entities.WaitingliststockWaitinglist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface WaitingliststockWaitlinglistRepository extends JpaRepository<WaitingliststockWaitinglist, Long> {

    ArrayList<WaitingliststockWaitinglist> findByItem(final int item);

}
