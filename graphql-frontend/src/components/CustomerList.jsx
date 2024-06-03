import React from 'react'
import Customer from './Customer'
import { gql, useQuery } from 'urql'

const CustomerList = (props) => {
  return (
      <div>
        {props.customersToRender?.length > 0 ? (
          props.customersToRender.map(customer => (
            <Customer key={customer.id} customer={customer} />
          ))
        ) : (
          <p>No customers found</p>
        )}
      </div>
    )
}

export default CustomerList

