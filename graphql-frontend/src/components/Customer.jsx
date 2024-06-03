import React from 'react'

const Customer = ({ customer }) => (
  <div>
    <div>
      {customer.firstName}  {customer.lastName} :: {customer.company}
    </div>
  </div>
)

export default Customer