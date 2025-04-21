package com.auctionapi.api.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.auctionapi.api.entity.DemoAdminEntity;

@Repository
public interface DemoAdminsRepository extends CrudRepository<DemoAdminEntity, Long> {

    @Query(value = "SELECT * FROM demo_admins", nativeQuery = true)
    Page<DemoAdminEntity> listAllAdminsFromDB(Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM demo_admins", nativeQuery = true)
    String countNumberOfAdmins();
    
    @Query(value = "DELETE FROM demo_admins WHERE admin_id = :id", nativeQuery = true)
    @Modifying
    @org.springframework.transaction.annotation.Transactional
    void deleteById(Long id);
    
    @Query(value = "SELECT * FROM demo_admins WHERE email = :email", nativeQuery = true)
    DemoAdminEntity findByEmail(@Param("email") String email);

}
