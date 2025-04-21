package com.auctionapi.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auctionapi.api.entity.DemoPaymentsEntity;
import com.auctionapi.api.service.DemoPaymentsService;

@RestController
@CrossOrigin
public class DemoPaymentsController {

    @Autowired
    private DemoPaymentsService demoPaymentsService;

    @RequestMapping(value = "/createPayment", method = RequestMethod.POST)
    public ResponseEntity<?> createPayment(@RequestBody DemoPaymentsEntity payment) {
        return ResponseEntity.ok(demoPaymentsService.createPayment(payment));
    }

    @RequestMapping(value = "/updatePayment", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePayment(@RequestBody DemoPaymentsEntity payment) {
        return ResponseEntity.ok(demoPaymentsService.updatePayment(payment));
    }

    @RequestMapping(value = "/listAllPayments", method = RequestMethod.GET)
    public ResponseEntity<?> listAllPayments() {
        return ResponseEntity.ok(demoPaymentsService.getAllPayments());
    }

    @RequestMapping(value = "/getPaymentById", method = RequestMethod.GET)
    public ResponseEntity<?> getPaymentById(@RequestParam Long id) {
        Optional<DemoPaymentsEntity> payment = demoPaymentsService.getPaymentById(id);
        return payment.isPresent() ? ResponseEntity.ok(payment) : ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/deletePayment", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePayment(@RequestParam Long id) {
        demoPaymentsService.deletePayment(id);
        return ResponseEntity.ok("Payment deleted successfully");
    }

    @RequestMapping(value = "/countPayments", method = RequestMethod.GET)
    public ResponseEntity<?> countPayments() {
        return ResponseEntity.ok(demoPaymentsService.countPayments());
    }
}
