import React, { useEffect, useState } from "react";
import auth from "../../../service/auth/AuthenticationService";
import axios from "axios";
import cart from "../../../service/function/cartFunctions";
import "./productDetails.css";
import { useNavigate } from "react-router";

function ProductsDetails() {

    const [product, setProduct] = useState([]);

    const navigate = useNavigate();

    const handleDelete = event => {
        axios.delete("http://localhost:8080/products/" + event.target.id, {
            headers: {
                Authorization: "Bearer " + localStorage.getItem("token")
            }
        })
            .then(res => console.log(res))
            .catch(err => console.log(err));

        navigate("/products");
    }

    useEffect(() => {
        axios.get("http://localhost:8080" + window.location.pathname)
            .then(res => setProduct(res.data.product))
            .catch(err => console.log(err));
    }, []);

    console.log(product);

    function addProduct() {
        let productToAdd = { id: product.id, name: product.name, quantity: 1, price: product.price, image: product.image, availableQuantity: product.quantity };
        cart.addProduct(productToAdd);
    }

    return (
        <div class="main-wrapper-product-details">
            <div class="container-product-details">
                <div class="product-details-div">
                    <div class="product-details-div-left">
                        <div class="product-img-container">
                            <img className="product-details-img"
                                src={product?.image}
                                alt=""
                            />
                        </div>
                    </div>
                    <div class="product-details-div-right">
                        <div className="start-container">
                            <span class="product-details-name">
                                {product?.name}
                            </span>
                            {auth.getRoles(localStorage.getItem("token")).includes("ADMIN")
                                ? <button id={product?.id} type="button" className="delete-product-btn" onClick={handleDelete}>Delete</button> : null
                            }
                        </div>
                        <span class="product-details-price">
                            € {product?.price?.toFixed(2)}
                        </span>
                        <p class="product-details-description">
                            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Vitae
                            animi ad minima veritatis dolore. Architecto facere dignissimos
                            voluptate fugit ratione molestias quis quidem exercitationem
                            voluptas.
                        </p>
                        <div class="btn-details-groups">
                            {product?.quantity > 0 ?
                                <button type="button" class="add-cart-btn" id={product?.id} onClick={addProduct}>
                                    <i class="fas fa-shopping-cart"></i>add to cart
                                </button>
                                : null}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default ProductsDetails;