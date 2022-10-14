import React from "react";
import Navbar from "./components/Navbar";
import Hero from "./components/Hero";
import { Route, Routes, BrowserRouter as Router } from "react-router-dom";
import ProductPage from "./pages/ProductsPage";
import SeriesPage from "./pages/SeriesPage";
import AboutPage from "./pages/AboutPage";
import ContactPage from "./pages/ContactPage";
import SeriesDetails from "./pages/SeriesDetails";

function App() {
  return (
    <div>
      <Router>
        <Navbar />
        <Routes>
          <Route path="/" element={<Hero />} />
          <Route path="/products" element={<ProductPage />}/>
          <Route path="/series" element={<SeriesPage />}/>
          <Route path="/about" element={<AboutPage />}/>
          <Route path="/contact" element={<ContactPage />}/>
          <Route path="/series/:id" element={<SeriesDetails />}/>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
