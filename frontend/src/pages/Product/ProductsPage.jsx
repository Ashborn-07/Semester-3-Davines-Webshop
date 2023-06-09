import React, { useEffect, useState } from "react";
import ProductList from "../../components/Product/Product List/ProductList";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import Popup from "../../components/Product/Popup Create Product/PopupProduct";
import auth from "../../service/auth/AuthenticationService";
import "./productsPage.css";

function ProductsPage() {
    const [products, setProducts] = useState([]);
    const [updated, setUpdated] = useState(true);
    const [buttonPopup, setButtonPopup] = useState(false);

    useEffect(() => {
        if (updated) {
            axios.get("http://localhost:8080/products")
                .then(res => setProducts(res.data.products))
                .catch(err => console.log(err));
            setUpdated(false);
        }
    }, [updated]);

    return (
        <section>
            <div className="header-product-page">
                <h1 className="title-product-page">All Products</h1>
                <img className="title-image" src="https://res.cloudinary.com/dssmw7qxi/image/upload/v1668441632/davines%20project/pon668awk9kk9ibytp9m.png" alt="" />
            </div>

            <div className="action-menu">
                {auth.getRoles().includes("ADMIN") ?

                    <div className="add-btn" onClick={() => setButtonPopup(true)}>
                        <div className="add-icon">
                            <FontAwesomeIcon className="i" icon={faPlus}/>
                        </div>
                        <span className="add-spanner">Add Product</span>
                    </div>
                    : null}
            </div>

            <Popup setUpdatedStatus={setUpdated} trigger={buttonPopup} setTrigger={setButtonPopup}>

            </Popup>

            <ProductList products={products} />
        </section>
    )
}


export default ProductsPage;