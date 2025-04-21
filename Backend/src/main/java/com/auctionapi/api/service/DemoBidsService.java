package com.auctionapi.api.service;

import com.auctionapi.api.entity.DemoBidsEntity;
import com.auctionapi.api.repositories.DemoBidsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DemoBidsService {

    @Autowired
    private DemoBidsRepository repository;

    public DemoBidsEntity createBid(DemoBidsEntity bid) {
        return repository.save(bid);
    }

    public DemoBidsEntity updateBid(DemoBidsEntity bid) {
        return repository.save(bid);
    }

    public List<DemoBidsEntity> getAllBids(int pageNumber, int size) {
        Page<DemoBidsEntity> bidsPage = repository.listAllBidsFromDB(PageRequest.of(pageNumber, size));
        return bidsPage.getContent();
    }

    public Optional<DemoBidsEntity> getBidId(Long id) {
        return repository.findById(id);
    }

    public void deleteBid(Long id) {
        repository.deleteById(id);
    }

    public long countBids() {
        return Long.parseLong(repository.countNumberOfBids());
    }
}