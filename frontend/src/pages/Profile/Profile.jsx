import React from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faUser} from "@fortawesome/free-solid-svg-icons";
import {faGear} from "@fortawesome/free-solid-svg-icons";
import {faFileInvoice} from "@fortawesome/free-solid-svg-icons";
import {faComment} from "@fortawesome/free-solid-svg-icons";
import Layout from "../../components/profile/Layout";
import "./profile.css";

function Profile() {

    const [activeTab, setActiveTab] = React.useState('Account');

    return (
        <div className="profile-wrapper">
            <div className="profile-menu">
                <div className="profile-menu-item">
                    <ul className="menu-items">
                        <li className="menu-item-flex" onClick={() => setActiveTab('Account')}>
                            <FontAwesomeIcon className="menu-item-icon" icon={faUser}/>
                            <p>Account</p>
                        </li>
                        <li className="menu-item-flex" onClick={() => setActiveTab('Settings')}>
                            <FontAwesomeIcon className="menu-item-icon" icon={faGear}/>
                            <p>Settings</p>
                        </li>
                        <li className="menu-item-flex" onClick={() => setActiveTab('Orders')}>
                            <FontAwesomeIcon className="menu-item-icon" icon={faFileInvoice}/>
                            <p>Orders</p>
                        </li>
                        <li className="menu-item-flex" onClick={() => setActiveTab('Chat')}>
                            <FontAwesomeIcon className="menu-item-icon" icon={faComment}/>
                            <p>Chat</p>
                        </li>
                    </ul>
                </div>
            </div>

            <div className="profile-component">
                <div className="profile-component-title">
                    <h1 className="component-title">{activeTab}</h1>
                </div>

                <Layout activeTab={activeTab} setActiveTab={setActiveTab}></Layout>
                {/* Component based on the selected menu */}
            </div>
        </div>
    )
}

export default Profile;