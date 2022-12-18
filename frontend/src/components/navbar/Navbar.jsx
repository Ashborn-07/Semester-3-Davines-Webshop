import React, { useState } from "react";
import { AiOutlineClose, AiOutlineMenu } from "react-icons/ai";
import { NavLink } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBagShopping } from "@fortawesome/free-solid-svg-icons";
import './navbar.css';

function Navbar(props) {
    const [nav, setNav] = useState(true);


    const toggleNav = () => {
        setNav(!nav);
    }

    function logout() {
        localStorage.clear();
        window.location.href = "/login";
    }

    function ConditionalLinks() {
        if (localStorage.getItem('token') !== null) {
            return (
                <>
                    <li><NavLink to="/profile">Profile</NavLink></li>
                    <li><NavLink onClick={logout}>Logout</NavLink></li>
                </>
            );
        }
        return (
            <>
                <li><NavLink to="/login">Login</NavLink></li>
                <li><NavLink to="/register">Register</NavLink></li>
            </>
        );
    }

    return (
        <div className='header'>
            <h1 className='logo'><NavLink to="/">davines</NavLink></h1>
            <div className="links">
                <ul className='nav'>
                    <li><NavLink to="/">Home</NavLink></li>
                    <li><NavLink to="/products">Products</NavLink></li>
                    <li><NavLink to="/series">Series</NavLink></li>
                    <li><NavLink to="/about">About</NavLink></li>
                    <li><NavLink to="/contact">Contact</NavLink></li>
                    {ConditionalLinks()}
                </ul>
                <div className="shopping-cart" onClick={props.setCartVisibility}>
                    <FontAwesomeIcon icon={faBagShopping} fontSize={25} />
                </div>
            </div>
            <div onClick={toggleNav} className='hamburgerBtn'>
                {!nav ? <AiOutlineClose size={20} /> : <AiOutlineMenu size={20} />}
            </div>
            <div className={!nav ? 'opened' : 'closed'}>
                <h1 className="header-side-menu"><NavLink to="/">davines</NavLink></h1>
                <ul className="nav-side-menu">
                    <li><NavLink to="/">Home</NavLink></li>
                    <li><NavLink to="/products">Products</NavLink></li>
                    <li><NavLink to="/series">Series</NavLink></li>
                    <li><NavLink to="/about">About</NavLink></li>
                    <li><NavLink to="/contact">Contact</NavLink></li>
                    {ConditionalLinks()}
                </ul>
                <div className="shoppingCart-phone">
                    <FontAwesomeIcon icon={faBagShopping} fontSize={25} />
                </div>
            </div>
        </div>
    )
}

export default Navbar;