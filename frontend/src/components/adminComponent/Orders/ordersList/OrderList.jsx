import React from "react";
import OrderItem from "../OrderItem/OrderItem";
import "./orderList.css";

function OrderList({page, setUpdate}) {

    return(
        <div className="order-list">
            {page.content?.map(item => (
                <OrderItem setUpdate={setUpdate} orderData={item}/>
            ))}
        </div>
    )
}

export default OrderList;