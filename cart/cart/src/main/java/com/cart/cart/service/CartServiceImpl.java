package com.cart.cart.service;

import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cart.cart.exception.CustomException;
import com.cart.cart.models.Cart;
import com.cart.cart.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {
    
    Logger logger= LoggerFactory.getLogger(CartServiceImpl.class);
    
    @Autowired
    private CartRepository cartRepository;
 
    @Override
    public Cart getCartById(int cartId)
    {
            
        return cartRepository.findByCartId(cartId);
    }



   @Override
    public Cart updateCart(int cartId,Cart cart) {
        
        Cart updatedCart= cartRepository.findByCartId(cartId);
        
        updatedCart.setCartId( cart.getCartId());
        updatedCart.setMedicines( cart.getMedicines());
        updatedCart.setTotalPrice( cart.getTotalPrice());



       cartRepository.save(updatedCart);
        
        return updatedCart;
        
    }



   @Override
    public List<Cart> getallcarts() {
        
        return cartRepository.findAll();
    }



   @Override
    public double cartTotal(Cart cart) {
        
        return cart.getTotalPrice();
    }



   @Override
    public Cart addCart(Cart cart){
        
        return cartRepository.save(cart);
    }



   @Override
    public void deleteCartById(int cartId) {
        cartRepository.deleteById(cartId);
        
    }
}