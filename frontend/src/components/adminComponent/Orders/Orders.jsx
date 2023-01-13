import React, { useEffect, useState } from "react";
import { Pagination } from "@mui/material";
import OrderList from "./ordersList/OrderList";
import "./orders.css";
import axios from "axios";

function Orders() {

    const [pageNumber, setPageNumber] = useState(1);
    const [page, setPage] = useState({});
    const [update, setUpdate] = useState(true);

    const handleChangePage = (event, newPage) => {
        setPageNumber(newPage);
        setUpdate(true);
    };

    useEffect(() => {
        if (update) {
            axios.get('http://localhost:8080/orders?orderPage=' + (pageNumber - 1), {
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
    }, [update, pageNumber]);

    return (
        <div className="orders-wrapper">
            <div className="orders-container">
                <OrderList page={page}/>

                <Pagination className="pagination-orders"
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

export default Orders;