package com.cart.cart.controller;

import java.util.ArrayList;
import java.util.List;



import javax.validation.Valid;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.cart.cart.exception.CustomException;
import com.cart.cart.models.Cart;
import com.cart.cart.models.Drugs;
import com.cart.cart.models.Medicines;
import com.cart.cart.repository.CartRepository;
import com.cart.cart.service.CartService;
import com.cart.cart.service.CartServiceImpl;
//import com.cartService.CartService.service.CartServiceImpl;

@RestController

@RequestMapping("/drugs/cart")
public class CartController {



    @Autowired
    CartService cartService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    private CartServiceImpl cartServiceImpl;
    
    Logger logger=LoggerFactory.getLogger(CartController.class);
    
    public  CartController(CartService cartService)
    {
    
        this.cartServiceImpl=cartServiceImpl;
    }
    
    public CartController() {
    }
    
@GetMapping("/listAll")
public ResponseEntity<List<Cart>> getAllCarts() {
    return ResponseEntity.ok(cartServiceImpl.getallcarts());

}

//add product to cart using productId and cart Id
@PostMapping("/addtocart/{cartId}/{drugsId}")
public ResponseEntity<Cart> addDrugToCart( @PathVariable  int cartId ,@PathVariable  int drugsId) throws CustomException {
Drugs drugs=restTemplate.getForObject("http://Drugs/drugs/getbyid/"+drugsId,Drugs.class);
    
    logger.info(" "+drugs);
    if(cartRepository.existsById(cartId)) 
    {
    Cart oldCart=cartRepository.findByCartId(cartId);
    List<Integer> idList = new ArrayList<>();
    List<Medicines> oldMedicines3 =oldCart.getMedicines();
    
    for(Medicines m : oldMedicines3) 
    {
        idList.add(m.getDrugsId());
    }
    
     if(idList.contains(drugs.getDrugsId())) 
     {
        logger.info("in if method");
        List<Medicines> oldMedicines2= oldCart.getMedicines();
       for (Medicines m : oldMedicines2) {
            
            if(m.getDrugsId()==drugsId) 
            {
                m.setDrugsQty(m.getDrugsQty()+1);
            }
        }
        
        double price =0;

      for (Medicines m : oldMedicines2) {
            
            price = price+ m.getDrugsCost()*m.getDrugsQty();
        }
        oldCart.setTotalPrice(price);
        
        return new ResponseEntity<>(cartServiceImpl.addCart(oldCart),HttpStatus.CREATED);
        
        
    }else {
    
    
    	Medicines medicines= new Medicines();
    	medicines.setDrugsId(drugsId);
    	medicines.setDrugsCost(drugs.getDrugsCost());
    	medicines.setDrugsName(drugs.getDrugsName());
    	medicines.setDrugsQty(1);
    	medicines.setDateOfExpiration(drugs.getDateOfExpiration());


   List<Medicines> oldMedicines =oldCart.getMedicines();
        oldMedicines.add(medicines);
        oldCart.setMedicines(oldMedicines);
    
        double price =0;
        
        for (Medicines m : oldMedicines) {
            
        price = price + m.getDrugsCost()*m.getDrugsQty();
        }
        oldCart.setTotalPrice(price);
        
        return new ResponseEntity<>(cartServiceImpl.addCart(oldCart),HttpStatus.CREATED);
    }
    }else {
    	Cart cart =  new Cart();
        cart.setCartId(cartId);
        Medicines medicines= new Medicines();
        medicines.setDrugsId(drugsId);
        medicines.setDrugsName(drugs.getDrugsName());
        medicines.setDrugsCost(drugs.getDrugsCost());
        medicines.setDrugsQty(1);
        medicines.setDateOfExpiration(drugs.getDateOfExpiration());
        //medicines.setStockQty(medicines.getStockQty());
        
        List<Medicines> list= new ArrayList<>();
        list.add(medicines);

        cart.setMedicines(list);
        double price =0;
        
        for (Medicines m : list) {
            
            price = price+ m.getDrugsCost()*m.getDrugsQty();
        }
        cart.setTotalPrice(price);
        
        
        
        return new ResponseEntity<>(cartRepository.save(cart),HttpStatus.CREATED);
        }
    }

    //get cart by cartId
    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable int cartId) {
        return new ResponseEntity<>(cartServiceImpl.getCartById(cartId),HttpStatus.OK);
    }

    //update cart by cartId
    @PutMapping("/update/{cartId}")
    public ResponseEntity<Cart> updateCart(@PathVariable int cartId ,@Valid @RequestBody Cart cart) throws CustomException {
        
        
        return ResponseEntity.ok(cartService.updateCart(cartId, cart));
    }



    //delete cart by cartId
    @DeleteMapping("/delete/{cartId}")
    public void deleteCart(@PathVariable int cartId) {
        cartServiceImpl.deleteCartById(cartId);
    }



    //remove item from cart using drugsId and cartId
    @PutMapping("/delete/medicines/{drugsId}/{cartId}")
    public Cart deleteCartDrug(@PathVariable int drugsId ,@PathVariable int cartId) throws CustomException {
        
        Drugs drugs=restTemplate.getForObject("http://Drugs/drugs/findbyid/"+drugsId,Drugs.class);



       Cart cart2=cartServiceImpl.getCartById(cartId);
        List<Medicines> list= new ArrayList<>();
        list=cart2.getMedicines();  
        System.out.println(list);
        
        list.removeIf(m->(m.getDrugsId()==drugsId));
        cart2.setMedicines(list);
        
        
        double price =0;
        
        for (Medicines m : list) {
            
            price = price+ m.getDrugsCost()*m.getDrugsQty();
        }
        cart2.setTotalPrice(price);

        return cartServiceImpl.updateCart(cart2.getCartId(), cart2);
        
    }



    // increase drug count in cart using product and cart Id
    @PutMapping("/increaseQuant/{drugId}/{cartId}")
    public Cart increaseDrugs(@PathVariable int drugId,@PathVariable int cartId) throws CustomException {
        
        
        Cart cart =cartServiceImpl.getCartById(cartId);
        



       List<Medicines> oldMedicines3 =cart.getMedicines();
 
           for (Medicines m : oldMedicines3) {
                
                if(m.getDrugsId()==drugId) {
                    m.setDrugsQty(m.getDrugsQty()+1);
                }
            }
            
            double price =0;
            
            for (Medicines m : oldMedicines3) {
                
                price = price+ m.getDrugsCost()*m.getDrugsQty();
            }
            cart.setTotalPrice(price);
            
            return cartServiceImpl.updateCart(drugId, cart);
            
            
    }

    //decrease Medicines quantity in cart from cartId and  productId
    @PutMapping("/decreaseQuant/{drugstId}/{cartId}")
    public Cart decreaseDrugs(@PathVariable int drugsId,@PathVariable int cartId) throws CustomException {
        
        
        Cart cart =cartServiceImpl.getCartById(cartId);
 
       List<Medicines> oldMedicines3 =cart.getMedicines();
  

           for (Medicines m : oldMedicines3) {
                
                if(m.getDrugsId()==drugsId) {
                    m.setDrugsQty(m.getDrugsQty()-1);
                }
            }
            
            double price =0;
            
            for (Medicines m : oldMedicines3) {
                
                price = price + m.getDrugsCost()*m.getDrugsQty();
            }
            cart.setTotalPrice(price);
            
            return cartServiceImpl.updateCart(drugsId, cart);
            
            
    }
}