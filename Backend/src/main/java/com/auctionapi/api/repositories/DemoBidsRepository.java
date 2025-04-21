package com.auctionapi.api.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.auctionapi.api.entity.DemoBidsEntity;

@Repository
public interface DemoBidsRepository extends CrudRepository<DemoBidsEntity, Long> {

    @Query(value = "SELECT * FROM demo_bids", nativeQuery = true)
    Page<DemoBidsEntity> listAllBidsFromDB(Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM demo_bids", nativeQuery = true)
    String countNumberOfBids();

//    ✅ Correct query method to get user by ID
    @Query(value = "SELECT * FROM demo_bids WHERE bid_id = :id", nativeQuery = true)
    Optional<DemoBidsEntity> findById(@Param("id") Long id);
    
    @Query(value = "DELETE FROM demo_bids WHERE bid_id = :id", nativeQuery = true)
    @Modifying
    @org.springframework.transaction.annotation.Transactional
    void deleteById(@Param("id") Long id);
}