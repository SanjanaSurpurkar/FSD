package com.auctionapi.api.repositories;

import com.auctionapi.api.entity.AuctionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AuctionRepository extends CrudRepository<AuctionEntity, Integer> {

    @Query(value = "SELECT * FROM hemavathy_auctions", nativeQuery = true)
    Page<AuctionEntity> listAllAuctionsFromDB(Pageable pageable);

    @Query(value = "SELECT count(*) FROM hemavathy_auctions", nativeQuery = true)
    String countNumberOfAuctions();
}