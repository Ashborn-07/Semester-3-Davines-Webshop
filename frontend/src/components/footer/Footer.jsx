import "./footer.css";

function footer() {
    return (
        <div className="footer">
            <div className="icons-wrapper">
                <a href="https://www.instagram.com/davines_bg/" target="_blank" rel="noreferrer">
                    <i id="first" className="fa-brands fa-instagram"></i>
                </a>
                <a href="https://www.facebook.com/davines.bg" target="_blank" rel="noreferrer">
                    <i id="second" className="fa-brands fa-facebook"></i>
                </a>
                <a href="https://www.youtube.com/@DavinesChannel" target="_blank" rel="noreferrer">
                    <i id="third" className="fa-brands fa-youtube"></i>
                </a>
            </div>
        </div>
    )
}

export default footer;