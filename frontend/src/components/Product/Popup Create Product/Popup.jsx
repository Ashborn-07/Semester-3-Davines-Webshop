import React, { useEffect, useState } from "react";
import ImageUploader from "../Image Uploader/ImageUploader";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faClose } from "@fortawesome/free-solid-svg-icons";
import "./popup.css";
import axios from "axios";

function Popup(props) {

    return (props.trigger) ? (
        <div className="popup">
            <div className="popup-inner">
                <div className="group-items">

                    <ImageUploader />
                    <div className="inputs">
                        <div className="input-wrapper">
                            <form className="input-form" action="#">
                                <div className="product-field">
                                    <input className="product-input" type="text" required />
                                    <label className="product-label">Product Name</label>
                                </div>
                                <div className="product-field">
                                    <input className="product-input" type="text" required />
                                    <label className="product-label">Product Type</label>
                                </div>
                                <div className="product-field description">
                                    <input className="product-input" type="text" required />
                                    <label className="product-label">Description</label>
                                </div>
                                <div className="product-field description">
                                    <input className="product-input" type="text" required />
                                    <label className="product-label">Product Quantity</label>
                                </div>
                                <div className="product-field description">
                                    <select className="product-input">
                                        <option value="" selected disabled hidden>Choose here</option>
                                        {props.series.map((series) => {
                                            return (
                                                <option key={series.id} value={series.id}>{series.name}</option>
                                            )
                                            })
                                        }
                                    </select>
                                </div>
                                <div className="product-field description">
                                    <input className="product-input" type="number" step="any" required />
                                    <label className="product-label">Product price</label>
                                </div>
                                <button className="custom-btn">Add new product</button>
                            </form>
                        </div>
                    </div>
                </div>
                <FontAwesomeIcon icon={faClose} className="close-btn" onClick={() => props.setTrigger(false)} />
                {/* <button className="close-btn" onClick={() => props.setTrigger(false)}>close</button> */}
            </div>
        </div>
    ) : "";
}

export default Popup;