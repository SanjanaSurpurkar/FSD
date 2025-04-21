package com.auctionapi.api.controller;

import com.auctionapi.api.entity.AuctionEntity;
import com.auctionapi.api.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @RequestMapping(value = "/createAuction", method = RequestMethod.POST)
    public ResponseEntity<?> createAuction(@RequestBody AuctionEntity auction) {
        try {
            // Ensure that all required fields are provided in the request
            if (auction.getTitle() == null || auction.getTitle().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title is required");
            }

            return ResponseEntity.ok(auctionService.createAuction(auction));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

//    @RestController
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/updateAuction", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAuction(@RequestBody AuctionEntity auction) {
        if (auction.getAuctionId() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Auction ID is required for update");
        }
        return ResponseEntity.ok(auctionService.updateAuction(auction));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/listAllAuctions", method = RequestMethod.GET)
    public ResponseEntity<?> listAllAuctions(@RequestParam(defaultValue = "0") final Integer pageNumber,
                                             @RequestParam(defaultValue = "10") final Integer size) {
        return ResponseEntity.ok(auctionService.getAllAuctions(pageNumber, size));
    }

    @RequestMapping(value = "/getAuctionById", method = RequestMethod.GET)
    public ResponseEntity<?> getAuctionById(@RequestParam int id) {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid auction ID");
        }

        Optional<AuctionEntity> auction = auctionService.getAuctionById(id);
        return auction.isPresent() ? ResponseEntity.ok(auction.get()) : ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/deleteAuction", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAuction(@RequestParam int id) {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid auction ID");
        }

        Optional<AuctionEntity> auction = auctionService.getAuctionById(id);
        if (auction.isPresent()) {
            auctionService.deleteAuction(id);
            return ResponseEntity.ok("Auction deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Auction not found");
        }
    }

    @RequestMapping(value = "/countAuctions", method = RequestMethod.GET)
    public ResponseEntity<?> countAuctions() {
        return ResponseEntity.ok(auctionService.countAuctions());
    }
}