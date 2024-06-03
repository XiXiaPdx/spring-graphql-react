package com.graphqljava.react.challenge;

import com.graphqljava.react.challenge.model.Customer;
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

        List<Customer> customerList = graphQlTester.document(query)
                .execute()
                .path("customerByFirstOrLastName")
                .entityList(Customer.class)
                .get();

        assertEquals(4, customerList.size());

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

        List<Customer> customerList = graphQlTester.document(query)
                .execute()
                .path("customerByCompany")
                .entityList(Customer.class)
                .get();

        assertEquals(8, customerList.size());

        assertEquals(8, customerList.stream()
                .filter(c -> c.getCompany().equals("Acme Corp")).count());

    }

    @Test
    void testSortCustomerByFirstNameASC() {
        String query = """
                    query customer {
                        sortCustomers(sortBy: FIRSTNAME, orderBy: ASC) {
                        firstName
                      }
                    }
                """;

        List<Customer> customerList = graphQlTester.document(query)
                .execute()
                .path("sortCustomers")
                .entityList(Customer.class)
                .get();

        assertEquals(20, customerList.size());
        assertEquals("Amelia", customerList.get(0).getFirstName());
        assertEquals("Ava", customerList.get(1).getFirstName());
        assertEquals("William", customerList.get(19).getFirstName());
    }

    @Test
    void testSortCustomerByLastNameDSC() {
        String query = """
                    query customer {
                        sortCustomers(sortBy: LASTNAME, orderBy: DSC) {
                        lastName
                      }
                    }
                """;

        List<Customer> customerList = graphQlTester.document(query)
                .execute()
                .path("sortCustomers")
                .entityList(Customer.class)
                .get();

        assertEquals(20, customerList.size());
        assertEquals("White", customerList.get(0).getLastName());
        assertEquals("Thomas", customerList.get(1).getLastName());
        assertEquals("Clark", customerList.get(19).getLastName());
    }

    @Test
    void testSortCustomerByCompanyAsc() {
        String query = """
                    query customer {
                        sortCustomers(sortBy: COMPANY, orderBy: ASC) {
                        company
                      }
                    }
                """;

        List<Customer> customerList = graphQlTester.document(query)
                .execute()
                .path("sortCustomers")
                .entityList(Customer.class)
                .get();

        assertEquals(20, customerList.size());
        assertEquals("Acme Corp", customerList.get(0).getCompany());
        assertEquals("Acme Corp", customerList.get(1).getCompany());
        assertEquals("Weyland-Yutani Corp", customerList.get(19).getCompany());
    }
}
