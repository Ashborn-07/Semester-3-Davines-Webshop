import React, {useEffect} from "react";
import Orders from "./Orders/Orders";
import "./adminLayout.css";

function AdminLayout(props) {

    useEffect(() => {

    }, [props.activeTab]);

    function returnOrdersTab() {
        return (
            <Orders></Orders>
        );
    }

    return (
        <div className="admin-layout">
            {props.activeTab === 'Orders' ? returnOrdersTab() : null}
        </div>
    );
}

export default AdminLayout;