import React, { useEffect, useState } from "react";
import SeriesList from "../../components/Series/Series List/SeriesList";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import Popup from "../../components/Series/Popup Create Series/PopupSeries";
import auth from "../../service/auth/AuthenticationService";
import axios from "axios";

function SeriesPage() {
    const [series, setSeries] = useState([]);

    const [buttonPopup, setButtonPopup] = useState(false);

    useEffect(() => {
        axios.get("http://localhost:8080/series")
            .then(res => setSeries(res.data.series))
            .catch(err => console.log(err));
    }, []);

    return (
        <section>

            <div className="action-menu">
                {auth.getRoles(localStorage.getItem("token")).includes("ADMIN") ?

                    <div className="add-btn" onClick={() => setButtonPopup(true)}>
                        <div className="add-icon">
                            <FontAwesomeIcon className="i" icon={faPlus} />
                        </div>
                        <span className="add-spanner">Add Series</span>
                    </div>
                    : null}
            </div>

            <Popup trigger={buttonPopup} setTrigger={setButtonPopup}></Popup>

            <SeriesList series={series} />
        </section>
    )
}

export default SeriesPage;