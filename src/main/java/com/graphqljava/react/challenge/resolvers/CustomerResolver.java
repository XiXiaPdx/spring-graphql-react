package com.graphqljava.react.challenge.resolvers;

import com.graphqljava.react.challenge.enums.OrderBy;
import com.graphqljava.react.challenge.enums.SortBy;
import com.graphqljava.react.challenge.model.Customer;
import com.graphqljava.react.challenge.repository.CustomerRepository;
import org.springframework.data.domain.Sort;
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
        return customerRepository.findByFirstNameIgnoreCaseOrLastNameIgnoreCase(name, name);
    }

    @QueryMapping
    public List<Customer> customerByCompany(@Argument String company) {
        return customerRepository.findByCompanyIgnoreCase(company);
    }

    @QueryMapping
    public List<Customer> sortCustomers(@Argument OrderBy orderBy, @Argument SortBy sortBy) {
        Sort.Direction direction = (orderBy == OrderBy.ASC) ? Sort.Direction.ASC : Sort.Direction.DESC;
        return customerRepository.findAll(Sort.by(direction, sortBy.getFieldName()));
    }


}
