import React, {useState} from "react";
import TextField from "@mui/material/TextField";
import {LocalizationProvider} from "@mui/x-date-pickers";
import {DesktopDatePicker} from "@mui/x-date-pickers";
import {AdapterDayjs} from "@mui/x-date-pickers/AdapterDayjs";
import {useFormik} from 'formik';
import * as Yup from 'yup';
import {toast} from "react-toastify";
import "./register.css"
import axios from "axios";
import {useNavigate} from "react-router";

function Register() {

    const [date, setDate] = useState(null);

    const handleDateChange = (newDate) => {
        setDate(newDate);
    }

    const navigate = useNavigate();

    const formik = useFormik({
        initialValues: {
            firstName: '',
            lastName: '',
            password: '',
            confirmPassword: '',
            email: '',
            phone: ''
        },
        validationSchema: Yup.object({
            firstName: Yup.string()
                .required('Required'),
            lastName: Yup.string()
                .required('Required'),
            phone: Yup.string()
                .max(10).min(10)
                .required('Required'),
            password: Yup.string()
                .max(30).min(5)
                .required('requried'),
            confirmPassword: Yup.string()
                .max(30).min(5)
                .required('requried')
                .oneOf([Yup.ref('password'), null], 'Passwords must match'),
            email: Yup.string()
                .email('Invalid email address')
                .required('Requried')
        }),
        onSubmit: values => {
            console.log(date.format("DD/MM/YYYY"));
            console.log(values);

            if (date === null) {
                toast.error("Please provide your birthday")
            }

            axios.post("http://localhost:8080/users/create", {
                email: values.email,
                firstName: values.firstName,
                lastName: values.lastName,
                birthday: date.format("MM/DD/YYYY"),
                phoneNumber: values.phone,
                password: values.password
            }).then(res => {
                if (res.status === 201) {
                    toast.success("You have created your account")
                    navigate('/login')
                }
            }).catch(err => {
                toast.error(err)
            })
        }
    })

    return (
        <div className="register-wrapper">
            <form className="register-form" onSubmit={formik.handleSubmit}>
                <div className="register-inner-container">
                    <h1 className="register-title">Create your personal Davines account</h1>
                    <p className="register-subtitle">Let's get to know each other better!</p>
                    <div className="fields-flex">
                        <div className="left-side">
                            {/* First name */}
                            {/* email */}
                            {/* password */}
                            {/* birhtday */}
                            <TextField
                                id="register-firstName"
                                label="First Name"
                                name="firstName"
                                type="text"
                                helperText={formik.touched.firstName && formik.errors.firstName ? formik.errors.firstName : null}
                                error={formik.touched.firstName && formik.errors.firstName ? true : false}
                                onChange={formik.handleChange}
                                value={formik.values.firstName}
                                onBlur={formik.handleBlur}
                            />
                            <TextField
                                id="register-email"
                                label="Email"
                                name="email"
                                type="email"
                                helperText={formik.touched.email && formik.errors.email ? formik.errors.email : null}
                                error={formik.touched.email && formik.errors.email ? true : false}
                                onChange={formik.handleChange}
                                value={formik.values.email}
                                onBlur={formik.handleBlur}
                            />
                            <TextField
                                id="register-password"
                                label="Password"
                                name="password"
                                type="password"
                                helperText={formik.touched.password && formik.errors.password ? formik.errors.password : null}
                                error={formik.touched.password && formik.errors.password ? true : false}
                                onChange={formik.handleChange}
                                value={formik.values.password}
                                onBlur={formik.handleBlur}
                            />
                            <LocalizationProvider dateAdapter={AdapterDayjs}>
                                <DesktopDatePicker
                                    label="Birthday"
                                    value={date}
                                    onChange={handleDateChange}
                                    renderInput={(params) => <TextField {...params} />}
                                />
                            </LocalizationProvider>
                        </div>
                        <div className="right-side">
                            {/* Last name */}
                            {/* phone */}
                            {/* confirm password */}
                            <TextField
                                id="register-lastName"
                                label="Last Name"
                                name="lastName"
                                type="text"
                                helperText={formik.touched.lastName && formik.errors.lastName ? formik.errors.lastName : null}
                                error={formik.touched.lastName && formik.errors.lastName ? true : false}
                                onChange={formik.handleChange}
                                value={formik.values.lastName}
                                onBlur={formik.handleBlur}
                            />
                            <TextField
                                id="register-phone"
                                label="Phone"
                                name="phone"
                                type="text"
                                helperText={formik.touched.phone && formik.errors.phone ? formik.errors.phone : null}
                                error={formik.touched.phone && formik.errors.phone ? true : false}
                                onChange={formik.handleChange}
                                value={formik.values.phone}
                                onBlur={formik.handleBlur}
                            />
                            <TextField
                                id="register-confirmPassword"
                                label="Confirm Password"
                                name="confirmPassword"
                                type="password"
                                helperText={formik.touched.confirmPassword && formik.errors.confirmPassword ? formik.errors.confirmPassword : null}
                                error={formik.touched.confirmPassword && formik.errors.confirmPassword ? true : false}
                                onChange={formik.handleChange}
                                value={formik.values.confirmPassword}
                                onBlur={formik.handleBlur}
                            />
                        </div>
                    </div>
                    <button className="register-btn" type="submit" onClick={formik.handleSubmit}>
                        Register
                    </button>
                </div>
            </form>
        </div>
    )
}

export default Register;