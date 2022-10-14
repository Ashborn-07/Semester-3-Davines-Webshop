import React from "react";
import { NavLink } from "react-router-dom";


function SeriesItem(props) {

    return (
        <div>
            <NavLink to={`/series/${props.series.id}`}>
            <img src="https://res.cloudinary.com/dssmw7qxi/image/upload/v1649950180/sample.jpg" alt="" />
            <h1>{props.series.name}</h1>
            </NavLink>
        </div>
    )
}

export default SeriesItem;