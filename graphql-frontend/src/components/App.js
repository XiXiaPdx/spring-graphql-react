import React from 'react'
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import CustomerList from './CustomerList'

const App = () =>
    <Routes>
      <Route path="/" element={<CustomerList />} />
    </Routes>
export default App
