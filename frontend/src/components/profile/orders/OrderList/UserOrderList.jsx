import React from "react";
import UserOrderItem from "../OrderItem/UserOrderItem";
import "../../../adminComponent/Orders/ordersList/orderList.css";

function UserOrderList({ page }) {
    return (
        <div>
            <div className="order-list">
                {page.content?.map(item => (
                    <UserOrderItem orderData={item} />
                ))}
            </div>
        </div>
    )
}

export default UserOrderList;