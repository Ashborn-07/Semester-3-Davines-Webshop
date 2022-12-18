import React from "react";
import Home from "./pages/Home/Home";
import ProductsPage from "./pages/Product/ProductsPage";
import SeriesPage from "./pages/Series/SeriesPage";
import AboutPage from "./pages/About/AboutPage";
import ContactPage from "./pages/Contact/ContactPage";
import SeriesDetails from "./pages/Series/SeriesDetails";
import ProductsDetails from "./pages/Product/Product Details/ProductsDetails";
import LogIn from "./pages/Log in/LogIn";
import Register from "./pages/Register/Register";
import Order from "./pages/Order/Order";

const AppRoutes = [
    {
        index: true,
        path: "/",
        element: <Home />   
    },
    {
        path: "/products",
        element: <ProductsPage />
    },
    {
        path: "series",
        element: <SeriesPage />
    },
    {
        path: "about",
        element: <AboutPage />
    },
    {
        path: "contact",
        element: <ContactPage />
    },
    {
        path: "/series/:id",
        element: <SeriesDetails />
    },
    {
        path: "/products/details/:id",
        element: <ProductsDetails />
    },
    {
        path: "/login",
        element: <LogIn />
    },
    {
        path: "/register",
        element: <Register />
    },
    {
        path: "/order",
        element: <Order />
    }
]

export default AppRoutes;