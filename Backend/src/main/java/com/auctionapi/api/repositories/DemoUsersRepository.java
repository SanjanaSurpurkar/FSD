package com.auctionapi.api.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.auctionapi.api.entity.DemoUsersEntity;

@Repository
public interface DemoUsersRepository extends CrudRepository<DemoUsersEntity, Long> {

    @Query(value = "SELECT * FROM demo_users", nativeQuery = true)
    Page<DemoUsersEntity> listAllUsersFromDB(Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM demo_users", nativeQuery = true)
    String countNumberOfUsers();

    // ✅ Correct query method to get user by ID
    @Query(value = "SELECT * FROM demo_users WHERE userid = :id", nativeQuery = true)
    Optional<DemoUsersEntity> findById(@Param("id") Long id);
    
    @Query(value = "DELETE FROM demo_users WHERE userid = :id", nativeQuery = true)
    @Modifying
    @org.springframework.transaction.annotation.Transactional
    void deleteById(@Param("id") Long id);

    @Query(value = "SELECT * FROM demo_users WHERE email = :email", nativeQuery = true)
    DemoUsersEntity findByEmail(@Param("email") String email);

}
