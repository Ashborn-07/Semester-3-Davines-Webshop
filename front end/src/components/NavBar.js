/* This example requires Tailwind CSS v2.0+ */
import React from 'react'
import {NavLink} from "react-bootstrap";

function NavBar() {
    return (
        <div className="nav-bar">
            <NavLink to="/products" exact activeClassName="active">
                Products
            </NavLink>
            <NavLink to="/series" activeClassName="active">
                Series
            </NavLink>
        </div>
    )
}

export default NavBar;