import React from 'react'
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import CustomerList from './CustomerList'
import SortedCustomers from './SortDropdown'

const App = () =>
    <Routes>
      <Route path="/" element={<SortedCustomers />} />
    </Routes>
export default App
