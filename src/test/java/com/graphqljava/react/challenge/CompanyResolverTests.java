package com.graphqljava.react.challenge;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.WebGraphQlTester;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CompanyResolverTests {

    @Autowired
    private WebGraphQlTester graphQlTester;

    @Test
    void testAllDistinctCompanies() {
        String query = """
                query {
                  allCompanies
                }
                """;

        List<String> companyList = graphQlTester.document(query)
                .execute()
                .path("allCompanies")
                .entityList(String.class)
                .get();

        assertEquals(11, companyList.size());
        assertTrue(companyList.stream()
                .anyMatch(c ->c.equals("Acme Corp"))
        );
        assertTrue(companyList.stream()
                .anyMatch(c ->c.equals("Buy n Large"))
        );
    }
}
