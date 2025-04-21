package com.auctionapi.api.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.auctionapi.api.entity.DemoPaymentsEntity;
import java.util.List;

@Repository
public interface DemoPaymentsRepository extends CrudRepository<DemoPaymentsEntity, Long> {

    @Query(value = "SELECT * FROM demo_payments", nativeQuery = true)
    List<DemoPaymentsEntity> listAllPaymentsFromDB();

    @Query(value = "SELECT COUNT(*) FROM demo_payments", nativeQuery = true)
    String countNumberOfPayments();

    @Query(value = "SELECT * FROM demo_payments WHERE payment_id = ?1", nativeQuery = true)
    DemoPaymentsEntity findPaymentById(Long id);

    @Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query(value = "DELETE FROM demo_payments WHERE payment_id = ?1", nativeQuery = true)
    void deletePaymentById(Long id);

}
