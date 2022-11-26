import React from "react";
import "./createProduct.css";

function CreateProduct(props) {

    return (props.trigger) ? (
        <div className="popup">
            <div className="popup-inner">
                <button className="close-btn" onClick={() => props.setTrigger(false)}>close</button>
            </div>
        </div>
    ) : "";
}

export default CreateProduct;