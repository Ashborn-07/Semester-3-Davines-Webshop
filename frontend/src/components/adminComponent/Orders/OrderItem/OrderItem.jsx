import React from "react";

import "./orderItem.css"

function OrderItem({ orderData }) {

    let date = new Date(orderData.orderDate);

    return (
        <div className="order-item-wrapper">
            <div className="order-item">
                <div className="order-information-left">
                    <p className="order-information">Order Id:</p>
                    <p className="order-information">Date of order:</p>
                    <p className="order-information">Total price:</p>
                    <p className="order-information">Status:</p>
                </div>
                <div className="order-information-right">
                    <p className="order-information">{orderData.id}</p>
                    <p className="order-information">{date.toLocaleDateString()}</p>
                    <p className="order-information">€ {orderData.total.toFixed(2)}</p>
                    <p className="order-information">{orderData.status}</p>
                </div>
            </div>  
            <button className="order-button">
                View Details
            </button>

        </div>
    )
}

export default OrderItem;