package com.cart.cart.service;

import java.util.List;



import org.springframework.stereotype.Service;



import com.cart.cart.models.Cart;



@Service
public interface CartService  {

   public Cart updateCart(int cartId,Cart cart);

   public List<Cart> getallcarts();

   public double cartTotal(Cart cart);

   public Cart addCart(Cart cart);
   
   public void deleteCartById(int cartId);

   public Cart getCartById(int cartId);
}