import React from "react";
import SeriesItem from "./SeriesItem";

function SeriesList(props) {

    return (
        <div className=" grid grid-cols-4 gap-1 left-0 m-3 justify-center bg-[#B8ABA0]">
            {props.series?.map(series => (
                <SeriesItem key={series.id} series={series} />
            ))}
        </div>
    )
}

export default SeriesList;