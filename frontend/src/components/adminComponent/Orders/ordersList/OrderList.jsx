import React from "react";
import OrderItem from "../OrderItem/OrderItem";
import "./orderList.css";

function OrderList({page}) {

    return(
        <div className="order-list">
            {page.content?.map(item => (
                <OrderItem orderData={item}/>
            ))}
        </div>
    )
}

export default OrderList;