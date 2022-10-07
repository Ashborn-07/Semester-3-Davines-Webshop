import React from "react";
import ProductItem from "./ProductItem";

function ProductList(props) {

    return (
        <div className="flex flex-col left-0 m-3 justify-center">
            {props.products.map(product => (
                <ProductItem key={product.id} product={product} />
            ))}
        </div>
    )
}

export default ProductList;