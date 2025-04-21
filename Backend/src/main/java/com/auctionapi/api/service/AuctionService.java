package com.auctionapi.api.service;

import com.auctionapi.api.entity.AuctionEntity;
import com.auctionapi.api.repositories.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {

    @Autowired
    private AuctionRepository repository;

    public AuctionEntity createAuction(AuctionEntity auction) {
        // Validation for title
        if (auction.getTitle() == null || auction.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Auction title is required");
        }

        // Ensure start and end times are present
        if (auction.getStartTime() == null || auction.getEndTime() == null) {
            throw new IllegalArgumentException("Start time and end time are required");
        }

        // Get current time
        Date now = new Date();

        // Set status based on startTime and endTime
        if (auction.getEndTime().before(now)) {
            auction.setStatus("CLOSED");
        } else if (auction.getStartTime().before(now)) {
            auction.setStatus("LIVE");
        } else {
            auction.setStatus("UPCOMING");
        }

        // Set current price if not already set
        auction.setCurrentPrice(auction.getStartPrice());

        // Save to DB
        return repository.save(auction);
    }


    public AuctionEntity updateAuction(AuctionEntity auction) {
        return repository.save(auction);
    }

    public List<AuctionEntity> getAllAuctions(int pageNumber, int size) {
        Page<AuctionEntity> auctionsPage = repository.listAllAuctionsFromDB(PageRequest.of(pageNumber, size));
        return auctionsPage.getContent();
    }

    public Optional<AuctionEntity> getAuctionById(Integer id) {
        return repository.findById(id);
    }

    public void deleteAuction(Integer id) {
        repository.deleteById(id);
    }

    public long countAuctions() {
        return Long.parseLong(repository.countNumberOfAuctions());
    }
}