import axios from "axios";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router";
import "./login.css";

function LogIn() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();

    function handleSubmit() {

        axios.post("http://localhost:8080/login",
            {
                "username": email,
                "password": password
            })
            .then(res => {
                // This is a terrible way of doing this, but it works for now (jk)
                sessionStorage.setItem("token", res.data.accessToken);
                // local storage
                localStorage.setItem("token", res.data.accessToken);
            })
            .catch(err => console.log(err.message));
    }

    useEffect(() => {
        if (localStorage.getItem("token") !== null) {
            navigate("/");
        }
    }, [navigate]);

    return (
        <div className="login-wrapper">
            <form className="login-form" action="">
                <div className="login-inner-container">
                    <input type="text" name="" id="" onChange={e => setEmail(e.target.value)} />
                    <input type="password" onChange={e => setPassword(e.target.value)} />
                    <button onClick={handleSubmit}>Log in</button>
                </div>
            </form>
        </div>
    );
}

export default LogIn;