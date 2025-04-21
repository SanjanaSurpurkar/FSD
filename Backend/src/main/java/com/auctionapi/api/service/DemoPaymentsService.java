package com.auctionapi.api.service;

import com.auctionapi.api.entity.DemoPaymentsEntity;
import com.auctionapi.api.repositories.DemoPaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DemoPaymentsService {

    @Autowired
    private DemoPaymentsRepository repository;

    public DemoPaymentsEntity createPayment(DemoPaymentsEntity payment) {
        return repository.save(payment);
    }

    public DemoPaymentsEntity updatePayment(DemoPaymentsEntity payment) {
        return repository.save(payment);
    }

    public List<DemoPaymentsEntity> getAllPayments() {
        return repository.listAllPaymentsFromDB();
    }

    public Optional<DemoPaymentsEntity> getPaymentById(Long id) {
        return Optional.ofNullable(repository.findPaymentById(id));
    }

    public void deletePayment(Long id) {
        repository.deletePaymentById(id);
    }

    public long countPayments() {
        return Long.parseLong(repository.countNumberOfPayments());
    }
}
