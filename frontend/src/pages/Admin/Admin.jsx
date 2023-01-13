import React, { useState } from "react";
import auth from "../../service/auth/AuthenticationService";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUsers } from "@fortawesome/free-solid-svg-icons";
import { faFileInvoice } from "@fortawesome/free-solid-svg-icons";
import AdminLayout from "../../components/adminComponent/AdminLayout";
import "./admin.css";

function Admin() {

    const [activeTab, setActiveTab] = useState('Orders');

    return (auth.getRoles().includes("ADMIN")) ? (
        <div className="admin-wrapper">
            <div className="admin-menu">
                <div className="side-menu-items">
                    <ul className="admin-menu-items">
                        <li className="admin-menu-item" onClick={() => setActiveTab('Orders')}>
                            <FontAwesomeIcon className="admin-menu-item-icon" icon={faFileInvoice} />
                            <p>Orders</p>
                        </li>
                        <li className="admin-menu-item" onClick={() => setActiveTab('Users')}>
                            <FontAwesomeIcon className="admin-menu-item-icon" icon={faUsers} />
                            <p>Users</p>
                        </li>
                        <li className="admin-menu-item">

                        </li>
                    </ul>
                </div>
            </div>
            <div className="admin-component">
                <div className="admin-component-title">
                    <h1 className="admin-title">{activeTab}</h1>
                </div>

                <AdminLayout activeTab={activeTab}></AdminLayout>
                {/* Component based on the selected menu */}
            </div>
        </div>
    ) : (
        <h1>Not Found 404</h1>
    )
}

export default Admin;