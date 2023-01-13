import React, { useEffect, useState } from "react";
import auth from "../../../service/auth/AuthenticationService";
import axios from "axios";
import "./productDetails.css";
import { useNavigate } from "react-router";
import { useCart } from "react-use-cart";
import { toast } from "react-toastify";

function ProductsDetails() {

    const [product, setProduct] = useState([]);
    const { addItem } = useCart();
    const { getItem } = useCart();
    const navigate = useNavigate();

    const handleDelete = event => {
        axios.delete("http://localhost:8080/products/delete/" + event.target.id, {
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
        let productToAdd = {
            id: product.id,
            name: product.name,
            description: product.description,
            type: product.type,
            price: product.price,
            quantity: 1,
            image: product.image,
            series: product.series,
            maxQuantity: product.quantity
        };
        //check if this product quantity in cart is less then product.quantity
        if (getItem(product.id)?.quantity < product.quantity || getItem(product.id) === undefined) {
            addItem(productToAdd);
        }
        toast.success("Product added to cart");
    }

    return (
        <div className="main-wrapper-product-details">
            <div className="container-product-details">
                <div className="product-details-div">
                    <div className="product-details-div-left">
                        <div className="product-img-container">
                            <img className="product-details-img"
                                src={product?.image}
                                alt=""
                            />
                        </div>
                    </div>
                    <div className="product-details-div-right">
                        <div className="start-container">
                            <span className="product-details-name">
                                {product?.name}
                            </span>
                            {auth.getRoles().includes("ADMIN")
                                ? <button id={product?.id} type="button" className="delete-product-btn"
                                    onClick={handleDelete}>Delete</button> : null
                            }
                        </div>
                        <div className="price-quantity">
                            <span className="product-details-price">
                                € {product?.price?.toFixed(2)}
                            </span>
                            <span className="product-details-quantity">
                                Quantity: {product?.quantity > 0 ? product.quantity : "Out of stock"}
                            </span>
                        </div>
                        <p className="product-details-description">
                            {product?.description}
                        </p>
                        <div className="btn-details-groups">
                            {product?.quantity > 0 ?
                                <button type="button" className="add-cart-btn" id={product?.id} onClick={addProduct}>
                                    <i className="fas fa-shopping-cart"></i>add to cart
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