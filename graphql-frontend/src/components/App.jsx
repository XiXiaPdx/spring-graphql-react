import React from 'react'
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import CustomerList from './CustomerList'
import SortCustomers from './SortDropdown'

function CombinedComponent() {
  return (
    <>
      <SortCustomers />
    </>
  );
}

const App = () =>
    <Routes>
      <Route path="/" element={<CombinedComponent />} />
    </Routes>
export default App
