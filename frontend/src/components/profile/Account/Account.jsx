import React, {useEffect} from "react";
import {TextField} from "@mui/material";
import auth from "./../../../service/auth/AuthenticationService";
import axios from "axios";
import "./account.css";

function Account() {

    const [user, setUser] = React.useState([]);

    useEffect(() => {
        if (auth.isTokenExpired(localStorage.getItem("token"))) {
            localStorage.removeItem("token");
            window.location.href = '/login';
        }

        if (auth.getUserId() !== null) {
            axios.get("http://localhost:8080/users/details/" + auth.getUserId(), {
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("token")
                }
            })
                .then(res => {
                    if (res.status === 200) {
                        setUser(res.data);
                    }
                }).catch(err => {
                console.log(err);
            });
        }
    }, []);

    return (
        <div className="account-wrapper">
            <div className="account-display">
                <div className="account-display-left">
                    <div className="account-basic-information">
                        <div className="profile-avatar-field">
                            <img className="profile-avatar"
                                 src="https://res.cloudinary.com/dssmw7qxi/image/upload/v1673281808/Account-512_lvhdfj.png"
                                 alt=""/>
                        </div>

                        <div className="profile-information-fields">
                            <h1 className="profile-information-title">My profile</h1>
                            <div className="profile-information-name">
                                <TextField
                                    id="disabled-firstName"
                                    variant="standard"
                                    value={user.firstName}
                                />
                                <TextField
                                    id="disabled-lastName"
                                    variant="standard"
                                    value={user.lastName}
                                />
                            </div>
                            <div className="profile-information-birthday">
                                <TextField
                                    sx={{width: "76%"}}
                                    id="disabled-birthday"
                                    variant="standard"
                                    value={user.birthday}
                                />
                            </div>
                        </div>
                    </div>
                </div>
                <div className="account-display-right">
                    <div className="display-right-top">
                        <div className="display-right-contacts">
                            <h1 className="display-contact-title">Contact information</h1>
                            <div className="display-contacts-information">
                                <TextField
                                    sx={{width: "100%"}}
                                    id="disabled-email"
                                    variant="standard"
                                    value={user.email}
                                />
                                <TextField
                                    sx={{width: "100%"}}
                                    id="disabled-phone"
                                    variant="standard"
                                    value={user.phoneNumber}
                                />
                            </div>
                        </div>
                    </div>
                    <div className="display-right-bottom">
                        <div className="display-bottom-orders">
                            <h1 className="display-orders-title">My recent orders</h1>
                            {/* тука последните 2/3 поръчки*/}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Account;