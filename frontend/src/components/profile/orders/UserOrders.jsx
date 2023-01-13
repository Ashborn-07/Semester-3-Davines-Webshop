import React, { useEffect, useState } from "react";
import UserOrderList from "./OrderList/UserOrderList";
import { Pagination } from "@mui/material";
import "./userOrder.css";
import axios from "axios";

function UserOrders({user}) {

    const [pageNumber, setPageNumber] = useState(1);
    const [page, setPage] = useState({});
    const [update, setUpdate] = useState(true);

    const handleChangePage = (event, newPage) => {
        setPageNumber(newPage);
        setUpdate(true);
    };

    useEffect(() => {
        if (update) {
            axios.get(`http://localhost:8080/orders/user/${user.id}?orderPage=` + (pageNumber - 1), {
                headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('token')
                }
            }).then((response) => {
                setPage(response.data);
            }).catch((error) => {
                console.log(error);
            });

            setUpdate(false);
        }
    }, [update, pageNumber, user.id]);

    return (
        <div className="user-orders-wrapper">
            <div className="user-orders-container">
                <UserOrderList page={page}/>

                <Pagination className="user-pagination-orders"
                    count={page.totalPages}
                    page={pageNumber}
                    onChange={handleChangePage}
                    color={"secondary"}
                    size={"large"}
                />
            </div>
        </div>
    )
}

export default UserOrders;