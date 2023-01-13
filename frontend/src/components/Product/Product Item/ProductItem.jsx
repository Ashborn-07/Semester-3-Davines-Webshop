import React from "react";
import { NavLink } from "react-router-dom";
import "./productItem.css";

function ProductItem(props) {

    return (
        <div className="product-card">
            <NavLink to={`/products/details/${props.product.id}`}>
                <img className="product-card-image" src={props.product.image} alt="" />
                <h1 className="product-name">{props.product.name}</h1>
            </NavLink>
        </div>
    )
}

export default ProductItem;