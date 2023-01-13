import React from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faClose, faPlus, faMinus, faTrash} from "@fortawesome/free-solid-svg-icons";
import {useNavigate} from "react-router-dom";
import {toast} from "react-toastify";
import {useCart} from "react-use-cart";
import "./shoppingCart.css";

function ShoppingCart(props) {

    // const [carts, setCarts] = useState([]);
    const navigate = useNavigate();
    const {
        isEmpty,
        // totalUniqueItems,
        items,
        // totalItems,
        cartTotal,
        updateItemQuantity,
        removeItem,
        // emptyCart
    } = useCart();

    function addExistingProduct(item) {
        //check if this item quantity in cart is less then product.quantity
        if (item.quantity < item.maxQuantity) {
            updateItemQuantity(item.id, item.quantity + 1);
        }
    }

    function handlePurchase() {
        if (isEmpty) {
            toast.warning("Your cart is empty!");
        } else {
            props.setCartVisibility(false);
            navigate("/checkout");
        }
    }

    return (props.visible) ? (
        <div className="cart">
            <form>
                <div className="minicart_header">
                    <div className="minicart_title">Your shopping cart</div>
                    <FontAwesomeIcon onClick={() => props.setCartVisibility(false)} icon={faClose} className="close-btn" />
                </div>
                <div className="minicart_products">
                    <ul className="cart_items">
                        {items.map((item, index) => (
                            <li className="cart_item" key={index}>
                                <div className="cart_image">
                                    <img src={item.image} alt="#"/>
                                </div>
                                <div className="cart_time_title">
                                    <div className="cart_item_description">
                                        <div className="item_title">{item.name}</div>
                                    </div>
                                    <div className="quantity">
                                        <span className="clearfix quantity">"Quantity"</span>
                                        <div className="left product-quantity-box">
                                            <span>
                                                <FontAwesomeIcon
                                                    onClick={() => updateItemQuantity(item.id, item.quantity - 1)}
                                                    icon={faMinus}
                                                    className="minus-btn"
                                                />
                                            </span>
                                            <input
                                                type="number"
                                                className="quantity-input"
                                                min={0}
                                                value={item.quantity}
                                                disabled
                                            />
                                            <span>
                                                <FontAwesomeIcon
                                                    onClick={() => addExistingProduct(item)}
                                                    icon={faPlus}
                                                    className="plus-btn"
                                                />
                                            </span>
                                            <span>
                                                <FontAwesomeIcon
                                                    onClick={() => removeItem(item.id)}
                                                    icon={faTrash}
                                                    className="trash-btn"
                                                />
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <strong className="right price">
                                    <span className="money">
                                        € {(item.quantity * item.price).toFixed(2)}
                                    </span>
                                </strong>
                            </li>
                        ))}
                    </ul>
                </div>
            </form>
            <div className="minicart_summary">
                <ul className="minicart_footer">
                    <li className="cart_subtotal">
                        <span>Subtotal</span>
                        <span className="total">€ {cartTotal.toFixed(2)}</span>
                    </li>
                    <li>
                        <div className="cart_text">
                            <p>
                                Do you have a promotional code? Enter it in the payment step
                            </p>
                        </div>
                        <button className="purchase-btn" onClick={handlePurchase}>Purchase</button>
                    </li>
                </ul>
            </div>
        </div>
    ) : "";
}

export default ShoppingCart;