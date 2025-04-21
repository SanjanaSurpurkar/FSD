package com.auctionapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.auctionapi.api.entity.DemoBidsEntity;
import com.auctionapi.api.service.DemoBidsService;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DemoBidsController {
    
    @Autowired
    private DemoBidsService demoBidsService;

    @RequestMapping(value = "/createBid", method = RequestMethod.POST)
    public ResponseEntity<?> createBid(@RequestBody DemoBidsEntity bid) {
        return ResponseEntity.ok(demoBidsService.createBid(bid));
    }

    @RequestMapping(value = "/updateBid", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBid(@RequestBody DemoBidsEntity bid) {
        return ResponseEntity.ok(demoBidsService.updateBid(bid));
    }

    @RequestMapping(value = "/listAllBids", method = RequestMethod.GET)
    public ResponseEntity<?> listAllBids(@RequestParam(defaultValue = "0") final Integer pageNumber,
                                          @RequestParam(defaultValue = "100") final Integer size) {
        return ResponseEntity.ok(demoBidsService.getAllBids(pageNumber, size));
    }

    @RequestMapping(value = "/getBidId", method = RequestMethod.GET)
    public ResponseEntity<?> getBidId(@RequestParam Long id) {
        Optional<DemoBidsEntity> bid = demoBidsService.getBidId(id);
        return bid.isPresent() ? ResponseEntity.ok(bid) : ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/deleteBid", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBid(@RequestParam Long id) {
        demoBidsService.deleteBid(id);
        return ResponseEntity.ok("Bid deleted successfully");
    }

    @RequestMapping(value = "/countBids", method = RequestMethod.GET)
    public ResponseEntity<?> countBids() {
        return ResponseEntity.ok(demoBidsService.countBids());
    }
}