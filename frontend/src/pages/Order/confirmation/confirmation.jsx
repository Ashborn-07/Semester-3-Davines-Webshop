import React from "react";
import "./confirmation.css";

function confirmation() {

    const query = new URLSearchParams(window.location.search);
    const orderId = query.get("orderId");

    return (
        <div className="confirmation-wrapper">
            <div className="confirmation-container">
                <h1>Your order has been received</h1>
                <div className="success-image">
                    <img src="https://res.cloudinary.com/dssmw7qxi/image/upload/v1673185249/success_prusrd.png"
                         alt="#"/>
                </div>
                <div className="paragraphs">
                    <p>Thank you for your purchase !</p>
                    <p>Your order ID is: {orderId}</p>
                    <p>You will receive an order confirmation email with details of your order.</p>
                </div>
                <div className="confirmation-end">
                    <button className="return_button" onClick={() => window.close()}>
                        Continue shopping
                    </button>
                </div>
            </div>
        </div>
    );
}

export default confirmation;