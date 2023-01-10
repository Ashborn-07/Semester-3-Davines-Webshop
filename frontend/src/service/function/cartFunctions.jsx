class ShoppingCartService {

    addProduct(product, maxQuantity) {
        let cart = localStorage.getItem('cart');

        if (cart === null) {
            let cart = [];
            cart.push(product);
            localStorage.setItem('cart', JSON.stringify(cart));
        } else {
            cart = JSON.parse(cart);
            if (cart.some(item => item.id === product.id)) {
                cart.forEach(item => {
                    if (item.id === product.id) {
                        if (item.quantity < maxQuantity) {
                            item.quantity++;
                        }
                    }
                });
            } else {
                cart.push(product);
            }
            localStorage.setItem('cart', JSON.stringify(cart));
        }
    }

    removeProduct(product) {
        let cart = localStorage.getItem('cart');

        if (cart === null) {
            return;
        } else {
            cart = JSON.parse(cart);
            if (cart.some(item => item.id === product.id)) {
                cart.forEach(item => {
                    if (item.id === product.id) {
                        item.quantity--;
                        if (item.quantity === 0) {
                            cart = cart.filter(item => item.id !== product.id);
                        }
                    }
                });
            } else {
                return;
            }
            localStorage.setItem('cart', JSON.stringify(cart));
        }
    }

    getCart() {
        return JSON.parse(localStorage.getItem('cart'));
    }

    getTotalPrice() {
        let cart = localStorage.getItem('cart');
        let totalPrice = 0;

        if (cart === null) {
            return totalPrice;
        } else {
            cart = JSON.parse(cart);
            cart.forEach(item => {
                totalPrice += item.price * item.quantity;
            });
            return totalPrice;
        }
    }

    isCartEmpty() {
        let cart = localStorage.getItem('cart');

        if (cart === null) {
            return true;
        } else {
            cart = JSON.parse(cart);
            return false;
        }
    }
}

export default new ShoppingCartService();