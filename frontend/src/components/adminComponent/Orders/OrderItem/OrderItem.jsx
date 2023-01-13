import axios from "axios";
import React, { useEffect } from "react";
import auth from "../../../../service/auth/AuthenticationService";
import { toast } from "react-toastify";
import "./orderItem.css"

function OrderItem({ orderData, setUpdate }) {

    let date = new Date(orderData.orderDate);
    let status = '';

    useEffect(() => {

    }, [orderData]);

    const updateStatus = () => {
        switch (orderData.status) {
            case 'PENDING':
                status = 'IN_PROGRESS';
                break;
            case 'IN_PROGRESS':
                status = 'DELIVERED';
                break;
            default:
                status = 'PENDING';
                break;
        }

        axios.put(`http://localhost:8080/orders/update/` + orderData.id, {
            status: status
        }, {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token'),
            }
        }).then(res => {
            if (res.status === 200) {
                console.log("Status updated");
                toast.success("Status updated");
                setUpdate(true);
            }
        }).catch(err => {
            toast.error("Something went wrong");
        });
    }

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
            <div className="button-flex">
                <button className="order-button">
                    View Details
                </button>
                {orderData.status !== 'DELIVERED' ? <button className="update-order-button" onClick={updateStatus}>Update Status</button> : null}
            </div>
        </div>
    )
}

export default OrderItem;