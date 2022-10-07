import React, { useEffect, useState } from "react";
import ProductList from "../components/ProductList";
import axios from "axios";

function ProductsPage() {
    const [products, setProducts] = useState([]);
    useEffect(() => {
        axios.get("http://localhost:8080/products")
            .then(res => setProducts(res.data.products))
            .catch(err => console.log(err));
    }, []);
    return (
        <div>
            <div className="flex flex-row bg-[#F4F4F6]">
                <h1 className="text-black text-5xl text-bold font-serif m-11">All Products</h1>
                <img className="w-[1290px] h-[444px] ml-96" src="https://res.cloudinary.com/dssmw7qxi/image/upload/v1664897350/davines_products_page_dokknt.png" alt="" />
            </div>
            <ProductList products={products}/>
        </div>
    )
}


export default ProductsPage;