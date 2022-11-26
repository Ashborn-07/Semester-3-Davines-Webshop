import React from "react";
import SeriesItem from "../Series Item/SeriesItem";
import "./seriesList.css";

function SeriesList(props) {

    return (
        <div className="series-list">
            {props.series?.map(series => (
                <SeriesItem key={series.id} series={series} />
            ))}
        </div>
    )
}

export default SeriesList;