import React from "react";
import axios from "axios";
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import { toast } from "react-toastify";
import "./login.css";

function LogIn() {

    const formik = useFormik({
        initialValues: {
            email: '',
            password: ''
        },
        validationSchema: Yup.object({
            email: Yup.string()
                .email('Invalid email address')
                .required('Required'),
            password: Yup.string()
                .required('Required')
        }),
        onSubmit: values => {
            axios.post('http://localhost:8080/login', values)
                .then(res => {
                    if (res.status === 200) {
                        localStorage.setItem('token', res.data.accessToken);
                        window.location.href= "/";
                    }
                })
                .catch(err => {
                    toast.error('Invalid credentials!');
                });
        },
    });

    return (
        <div className="login-wrapper">
            <form className="login-form" onSubmit={formik.handleSubmit}>
                <div className="login-inner-container">
                    <h1 className="login-title">Welcome to Davines</h1>
                    <Box
                        sx={{
                            '& > :not(style)': { m: 1, width: '90%', marginBottom: "20px", textAlign: "center", marginLeft: "auto", marginRight: "auto", marginTop: "20px" },
                        }}
                        noValidate
                        autoComplete="off"
                    >
                        <TextField
                            className="login-input"
                            id="email-login"
                            label="Email"
                            helperText={formik.touched.email && formik.errors.email ? formik.errors.email : null}
                            error={formik.touched.email && formik.errors.email ? true : false}
                            variant="outlined"
                            type="email"
                            name="email"
                            onChange={formik.handleChange}
                            value={formik.values.email}
                            onBlur={formik.handleBlur}
                        />
                        <TextField
                            className="login-input"
                            id="password-login"
                            label="Password"
                            helperText={formik.touched.password && formik.errors.password ? formik.errors.password : null}
                            error={formik.touched.password && formik.errors.password ? true : false}
                            variant="outlined"
                            type="password"
                            name="password"
                            onBlur={formik.handleBlur}
                            onChange={formik.handleChange}
                            value={formik.values.password}
                        />
                        <button className="login-button" type="submit" onClick={formik.handleSubmit}>Log in</button>
                    </Box>
                </div>
            </form >
        </div >
    );
}

export default LogIn;