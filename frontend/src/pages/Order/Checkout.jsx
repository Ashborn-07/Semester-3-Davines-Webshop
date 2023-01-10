import React from "react";
import TextField from "@mui/material/TextField";
import Autocomplete from "@mui/material/Autocomplete"
import {LocalizationProvider} from "@mui/x-date-pickers";
import {DesktopDatePicker} from "@mui/x-date-pickers";
import {AdapterDayjs} from "@mui/x-date-pickers/AdapterDayjs";
import {useFormik} from 'formik';
import * as Yup from 'yup';
import "./checkout.css";
import {useNavigate} from "react-router-dom";
import {useCart} from "react-use-cart";
import axios from "axios";

function Order() {

    const {
        items,
        cartTotal,
        emptyCart
    } = useCart();
    let date = new Date();
    const navigate = useNavigate();

    const countries = [
        {label: 'Austria', code: 'AT'},
        {label: 'Belgium', code: 'BE'},
        {label: 'Bulgaria', code: 'BG'},
        {label: 'Cyprus', code: 'CY'},
        {label: 'Czech Republic', code: 'CZ'},
        {label: 'Germany', code: 'DE'},
        {label: 'Denmark', code: 'DK'},
        {label: 'Estonia', code: 'EE'},
        {label: 'Spain', code: 'ES'},
        {label: 'Finland', code: 'FI'},
        {label: 'France', code: 'FR'},
        {label: 'United Kingdom', code: 'GB'},
        {label: 'Greece', code: 'GR'},
        {label: 'Hungary', code: 'HU'},
        {label: 'Croatia', code: 'HR'},
        {label: 'Ireland, Republic of (EIRE)', code: 'IE'},
        {label: 'Italy', code: 'IT'},
        {label: 'Lithuania', code: 'LT'},
        {label: 'Luxembourg', code: 'LU'},
        {label: 'Latvia', code: 'LV'},
        {label: 'Malta', code: 'MT'},
        {label: 'Netherlands', code: 'NL'},
        {label: 'Poland', code: 'PL'},
        {label: 'Portugal', code: 'PT'},
        {label: 'Romania', code: 'RO'},
        {label: 'Sweden', code: 'SE'},
        {lable: 'Slovenia', code: 'SI'},
        {label: 'Slovakia', code: 'SK'}
    ];

    const formik = useFormik({
        initialValues: {
            email: '',
            firstName: '',
            lastName: '',
            phone: '',
            city: '',
            address: '',
            country: ''
        },
        validationSchema: Yup.object({
            email: Yup.string()
                .email('Invalid email address')
                .required('Required'),
            firstName: Yup.string()
                .required('Required'),
            lastName: Yup.string()
                .required('Required'),
            phone: Yup.string()
                .max(10).min(10)
                .required('Required'),
            city: Yup.string()
                .required('Required'),
            address: Yup.string()
                .required('Required')
        }),
        onSubmit: values => {

            let product = [];
            items.forEach(item => {
                let productConverted = {
                    id: item.id,
                    name: item.name,
                    price: item.price,
                    quantity: item.quantity,
                    image: item.image,
                    description: item.description,
                    type: item.type,
                    series: item.series
                }
                product.push(productConverted);
            })

            axios.post("http://localhost:8080/orders/create", {
                email: values.email,
                firstName: values.firstName,
                lastName: values.lastName,
                orderDate: date,
                country: values.country,
                city: values.city,
                address: values.address,
                phone: values.phone,
                totalPrice: cartTotal.toFixed(2),
                products: product
            }).then(res => {
                emptyCart();
                console.log(res);
                window.open('/confirmation?orderId=' + res.data.orderId, '_blank', 'noreferrer')
                navigate('/');
            }).catch(err => {
                console.log(err);
            })
        }
    })

    return (
        <div className="order-wrapper">
            <form className="order_form" onSubmit={formik.handleSubmit}>
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
                    <TextField sx={{background: '#fff'}}
                               id="order-email"
                               label="Email"
                               helperText={formik.touched.email && formik.errors.email ? formik.errors.email : null}
                               error={formik.touched.email && formik.errors.email ? true : false}
                               type="email"
                               name="email"
                               onChange={formik.handleChange}
                               value={formik.values.email}
                               onBlur={formik.handleBlur}
                    />
                    <div className="name_fields">
                        <TextField sx={{background: '#fff'}}
                                   id="order-firstName"
                                   label="First Name"
                                   helperText={formik.touched.firstName && formik.errors.firstName ? formik.errors.firstName : null}
                                   error={formik.touched.firstName && formik.errors.email ? true : false}
                                   type="text"
                                   name="firstName"
                                   onChange={formik.handleChange}
                                   value={formik.values.firstName}
                                   onBlur={formik.handleBlur}
                        />
                        <TextField sx={{background: '#fff'}}
                                   id="order-lastName"
                                   label="Last Name"
                                   helperText={formik.touched.lastName && formik.errors.lastName ? formik.errors.lastName : null}
                                   error={formik.touched.lastName && formik.errors.lastName ? true : false}
                                   type="text"
                                   name="lastName"
                                   onChange={formik.handleChange}
                                   value={formik.values.lastName}
                                   onBlur={formik.handleBlur}
                        />
                    </div>
                    <Autocomplete
                        disablePortal
                        id="order-country"
                        value={formik.values.country}
                        onChange={(event, value) => (formik.values.country = value)}
                        options={countries.map((option) => option.label + ' (' + option.code + ')')}
                        sx={{background: '#fff'}}
                        renderInput={(params) =>
                            <TextField
                                {...params}
                                onBlur={formik.handleBlur}
                                inputProps={{
                                    ...params.inputProps
                                }}
                                label="Country"
                                name="country"
                            />}
                    />
                    <div className="phone_city_fields">
                        <TextField sx={{background: '#fff'}}
                                   id="order-phone"
                                   label="Phone"
                                   helperText={formik.touched.phone && formik.errors.phone ? formik.errors.phone : null}
                                   error={formik.touched.phone && formik.errors.phone ? true : false}
                                   type="text"
                                   name="phone"
                                   onChange={formik.handleChange}
                                   value={formik.values.phone}
                                   onBlur={formik.handleBlur}
                        />
                        <TextField sx={{background: '#fff'}}
                                   id="order-city"
                                   label="City"
                                   helperText={formik.touched.city && formik.errors.city ? formik.errors.city : null}
                                   error={formik.touched.city && formik.errors.city ? true : false}
                                   type="text"
                                   name="city"
                                   onChange={formik.handleChange}
                                   value={formik.values.city}
                                   onBlur={formik.handleBlur}
                        />
                    </div>
                    <TextField sx={{background: '#fff'}}
                               id="order-address"
                               label="Address"
                               helperText={formik.touched.address && formik.errors.address ? formik.errors.address : null}
                               error={formik.touched.address && formik.errors.address ? true : false}
                               type="text"
                               name="address"
                               onChange={formik.handleChange}
                               value={formik.values.address}
                               onBlur={formik.handleBlur}
                    />
                    <button className="final_btn" type="submit" onClick={formik.handleSubmit}>
                        Purchase
                    </button>
                </div>
                <div className="products_order_information">
                    <div className="products">
                        <ul className="products_item">
                            {items?.map((item) => (
                                <li className="products_item">
                                    <div className="products_image">
                                        <img src={item.image
                                        } alt="#"/>
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
                                        <span className="price">€ {(item.quantity * item.price).toFixed(2)}</span>
                                    </div>
                                </li>
                            ))}
                        </ul>
                    </div>
                    <div className="total_price_order">
                        <span className="total_order">Total: € {cartTotal.toFixed(2)}</span>
                    </div>
                </div>
            </form>
        </div>
    );
}

export default Order;