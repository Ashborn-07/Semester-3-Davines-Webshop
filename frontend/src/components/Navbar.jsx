import React, {useState} from "react";
import { AiOutlineClose, AiOutlineMenu } from 'react-icons/ai'
import { NavLink } from "react-router-dom";

const Navbar = () => {
    const [nav, setNav] = useState(true)

    const handleNav = () => {
        setNav(!nav)
    }

    return (
        <div className='flex justify-between items-center h-24 max-w-[1240px] mx-auto px-4 text-black'>
            <h1 className='cursor-pointer w-full text-3xl font-bold text-[#000] font-serif italic'><NavLink to="/">davines</NavLink></h1>
            <ul className="hidden md:flex font-semibold">
                <li className="p-4"><NavLink to="/">Home</NavLink></li>
                <li className="p-4"><NavLink to="/products">Products</NavLink></li>
                <li className="p-4"><NavLink to="/series">Series</NavLink></li>
                <li className="p-4"><NavLink to="/about">About</NavLink></li>
                <li className="p-4"><NavLink to="/contact">Contact</NavLink></li>
            </ul>
            <div onClick={handleNav} className='block md:hidden'>
                {!nav ? <AiOutlineClose size={20}/> : <AiOutlineMenu size={20}/>}
            </div>
            <div className={!nav ? 'fixed left-0 top-0 w-[60%] border-r border-r-black bg-[#fff] ease-in-out duration-500' : 'fixed left-[-100%]'}>
                <h1 className="cursor-pointer w-full text-3xl font-bold text-[#000] font-serif italic m-8"><NavLink to="/">davines</NavLink></h1>
                <ul className="uppercase p-4 font-semibold">
                    <li className="p-4 border-b border-black"><NavLink to="/">Home</NavLink></li>
                    <li className="p-4 border-b border-black"><NavLink to="/products">Products</NavLink></li>
                    <li className="p-4 border-b border-black"><NavLink to="/series">Series</NavLink></li>
                    <li className="p-4 border-b border-black"><NavLink to="/about">About</NavLink></li>
                    <li className="p-4"><NavLink to="/contact">Contact</NavLink></li>
                </ul>
            </div>
        </div>
    )
}

export default Navbar;