import React, {useEffect, useState} from "react";
import Account from "./Account/Account";
import Settings from "./settings/Settings";
import auth from "../../service/auth/AuthenticationService";
import axios from "axios";
import "./layout.css";

function Layout(props) {

    const [user, setUser] = useState({});
    const [update, setUpdateOfUser] = useState(true);

    useEffect(() => {
        if (update) {
            if (auth.getUserId() !== null) {
                axios.get("http://localhost:8080/users/details/" + auth.getUserId(), {
                    headers: {
                        "Authorization": "Bearer " + localStorage.getItem("token")
                    }
                })
                    .then(res => {
                        if (res.status === 200) {
                            setUser(res.data);
                            setUpdateOfUser(false);
                        }
                    }).catch(err => {
                    console.log(err);
                });
            }
        }

        console.log(user);
    }, [props.activeTab, user, update]);

    function returnAccount() {
        return (

            <Account></Account>

        );
    }

    function returnSettings() {
        return (

            <Settings user={user} setUser={setUser} setActiveTab={props.setActiveTab}
                      setUpdateOfUser={setUpdateOfUser}/>

        )
    }

    function returnOrders() {
        return (
            <Settings/>
        )
    }

    function returnChat() {
        return (
            <Settings/>
        )
    }

    return (
        <div className="profile-layout">
            {props.activeTab === 'Account' ? returnAccount() : null}
            {props.activeTab === 'Settings' ? returnSettings() : null}
            {props.activeTab === 'Orders' ? returnOrders() : null}
            {props.activeTab === 'Chat' ? returnChat() : null}
        </div>
    )
}

export default Layout;