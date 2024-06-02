import React from 'react'
import Customer from './Customer'
import { gql, useQuery } from 'urql'

const SORT_QUERY = gql`
    {
        sortCustomers(sortBy: FIRSTNAME, orderBy: ASC) {
        id
        firstName
        lastName
        company
      }
    }
`;

const CustomerList = () => {
  const [result] = useQuery({ query: SORT_QUERY })
  const { data, fetching, error } = result

  if (fetching) return <div>Fetching</div>
    if (error) return <div>Error</div>

    const customersToRender = data.sortCustomers

  return (
      <div>
        {customersToRender.map(customer => <Customer key={customer.id} customer={customer} />)}
      </div>
    )
}

export default CustomerList

