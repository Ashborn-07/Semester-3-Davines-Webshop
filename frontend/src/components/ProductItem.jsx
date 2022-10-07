import React from "react";

function ProductItem (props) {

    return (
        <div>
            <img className="w-[23%]" src="https://res.cloudinary.com/dssmw7qxi/image/upload/v1664895824/jljudhgdukndvgz5qtee_tjifmc.jpg" alt="" />
            <h1 className="italic text-xl font-semibold">{props.product.name}</h1>
            <p className="text-sm">{props.product.description}</p>
        </div>
    )
}

export default ProductItem; 