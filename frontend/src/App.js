import './App.css';
import React from 'react';
import Products from './pages/Products';
import Series from './pages/Series';
import NavBar from './components/NavBar'
import { Route, Routes, BrowserRouter as Router } from "react-router-dom";

function App() {
  return (
    <div className="App">
      <Router>
        <NavBar />
        <Routes>
            <Route path="/products" element={<Products />} />
            <Route path="/series" element={<Series />} />
        </Routes>
        </Router>
    </div>
  );
}

export default App;
