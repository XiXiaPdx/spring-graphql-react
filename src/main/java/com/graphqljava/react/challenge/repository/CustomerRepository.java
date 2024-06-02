package com.graphqljava.react.challenge.repository;

import com.graphqljava.react.challenge.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    List<Customer> findByFirstNameIgnoreCaseOrLastNameIgnoreCase(@Param("firstName") String firstname, @Param("lastName") String lastname);

    List<Customer> findByCompanyIgnoreCase(@Param("company") String company);

}

