package com.graphqljava.react.challenge;

import com.graphqljava.react.challenge.model.Customer;
import com.graphqljava.react.challenge.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.WebGraphQlTester;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerResolverTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WebGraphQlTester graphQlTester;

    @Test
    void testCustomerByFirstOrLastName_ExactMatch_CaseInsensitive() {
        String query = """
                query {
                  customerByFirstOrLastName(name: "john") {
                    id
                    firstName
                    lastName
                    company
                  }
                }
                """;

        // Execute the GraphQL query
        List<Customer> customerList = this.graphQlTester.document(query)
                .execute()
                .path("customerByFirstOrLastName")
                .entityList(Customer.class)
                .get();

        assertEquals(4, customerList.size());

        // Check first names (case-insensitive)
        assertTrue(customerList.stream()
                .anyMatch(c -> c.getFirstName().equalsIgnoreCase("John") &&
                c.getLastName().equalsIgnoreCase("Smith"))
                );
        assertTrue(customerList.stream()
                .anyMatch(c -> c.getFirstName().equalsIgnoreCase("John") &&
                c.getLastName().equalsIgnoreCase("Doe"))
                );

        assertTrue(customerList.stream()
                .anyMatch(c -> c.getFirstName().equalsIgnoreCase("John") &&
                        c.getLastName().equalsIgnoreCase("Johnson"))
        );

        assertTrue(customerList.stream()
                .anyMatch(c -> c.getFirstName().equalsIgnoreCase("Smith") &&
                        c.getLastName().equalsIgnoreCase("John"))
        );

    }

    @Test
    void testCustomerByCompany_ExactMatch_CaseInsensitive() {
        String query = """
                query customer {
                 customerByCompany(company: "acme Corp") {
                  company
                 }
                }
                """;

        // Execute the GraphQL query
        List<Customer> customerList = this.graphQlTester.document(query)
                .execute()
                .path("customerByCompany")
                .entityList(Customer.class)
                .get();

        assertEquals(8, customerList.size());

        assertEquals(8, customerList.stream()
                .filter(c -> c.getCompany().equals("Acme Corp")).count());

    }
}
