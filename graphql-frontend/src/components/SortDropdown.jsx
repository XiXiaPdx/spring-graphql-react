import React from 'react'
import { useState, useEffect } from 'react';
import { useSearchParams } from 'react-router-dom';
import CustomerList from './CustomerList'
import { gql, useQuery } from 'urql'

const SORT_QUERY = gql`
    query sortedCustomers($sortBy: SortBy!, $orderBy: OrderBy!){
        sortCustomers(sortBy: $sortBy, orderBy: $orderBy) {
        id
        firstName
        lastName
      }
    }
`;

const SortCustomers = () => {
    const [sortByOrderByParams, setSortByOrderByParams] = useSearchParams();
    const [sortBy, setSortBy] = useState(sortByOrderByParams.get('sortBy') || "COMPANY");
    const [orderBy, setOrderBy] = useState(sortByOrderByParams.get('orderBy') || "ASC");

    useEffect(() => {
        setSortByOrderByParams({sortBy, orderBy})}
        , [sortBy, orderBy]
        )

    const handleChange = (event) => {
        const [newSortBy, newOrderBy] = event.target.value.split("-")
        setSortBy(newSortBy)
        setOrderBy(newOrderBy)
    }

      const [result] = useQuery({ query: SORT_QUERY, variables: {sortBy, orderBy}})
      const { data, fetching, error } = result

      if (fetching) return <div>Fetching</div>
      if (error) return <div>Error</div>

       const customersToRender = data.sortCustomers.slice(0,10)

    return (
       <div>
      <form>
        <select value={`${sortBy}-${orderBy}`} onChange={handleChange}>
          <option value="FIRSTNAME-ASC">Sort by First name, A->Z</option>
          <option value="FIRSTNAME-DSC">Sort by First name, Z->A</option>
          <option value="LASTNAME-ASC">Sort by Last name, A->Z</option>
          <option value="LASTNAME-DSC">Sort by Last name, Z->A</option>
         <option value="COMPANY-ASC">Sort by Company, A->Z</option>
           <option value="COMPANY-DSC">Sort by Company, Z->A</option>
        </select>
      </form>

       <CustomerList customersToRender={customersToRender}/>
      </div>
    )
}

export default SortCustomers