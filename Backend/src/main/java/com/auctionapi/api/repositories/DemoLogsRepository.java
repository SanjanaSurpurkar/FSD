package com.auctionapi.api.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.auctionapi.api.entity.DemoLogsEntity;
//import com.auctionapi.api.entity.DemoUsersEntity;

@Repository
public interface DemoLogsRepository extends CrudRepository<DemoLogsEntity, Long> {

    @Query(value = "SELECT * FROM demo_logs", nativeQuery = true)
    Page<DemoLogsEntity> listAllLogsFromDB(Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM demo_logs", nativeQuery = true)
    String countNumberOfLogs();
    
 // ✅ Correct query method to get user by ID
    @Query(value = "SELECT * FROM demo_logs WHERE log_id = :id", nativeQuery = true)
    Optional<DemoLogsEntity> findById(@Param("id") Long id);
    
    @Query(value = "DELETE FROM demo_logs WHERE log_id = :id", nativeQuery = true)
    @Modifying
    @org.springframework.transaction.annotation.Transactional
    void deleteById(@Param("id") Long id);
}
