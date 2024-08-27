package com.javeed.customer.repository;

import com.javeed.customer.entity.*;
import jakarta.transaction.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByMobileNumber(String mobileNumber);


    //Required below annotations since delete is performed on non-PK key!
    @Transactional
    @Modifying
    void deleteByMobileNumber(String mobileNumber);
}
