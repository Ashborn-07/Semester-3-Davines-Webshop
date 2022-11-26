import React, { useState } from "react";
import { AiOutlineClose, AiOutlineMenu } from "react-icons/ai";
import { NavLink, useNavigate } from "react-router-dom";
import './navbar.css';

const Navbar = () => {
    const [nav, setNav] = useState(true);

    const navigate = useNavigate();

    const toggleNav = () => {
        setNav(!nav);
    }

    function logout() {
        localStorage.clear();
        navigate("/");
    }

    return (
        <div className='header'>
            <h1 className='logo'><NavLink to="/">davines</NavLink></h1>
            <ul className='nav'>
                <li><NavLink to="/">Home</NavLink></li>
                <li><NavLink to="/products">Products</NavLink></li>
                <li><NavLink to="/series">Series</NavLink></li>
                <li><NavLink to="/about">About</NavLink></li>
                <li><NavLink to="/contact">Contact</NavLink></li>
                {localStorage.getItem('token') !== null
                    && <li><NavLink to="/profile">Profile</NavLink></li>
                }
                {localStorage.getItem('token') !== null ?
                    <li><NavLink onClick={logout}>Logout</NavLink></li> : <li><NavLink to="/login">Login</NavLink></li>
                }
            </ul>
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
                    {localStorage.getItem('token') !== null
                        && <li><NavLink to="/profile">Profile</NavLink></li>
                    }
                    {localStorage.getItem('token') !== null ?
                        <li><NavLink onClick={logout}>Logout</NavLink></li> : <li><NavLink to="/login">Login</NavLink></li>
                    }
                </ul>
            </div>
        </div>
    )
}

export default Navbar;