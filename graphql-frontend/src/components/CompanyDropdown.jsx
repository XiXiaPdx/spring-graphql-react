import React from 'react'
import { useState, useEffect } from 'react';
import { useSearchParams } from 'react-router-dom';
import CustomerList from './CustomerList'
import { gql, useQuery } from 'urql'

const ALL_COMPANIES_QUERY = gql`
    {
      allCompanies
    }
`;

function CompanyList({ companyData }) {
    console.log(companyData)
  if (!companyData || !companyData.allCompanies) {
    return <div>No Companies Found</div>;
  }

  return (
      <select>
      {companyData.allCompanies.map((company) => (
        <option value={company}> {company} </option>
      ))}
      </select>
  );
}

const FILTER_QUERY = gql`
    query customer($company: String!) {
        customerByCompany(company: $company) {
        firstName
        lastName
        id
      }
    }
`;

function FilterCustomersByCompanyRequest({company}) {
    const [result] = useQuery({ query: FILTER_QUERY, variables: {company}})
          const { data, fetching, error } = result

          if (fetching) return <div>Fetching</div>
          if (error) return <div>Error</div>

        return data.customerByCompany
}

const CustomersByCompany = () => {
    const [companyParam, setCompanyParam] = useSearchParams();
    const [company, setCompany] = useState(companyParam.get('company') || "");

    useEffect(() => {
        setCompanyParam({companyParam})}
        , [company]
        )

    const handleChange = (event) => {
        const [company] = event.target.value
        setCompany(company)
    }

      const [companiesResult] = useQuery({ query: ALL_COMPANIES_QUERY})
      const { companyData, companyFetching, companyError } = companiesResult


    if (companyFetching) return <div>Fetching</div>
    if (companyError) return <div>Error</div>

    const companiesToRender = companyData?.allCompanies;

    return (
       <div>
      <form>
      </form>

      </div>
    )
}

export default CustomersByCompany