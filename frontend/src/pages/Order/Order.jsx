import React from "react";
import TextField from "@mui/material/TextField";
import { LocalizationProvider } from "@mui/x-date-pickers";
import { DesktopDatePicker } from "@mui/x-date-pickers";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import cart from "../../service/function/cartFunctions";
import "./order.css";

function Order() {

    const [date, setDate] = React.useState(Date.now());

    return (
        <div className="order-wrapper">
            <form className="order_form">
                <div className="input_fields">
                    <LocalizationProvider dateAdapter={AdapterDayjs}>
                        <DesktopDatePicker
                            label="Date"
                            inputFormat="DD/MM/YYYY"
                            value={date}
                            disabled={true}
                            renderInput={(params) => <TextField {...params} />}
                        />
                    </LocalizationProvider>
                    <div className="name_fields">
                        <TextField sx={{background: '#fff'}}
                            label="First Name"
                        />
                        <TextField sx={{background: '#fff'}}
                            label="Last Name"
                        />
                    </div>
                    <TextField sx={{background: '#fff'}}
                        label="Email"
                    />
                    <TextField sx={{background: '#fff'}}
                        label="Address"
                    />
                    <TextField sx={{background: '#fff'}}
                        label="Phone"
                    />
                </div>
                <div className="products">
                    <ul className="products_item">
                        {cart.getCart().map((item) => (
                            <li className="products_item">
                                <div className="products_image">
                                    <img src={item.image
                                    } alt="#" />
                                </div>
                                <div className="products_time_title">
                                    <div className="products_item_description">
                                        <div className="item_title">{item.name}</div>
                                    </div>
                                    <div className="quantity">
                                        <span className="clearfix quantity">"Quantity"</span>
                                        <div className="left product-quantity-box">
                                            <span>
                                                <input
                                                    type="number"
                                                    className="quantity-input"
                                                    min={0}
                                                    value={item.quantity}
                                                    disabled
                                                />
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div className="products_price">
                                    <span className="price">{item.price}</span>
                                </div>
                            </li>
                        ))}
                    </ul>
                </div>
            </form>
        </div>
    );
}

export default Order;