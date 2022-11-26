import React from "react";
import ProductItem from "../Product Item/ProductItem";
import "./productList.css";

function ProductList(props) {

    return (
        <div className="products-list">
            {props.products?.map(product => (
                <ProductItem key={product.id} product={product} />
            ))}
        </div>
    )
}

export default ProductList;