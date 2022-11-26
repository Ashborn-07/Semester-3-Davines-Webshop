import React, { useEffect, useState } from "react";
import axios from "axios";

function SeriesDetails() {
    const [series, setSeries] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080" + window.location.pathname)
            .then(res => setSeries(res.data))
            .catch(err => console.log(err));
    }, []);

    console.log(series);

    return (
        <div>
            <h1>{series.series?.name}</h1>
            <h2>{series.series?.description}</h2>
            {series.products?.map(product => (
                <div key={product.id}>
                    <h1>{product.name}</h1>
                </div>
            ))}
        </div>
    )
}

export default SeriesDetails;