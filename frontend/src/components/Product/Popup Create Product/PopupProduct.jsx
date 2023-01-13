import React, { useEffect, useState } from "react";
import ImageUploader from "./Image Uploader/ImageUploader";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faClose } from "@fortawesome/free-solid-svg-icons";
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Box from '@mui/material/Box';
import Select from '@mui/material/Select';
import TextField from '@mui/material/TextField';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import "./popupProduct.css";
import axios from "axios";
import { toast } from "react-toastify";

function Popup(props) {

    const [SelectedSeries, SetSelectedSeries] = useState("");
    const [image, setImage] = useState(null);
    var imageLink = null;

    function setImageLink(link) {
        imageLink = link;
    }

    const [seriesData, setSeriesData] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/series")
            .then(res => setSeriesData(res.data.series))
    }, []);

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
            productName: '',
            productType: '',
            productDescription: '',
            productQuantity: 0,
            productPrice: 0
        },
        validationSchema: Yup.object({
            productName: Yup
                .string()
                .max(255)
                .required(
                    'Name is required'),
            productType: Yup
                .string()
                .max(255)
                .required('Product type is required'),
            productDescription: Yup
                .string()
                .max(255)
                .required('Product description is required'),
            productQuantity: Yup
                .number()
                .max(255)
                .required('Product quantity is required'),
            productPrice: Yup
                .number()
                .max(255)
                .required('Product price is required')
        }), onSubmit: async (values) => {
            await handleSubmitImage();

            console.log(values);
            console.log(imageLink);

            const data = axios.post("http://localhost:8080/products/create", {
                name: values.productName,
                type: values.productType,
                description: values.productDescription,
                quantity: values.productQuantity,
                price: values.productPrice,
                image: imageLink,
                seriesId: SelectedSeries
            }, {
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                }
            }).catch(err => {
                console.log(err);
                toast.error("Error creating product. Please fill in all fields.");
            });

            if ((await data).status === 201) {
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
    }
    );

    return (props.trigger) ? (
        <div className="popup">
            <div className="popup-inner">
                <div className="group-items">

                    <ImageUploader setImage={setImage} image={image} />
                    <div className="inputs">
                        <div className="input-wrapper">
                            <form onSubmit={formik.handleSubmit}>
                                <Box
                                    sx={{
                                        '& > :not(style)': { m: 1, width: '90%', marginBottom: "20px", textAlign: "center", marginLeft: "auto", marginRight: "auto", marginTop: "20px" },
                                    }}
                                    noValidate
                                    autoComplete="off"
                                >
                                    <div className="product-field">
                                        <TextField
                                            id="productName"
                                            name="productName"
                                            error={Boolean(formik.touched.productName && formik.errors.productName)}
                                            helperText={formik.touched.productName && formik.errors.productName}
                                            sx={{ width: '90%' }}
                                            onBlur={formik.handleBlur}
                                            onChange={formik.handleChange}
                                            value={formik.values.productName}
                                            label="Product name"
                                            variant="outlined" />
                                    </div>
                                    <div className="product-field">
                                        <TextField
                                            id="productType"
                                            name="productType"
                                            error={Boolean(formik.touched.productType && formik.errors.productType)}
                                            helperText={formik.touched.productType && formik.errors.productType}
                                            sx={{ width: '90%' }}
                                            onBlur={formik.handleBlur}
                                            value={formik.values.productType}
                                            onChange={formik.handleChange}
                                            label="Product Type"
                                            variant="outlined" />
                                    </div>
                                    <div className="product-field">
                                        <TextField
                                            id="productDescription"
                                            name="productDescription"
                                            error={Boolean(formik.touched.productDescription && formik.errors.productDescription)}
                                            helperText={formik.touched.productDescription && formik.errors.productDescription}
                                            sx={{ width: '90%' }}
                                            onBlur={formik.handleBlur}
                                            onChange={formik.handleChange}
                                            value={formik.values.productDescription}
                                            label="Description"
                                            rows={4}
                                            variant="outlined"
                                            multiline />
                                    </div>
                                    <div className="product-field">
                                        <TextField
                                            id="productQuantity"
                                            name="productQuantity"
                                            error={Boolean(formik.touched.productQuantity && formik.errors.productQuantity)}
                                            helperText={formik.touched.productQuantity && formik.errors.productQuantity}
                                            sx={{ width: '90%' }}
                                            onBlur={formik.handleBlur}
                                            onChange={formik.handleChange}
                                            value={formik.values.productQuantity}
                                            label="Product Quantity"
                                            variant="outlined" />
                                    </div>
                                    <div className="product-field">
                                        <FormControl sx={{ width: '90%' }}>
                                            <InputLabel>Series</InputLabel>
                                            <Select
                                                labelId="demo-simple-select-label"
                                                id="productSeries"
                                                name="productSeries"
                                                value={SelectedSeries}
                                                label="Series"
                                                onChange={(e) => SetSelectedSeries(e.target.value)}
                                            >
                                                {seriesData.map((series) => (
                                                    <MenuItem value={series.id}>{series.name}</MenuItem>
                                                ))}
                                            </Select>
                                        </FormControl>
                                    </div>
                                    <div className="product-field">
                                        <TextField
                                            id="productPrice"
                                            name="productPrice"
                                            error={Boolean(formik.touched.productPrice && formik.errors.productPrice)}
                                            helperText={formik.touched.productPrice && formik.errors.productPrice}
                                            sx={{ width: '90%' }}
                                            onBlur={formik.handleBlur}
                                            onChange={formik.handleChange}
                                            value={formik.values.productPrice}
                                            label="Product Price"
                                            variant="outlined" />
                                    </div>
                                    <button className="custom-btn" type="submit" onClick={formik.handleSubmit}>Add new product</button>
                                </Box>
                            </form>
                        </div>
                    </div>
                </div>
                <FontAwesomeIcon icon={faClose} className="close-btn" onClick={() => props.setTrigger(false)} />
                {/* <button className="close-btn" onClick={() => props.setTrigger(false)}>close</button> */}
            </div>
        </div >
    ) : "";
}

export default Popup;