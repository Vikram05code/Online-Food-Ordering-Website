package com.vikram.service;

import com.vikram.Exception.CartException;
import com.vikram.Exception.CartItemException;
import com.vikram.Exception.FoodException;
import com.vikram.Exception.UserException;
import com.vikram.model.Cart;
import com.vikram.model.CartItem;
import com.vikram.request.AddCartItemRequest;

public interface CartService {

    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws UserException, FoodException, CartException, CartItemException;

    public CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws CartItemException;

    public Cart removeItemFromCart(Long cartItemId, String jwt) throws UserException, CartException, CartItemException;

    public Long calculateCartTotals(Cart cart) throws UserException;

    public Cart findCartById(Long id) throws CartException;

    public Cart findCartByUserId(Long userId) throws CartException, UserException;

    public Cart clearCart(Long userId) throws CartException, UserException;




}