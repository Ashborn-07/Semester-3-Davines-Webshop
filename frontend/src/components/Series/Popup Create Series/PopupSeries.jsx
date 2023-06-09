import React, {useState} from "react";
import ImageUploader from "./Image Series Uploader/SeriesImageUploader";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faClose} from "@fortawesome/free-solid-svg-icons";
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import {useFormik} from 'formik';
import * as Yup from 'yup';
import axios from "axios";
import {toast} from "react-toastify";
import "./popupSeries.css";

function PopupSeries(props) {

    const [image, setImage] = useState(null);
    var imageLink = null;

    function setImageLink(link) {
        imageLink = link;
    }

    async function handleSubmitImage() {

        const formData = new FormData();

        formData.append('file', image);
        formData.append('upload_preset', 'davines_upload');

        console.log(formData);

        const data = await fetch('https://api.cloudinary.com/v1_1/dssmw7qxi/image/upload', {
            method: 'POST',
            body: formData
        }).then(res => res.json());

        console.log('data', data);
        setImageLink(data.secure_url);
        console.log(imageLink);
    }

    const formik = useFormik({
        initialValues: {
            seriesName: '',
            seriesDescription: ''
        },
        validationSchema: Yup.object({
            seriesName: Yup.string()
                .max(255, 'Too long')
                .required('Series name is required'),
            seriesDescription: Yup.string()
                .max(1000, 'Too long')
                .required('Series description is required'),
        }), onSubmit: async values => {
            await handleSubmitImage();

            console.log(values);
            console.log(imageLink);

            const data = axios.post("http://localhost:8080/series/create", {
                name: values.seriesName,
                description: values.seriesDescription,
                image: imageLink
            }, {
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem("token")}}`
                }
            }).catch(err => {
                console.log(err);
                toast.error("Error creating series. Please fill in all fields.");
            });

            if ((await data).status === 201) {
                console.log("success");
                const resolveAfter2Sec = new Promise(resolve => setTimeout(resolve, 2000));

                toast.promise(resolveAfter2Sec, {
                    loading: "Creating product",
                    success: "Product created",
                    error: "Error creating product"
                });
                props.setTrigger(false);
                props.setUpdatedStatus(true);
            }
        }
    });

    return (props.trigger) ? (
        <div className="popup-series">
            <div className="popup-inner-series">
                <div className="group-items-series">

                    <ImageUploader setImage={setImage} image={image}/>
                    <div className="inputs-series">
                        <div className="input-series-wrapper">
                            <form onSubmit={formik.handleSubmit}>
                                <Box
                                    sx={{
                                        '& > :not(style)': {
                                            m: 1,
                                            width: '90%',
                                            marginBottom: "20px",
                                            textAlign: "center",
                                            marginLeft: "auto",
                                            marginRight: "auto",
                                            marginTop: "20px"
                                        },
                                    }}
                                    noValidate
                                    autoComplete="off"
                                >
                                    <div className="series-input-layout">
                                        <div className="series-right">
                                            <TextField
                                                id="seriesName"
                                                name="seriesName"
                                                error={formik.touched.seriesName && Boolean(formik.errors.seriesName)}
                                                helperText={formik.touched.seriesName && formik.errors.seriesName}
                                                onBlur={formik.handleBlur}
                                                onChange={formik.handleChange}
                                                value={formik.values.seriesName}
                                                label="Series Name"
                                                variant="outlined"
                                                sx={{width: '90%'}}
                                            />
                                            <button className="series-submit-btn" type="submit"
                                                    onClick={formik.handleSubmit}>Add new Series
                                            </button>
                                        </div>
                                        <div className="series-left">
                                            <TextField
                                                id="seriesDescription"
                                                name="seriesDescription"
                                                error={formik.touched.seriesDescription && Boolean(formik.errors.seriesDescription)}
                                                helperText={formik.touched.seriesDescription && formik.errors.seriesDescription}
                                                onBlur={formik.handleBlur}
                                                onChange={formik.handleChange}
                                                value={formik.values.seriesDescription}
                                                label="Series Description"
                                                variant="outlined"
                                                multiline
                                                sx={{width: '90%'}}
                                                rows={6}
                                            />
                                        </div>
                                    </div>
                                </Box>
                            </form>
                        </div>
                    </div>
                </div>
                <FontAwesomeIcon icon={faClose} className="close-btn" onClick={() => props.setTrigger(false)} />
            </div>
        </div>
    ) : "";
}

export default PopupSeries;