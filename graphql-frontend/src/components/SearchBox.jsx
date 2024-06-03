import React from 'react'
import { useState, useEffect, useRef } from 'react';
import { useSearchParams } from 'react-router-dom';
import CustomerList from './CustomerList'
import { gql, useQuery } from 'urql'
import debounce from 'lodash/debounce';

const SEARCH_QUERY = gql`
    query searchCustomers($name: String!){
        customerByFirstOrLastName(name: $name)  {
        id
        firstName
        lastName
      }
    }
`;

const SearchCustomers = () => {
    const [searchParams, setSearchParams] = useSearchParams();
    const [name, setName] = useState(searchParams.get('name') || "");

    useEffect(() => {
        setSearchParams({name})}
        , [name]
        )

    const debouncedSearch = debounce((name) => {
        //useQuery here and the res
        }, 500)
    const handleChange = (event) => {
         setName(event.target.value)
         debouncedSearch(event.target.value)
        }

      const [result] = useQuery({ query: SEARCH_QUERY, variables: {name}})
      const { data, fetching, error } = result

      if (fetching) return <div>Fetching</div>
      if (error) return <div>Error</div>

       const customersToRender = data.customerByFirstOrLastName

    return (
       <div>
        <input type="text" value={name} onChange={handleChange} />
       <CustomerList customersToRender={customersToRender}/>
      </div>
    )
}

export default SearchCustomers