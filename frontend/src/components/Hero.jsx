import React from "react";
import Typed from "react-typed";

const Hero = () => {
    return (
        <div className="text-black">
            <div className="max-w-[800px] w-full h-screen mx-auto text-center flex flex-col mt-[130px]">
                <p className="text-[#000] font-bold p-1 text-xl">POST-SUMMER HAIR CARE</p>
                <h1 className="md:text-6xl sm:text-6xl text-4xl font-bold md:py-6">How to repair damages caused by sun and salt</h1>
                <div className="flex justify-center items-center">
                    <p className="md-text-5xl sm:text-4xl text-xl font-bold">Different products from series like</p>
                    <Typed 
                    className='md-text-5xl sm:text-4xl text-xl font-bold pl-2 text-gray-500'
                    strings={[
                        "Love",
                        "Energizer"
                    ]} 
                    typeSpeed={120}
                    backSpeed={140}
                    loop/>
                </div>
            </div>
        </div>

    )
}

export default Hero;