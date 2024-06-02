import React from 'react'

const Customer = ({ customer }) => (
  <div>
    <div>
      {customer.firstName} {customer.lastName}
    </div>
  </div>
)

export default Customer