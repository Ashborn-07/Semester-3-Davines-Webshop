import React from "react";
import Home from "./pages/Home/Home";
import ProductsPage from "./pages/Product/ProductsPage";
import SeriesPage from "./pages/Series/SeriesPage";
import AboutPage from "./pages/About/AboutPage";
import ContactPage from "./pages/Contact/ContactPage";
import SeriesDetails from "./pages/Series/SeriesDetails";
import LogIn from "./pages/Log in/LogIn";

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
        path: "/login",
        element: <LogIn />
    }
]

export default AppRoutes;