import React from "react";
import "./productItem.css";

function ProductItem (props) {

    return (
        <div className="product-card">
            <img className="product-card-image" src={props.product.image} alt="" />
            <h1 className="product-name">{props.product.name}</h1>
        </div>
    )   
}

export default ProductItem; 