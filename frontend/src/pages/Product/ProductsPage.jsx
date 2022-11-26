import React, { useEffect, useState } from "react";
import ProductList from "../../components/Product/Product List/ProductList";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import Popup from "../../components/Product/Popup Create Product/Popup";
import "./productsPage.css";

function ProductsPage() {
    const [products, setProducts] = useState([]);
    const [buttonPopup, setButtonPopup] = useState(false);
    const [seriesData, setSeriesData] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/products")
            .then(res => setProducts(res.data.products))
            .catch(err => console.log(err));
    }, []);

    const GetSeries = () => {
        axios.get("http://localhost:8080/series")
            .then(res => setSeriesData(res.data.series));
    }


    return (
        <section>
            <div className="header-product-page">
                <h1 className="title-product-page">All Products</h1>
                <img className="title-image" src="https://res.cloudinary.com/dssmw7qxi/image/upload/v1668441632/davines%20project/pon668awk9kk9ibytp9m.png" alt="" />
            </div>

            <div className="action-menu">
                <div className="add-btn" onClick={() => setButtonPopup(true)}>
                    <div className="add-icon">
                        <FontAwesomeIcon className="i" icon={faPlus} />
                    </div>
                    <span className="add-spanner">Add Product</span>
                </div>
            </div>

            <Popup trigger={buttonPopup} setTrigger={setButtonPopup} series={seriesData} onClick={GetSeries}>

            </Popup>

            <ProductList products={products} />
        </section>
    )
}


export default ProductsPage;