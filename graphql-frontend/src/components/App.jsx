import React from 'react'
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import CustomerList from './CustomerList'
import SortedCustomers from './SortDropdown'

function CombinedComponent() {
  return (
    <>
      <SortedCustomers />
    </>
  );
}

const App = () =>
    <Routes>
      <Route path="/" element={<CombinedComponent />} />
    </Routes>
export default App
