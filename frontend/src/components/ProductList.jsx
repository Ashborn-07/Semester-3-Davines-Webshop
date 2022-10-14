import React from "react";
import ProductItem from "./ProductItem";

function ProductList(props) {

    return (
        <div className=" grid grid-cols-4 gap-1 left-0 m-3 justify-center bg-[#B8ABA0]">
            {props.products?.map(product => (
                <ProductItem key={product.id} product={product} />
            ))}
        </div>
    )
}

export default ProductList;