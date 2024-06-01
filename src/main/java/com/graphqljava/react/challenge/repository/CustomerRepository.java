package com.graphqljava.react.challenge.repository;

import com.graphqljava.react.challenge.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("SELECT c FROM Customer c WHERE LOWER(c.firstName) = LOWER(:name) OR LOWER(c.lastName) = LOWER(:name)")
    List<Customer> findByFirstOrLastName(@Param("name") String name);
//    @Query("SELECT c FROM Customer c WHERE LOWER(c.company) = LOWER(:company)")
    List<Customer> findByCompanyIgnoreCase(@Param("company") String company);

}

