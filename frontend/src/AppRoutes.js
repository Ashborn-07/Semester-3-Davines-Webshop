import React from "react";
import Home from "./pages/Home/Home";
import ProductsPage from "./pages/Product/ProductsPage";
import SeriesPage from "./pages/Series/SeriesPage";
import AboutPage from "./pages/About/AboutPage";
import SeriesDetails from "./pages/Series/SeriesDetails";
import ProductsDetails from "./pages/Product/Product Details/ProductsDetails";
import LogIn from "./pages/Log in/LogIn";
import Register from "./pages/Register/Register";
import Checkout from "./pages/Order/Checkout";
import Confirmation from "./pages/Order/confirmation/confirmation";
import Profile from "./pages/Profile/Profile";
import Admin from "./pages/Admin/Admin";

const AppRoutes = [
    {
        index: true,
        path: "/",
        element: <Home/>
    },
    {
        path: "/products",
        element: <ProductsPage/>
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
        path: "/series/details/:id",
        element: <SeriesDetails />
    },
    {
        path: "/products/details/:id",
        element: <ProductsDetails />
    },
    {
        path: "/login",
        element: <LogIn/>
    },
    {
        path: "/register",
        element: <Register/>
    },
    {
        path: "/profile",
        element: <Profile/>
    },
    {
        path: "/admin",
        element: <Admin/>
    },
    {
        path: "/checkout",
        element: <Checkout/>
    },
    {
        path: "/confirmation",
        element: <Confirmation/>
    }
]

export default AppRoutes;