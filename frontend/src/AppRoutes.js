import React from "react";
import Home from "./pages/Home";
import ProductsPage from "./pages/ProductsPage";
import SeriesPage from "./pages/SeriesPage";
import AboutPage from "./pages/AboutPage";
import ContactPage from "./pages/ContactPage";
import SeriesDetails from "./pages/SeriesDetails";

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
    }
]

export default AppRoutes;