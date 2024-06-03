package com.graphqljava.react.challenge.resolvers;

import com.graphqljava.react.challenge.model.Customer;
import com.graphqljava.react.challenge.repository.CustomerRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
class CompanyResolver {

    private final CustomerRepository customerRepository;

    public CompanyResolver(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @QueryMapping
    public List<String> allCompanies() {
        return customerRepository.findDistinctCompany();
    }
}
