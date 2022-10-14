import React from "react";

function ProductItem (props) {

    return (
        <div className="w-[350px] h-[400px] rounded-[10px] outline-none outline-offset-[-8px] outline-[rgba(0, 0, 0, 0, .5)] shadow-[0_3px_3px_10px_rgba(0,0,0,.1)] relative overflow-hidden m-2">
            <img className="w-[100%] h-[100%] absolute inset-0 object-cover opacity-[.93] transition-transform hover:scale-[1.15]" src="https://res.cloudinary.com/dssmw7qxi/image/upload/v1665480013/image_tiyxgb.png" alt="" />
            <h1 className="italic text-xl font-semibold absolute left-[15px] bottom-[15px] drop-shadow-[0_0px_0px_1px_rgba(0,0,0,.5)]">{props.product.name}</h1>
        </div>
    )   
}

export default ProductItem; 