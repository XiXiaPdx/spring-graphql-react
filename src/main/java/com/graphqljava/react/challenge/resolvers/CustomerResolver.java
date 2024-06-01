package com.graphqljava.react.challenge.resolvers;

import com.graphqljava.react.challenge.model.Customer;
import com.graphqljava.react.challenge.repository.CustomerRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
class CustomerResolver {

    private final CustomerRepository customerRepository;

    public CustomerResolver(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @QueryMapping
    public List<Customer> customerByFirstOrLastName(@Argument String name) {
        return customerRepository.findByFirstOrLastName(name);
    }

    @QueryMapping
    public List<Customer> customerByCompany(@Argument String company) {
        return customerRepository.findByCompanyIgnoreCase(company);
    }

}
