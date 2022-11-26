import React from "react";
import './home.css';
import { Typewriter } from 'react-simple-typewriter';

const Home = () => {
    return (
        <div className="home-container">
            <div className="wrapper">
                <p className="sub-title">POST-SUMMER HAIR CARE</p>
                <h1 className="title">How to repair damages caused by sun and salt</h1>
                <div className="container-info">
                    <p className="container-info-p">Different products from series like
                        <span className="typed">
                            {<Typewriter
                                words={['Love', 'Energizing']}
                                loop={false}
                                cursor
                                cursorStyle='|'
                                typeSpeed={120}
                                deleteSpeed={100}
                                delaySpeed={1000}
                            />}
                        </span></p>
                </div>
            </div>
        </div>

    )
}

export default Home;