import React, { useState, useRef } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCloudArrowUp, faTimes } from "@fortawesome/free-solid-svg-icons";
import './imageUploader.css';

const ImageUploader = ({setImage, image}) => {

    const [previewUrl, setPreviewUrl] = useState("");

    const fileInput = useRef(null);

    const handleFile = file => {
        //you can carry out any file validations here...
        setImage(file);
        setPreviewUrl(URL.createObjectURL(file));
    }

    const handleOndragOver = event => {
        event.preventDefault();
    }

    const handleOndrop = event => {
        //prevent the browser from opening the image
        event.preventDefault();
        event.stopPropagation();
        //let's grab the image file
        let imageFile = event.dataTransfer.files[0];
        handleFile(imageFile);
    }

    const handleRemoveImage = () => {
        setImage(null);
        setPreviewUrl(null);
    }

    const dragEnter = event => {
        event.currentTarget.style.border = "2px dashed #9658fe"
    }

    const dragLeave = event => {
        event.currentTarget.style.border = "2px dashed #c2cdda"
    }

    return (
        <form className="upload-container" method="post">
            <div className="upload-wrapper" onDragEnter={dragEnter} onDragLeave={dragLeave}>
                <div className="image-preview"
                    onDragOver={handleOndragOver}
                    onDrop={handleOndrop}
                    onClick={() => fileInput.current.click()}>
                    {previewUrl && <img src={previewUrl} alt="#"/>}
                </div>
                <div className="upload-content">
                    <div className="upload-icon"><FontAwesomeIcon icon={faCloudArrowUp} /></div>
                    <div className="upload-text">No file chosen, yet!</div>
                </div>
                <div className="upload-cancel-btn" onClick={handleRemoveImage}><FontAwesomeIcon icon={faTimes} /></div>
                <div className="upload-file-name">{image?.name}</div>
            </div>
            <input className="upload-default-btn" type="file" accept="image/*" ref={fileInput} hidden
                onChange={e => handleFile(e.target.files[0])} />
        </form>
    );
}

export default ImageUploader;