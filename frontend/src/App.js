import React, { useEffect, useState } from 'react';
import Navbar from './components/navbar/Navbar';
import Footer from './components/footer/Footer';
import { Route, Routes, BrowserRouter as Router } from 'react-router-dom';
import AppRoutes from './AppRoutes';
import { ToastContainer } from 'react-toastify';
import auth from './service/auth/AuthenticationService';  
import ShoppingCart from "./components/shop/ShoppingCart";
import 'react-toastify/dist/ReactToastify.css';
import './App.css';

function App() {

  useEffect(() => {
    if (localStorage.getItem('token')) {
      if (auth.isTokenExpired(localStorage.getItem('token'))) {
        localStorage.removeItem('token');
        window.location.href = '/login';
      }
    }
  }, []);

  const [cartVisible, setCartVisible] = useState(false);

  return (
    <div className='page-container'>
      <div className='content-wrap'>
        <Router>
          <ShoppingCart visible={cartVisible} setCartVisibility={setCartVisible} />
          {window.location.pathname === '/confirmation' ? '' : <Navbar setCartVisibility={setCartVisible}/>}
          <Routes>
            {AppRoutes.map((route, index) => {
              const { element, ...rest } = route;
              return <Route key={index} {...rest} element={element} />;
            })}
          </Routes>
        </Router>
      </div>
      <ToastContainer
        position="bottom-right"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="light"
      />
        {window.location.pathname === '/confirmation' ? '' : <Footer/>}
    </div>
  );
}

export default App;
