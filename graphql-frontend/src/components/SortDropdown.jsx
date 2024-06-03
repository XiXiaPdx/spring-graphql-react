import React from 'react'
import { useState } from 'react';
import CustomerList from './CustomerList'
import { gql, useQuery } from 'urql'



const SORT_QUERY = gql`
    query sortedCustomers($sortBy: SortBy!, $orderBy: OrderBy!){
        sortCustomers(sortBy: $sortBy, orderBy: $orderBy) {
        id
        firstName
        lastName
        company
      }
    }
`;

const SortedCustomers = () => {
    const [sortBy, setSortBy] = useState("COMPANY");
    const [orderBy, setOrderBy] = useState("ASC");
    const handleChange = (event) => {
        const [newSortBy, newOrderBy] = event.target.value.split("-")
        setSortBy(newSortBy)
        setOrderBy(newOrderBy)
    }

      const [result] = useQuery({ query: SORT_QUERY, variables: {sortBy, orderBy}})
      const { data, fetching, error } = result

      if (fetching) return <div>Fetching</div>
      if (error) return <div>Error</div>

       const customersToRender = data.sortCustomers

    return (
       <div>
      <form>
        <select value={`${sortBy}-${orderBy}`} onChange={handleChange}>
          <option value="FIRSTNAME-ASC">First name, A->Z</option>
          <option value="FIRSTNAME-DSC">First name, Z->A</option>
          <option value="LASTNAME-ASC">Last name, A->Z</option>
          <option value="LASTNAME-DSC">Last name, Z->A</option>
         <option value="COMPANY-ASC">Company, A->Z</option>
           <option value="COMPANY-DSC">Company, Z->A</option>
        </select>
      </form>

       <CustomerList customersToRender={customersToRender}/>
      </div>
    )
}

export default SortedCustomers