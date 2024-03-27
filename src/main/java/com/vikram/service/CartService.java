package com.vikram.service;

import com.vikram.model.Cart;
import com.vikram.model.CartItem;
import com.vikram.request.AddCartItemRequest;

public interface CartService {

    public CartItem addItemToCart(AddCartItemRequest req, String jwt);

    public CartItem updateCartItemQuantity(Long cartItemId,int quantity);

    public Cart removeItemFromCart(Long cartItemId, String jwt);

    public Long calculateCartTotals(Cart cart);

    public Cart findCartById(Long id);

    public Cart findCartByUserId(Long userId);

    public Cart clearCart(Long userId);


}
