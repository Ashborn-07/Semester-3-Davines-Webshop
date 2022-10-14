import React, { useEffect, useState } from "react";
import SeriesList from "../components/SeriesList"
import axios from "axios";

function SeriesPage() {
    const [series, setSeries] = useState([]);
    useEffect(() => {
        axios.get("http://localhost:8080/series")
            .then(res => setSeries(res.data.series))
            .catch(err => console.log(err));
    }, []);

    return (
        <div>
            <SeriesList series={series}/>
        </div>
    )
}

export default SeriesPage;