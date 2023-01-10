import React, {useEffect, useState} from "react";
import {TextField} from "@mui/material";
import {LocalizationProvider} from "@mui/x-date-pickers";
import {AdapterDayjs} from "@mui/x-date-pickers/AdapterDayjs";
import {DesktopDatePicker} from "@mui/x-date-pickers";
import axios from "axios";
import auth from "../../../service/auth/AuthenticationService";
import * as Yup from 'yup';
import {useFormik} from 'formik';
import {toast} from "react-toastify";
import "./settings.css";

function Settings({user, setUser, setActiveTab, setUpdateOfUser}) {

    const [update, setUpdate] = useState(false);

    const handleDateChange = (newDate) => {
        setUser({birthday: newDate.format("MM/DD/YYYY")});
    }

    function setUpdateState(newValue, oldValue) {
        console.log(newValue);
        if (newValue !== oldValue) {
            setUpdate(true);
            console.log('update');
            console.log(update);
            document.getElementById("update-btn").removeAttribute("disabled");
            return;
        }
        setUpdate(false);
        console.log(update);
        document.getElementById("update-btn").setAttribute("disabled", "disabled");
    }

    useEffect(() => {
        if (auth.isTokenExpired(localStorage.getItem("token"))) {
            localStorage.removeItem("token");
            window.location.href = '/login';
        }

    }, []);

    const formik = useFormik({
        initialValues: {
            firstName: user.firstName,
            lastName: user.lastName,
            email: user.email,
            phone: user.phoneNumber
        },
        validationSchema: Yup.object({
            firstName: Yup.string()
                .required('Required'),
            lastName: Yup.string()
                .required('Required'),
            email: Yup.string()
                .email('Invalid email address')
                .required('Required'),
            phone: Yup.string()
                .max(10).min(10)
                .required('Required')
        }),
        onSubmit: values => {

            axios.put("http://localhost:8080/users/update/" + user.id, {
                id: user.id,
                email: values.email,
                firstName: values.firstName,
                lastName: values.lastName,
                birthday: user.birthday,
                phoneNumber: values.phone

            }, {
                headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('token')
                }
            }).then(res => {
                if (res.status === 204) {
                    toast.success("Account updated successfully");
                    setUpdateOfUser(true);
                    setActiveTab('Account');
                }
            }).catch(err => {
                toast.error("Something went wrong");
                console.log(err);
            });
        }
    });

    return (
        <div className="settings-wrapper">
            <form className="settings-form" onSubmit={formik.handleSubmit}>
                <div className="settings-inner-container">
                    <h1 className="settings-title">Update Account information</h1>
                    <div className="settings-fields">
                        <div className="settings-left">
                            <TextField
                                id="update-firstName"
                                label="First Name"
                                name="firstName"
                                type="text"
                                helperText={formik.touched.firstName && formik.errors.firstName ? formik.errors.firstName : null}
                                error={formik.touched.firstName && formik.errors.firstName ? true : false}
                                value={formik.values.firstName}
                                onChange={(e) => {
                                    formik.handleChange(e);
                                    setUpdateState(e.target.value, user.firstName);
                                }}
                                onBlur={formik.handleBlur}
                            />
                            <TextField
                                id="update-email"
                                label="Email"
                                name="email"
                                type="email"
                                helperText={formik.touched.email && formik.errors.email ? formik.errors.email : null}
                                error={formik.touched.email && formik.errors.email ? true : false}
                                value={formik.values.email}
                                onChange={(e) => {
                                    formik.handleChange(e);
                                    setUpdateState(e.target.value, user.email);
                                }}
                                onBlur={formik.handleBlur}
                            />
                            <LocalizationProvider dateAdapter={AdapterDayjs}>
                                <DesktopDatePicker
                                    label="Birthday"
                                    value={user.birthday}
                                    onChange={(e) => {
                                        handleDateChange(e);
                                        setUpdateState(e.format("MM/DD/YYYY"), user.birthday);
                                    }}
                                    renderInput={(params) => <TextField {...params} />}
                                />
                            </LocalizationProvider>
                        </div>
                        <div className="settings-right">
                            <TextField
                                id="update-lastName"
                                label="Last Name"
                                name="lastName"
                                type="text"
                                helperText={formik.touched.lastName && formik.errors.lastName ? formik.errors.lastName : null}
                                error={formik.touched.lastName && formik.errors.lastName ? true : false}
                                value={formik.values.lastName}
                                onChange={(e) => {
                                    formik.handleChange(e);
                                    setUpdateState(e.target.value, user.lastName);
                                }}
                                onBlur={formik.handleBlur}
                            />
                            <TextField
                                id="update-phone"
                                label="Phone"
                                name="phone"
                                type="text"
                                helperText={formik.touched.phone && formik.errors.phone ? formik.errors.phone : null}
                                error={formik.touched.phone && formik.errors.phone ? true : false}
                                value={formik.values.phone}
                                onChange={(e) => {
                                    formik.handleChange(e);
                                    setUpdateState(e.target.value, user.phoneNumber);
                                }}
                                onBlur={formik.handleBlur}
                            />
                        </div>
                    </div>
                    <button id="update-btn" disabled className="update-btn" type="submit"
                            onSubmit={formik.handleSubmit}>
                        Save
                    </button>
                </div>
            </form>
        </div>
    );
}

export default Settings;