import React from "react";
import { NavLink } from "react-router-dom";
import "./seriesItem.css";


function SeriesItem(props) {

    return (
        <div className="series-wrapper">
            <NavLink to={`/series/details/${props.series.id}`}>
            <img className="series-image" src={props.series.image} alt="" />
            <label className="series-label">{props.series.name}</label>
            </NavLink>
        </div>
    )
}

export default SeriesItem;