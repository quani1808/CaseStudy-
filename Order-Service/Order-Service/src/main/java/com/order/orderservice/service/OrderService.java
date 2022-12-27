package com.order.orderservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.order.orderservice.exception.OrderNotFoundException;
import com.order.orderservice.model.Address;
import com.order.orderservice.model.Cart;
import com.order.orderservice.model.Order;
import com.order.orderservice.repository.AddressRepository;
import com.order.orderservice.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGenertorService;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	private static final Logger logger=LoggerFactory.getLogger(OrderService.class);

	public Order saveDrugsDetails(Order order, MultipartFile[] file) throws OrderNotFoundException{
        
        logger.info("Sucessfully Registered new Drug");
    order.setOrderId(Integer.valueOf(sequenceGenertorService.generateSequence(order.SEQUENCE_NAME)));
    Order save =orderRepository.save(order);
    
    return save;
    }



     public List<Order> getAllOrders()   
     {    
       return orderRepository.findAll();
     }  

     
     public void placeOrder(Cart cart, String mode, String fullName) { Order order
     = new Order();
     order.setOrderId(sequenceGenertorService.generateSequence(Order.SEQUENCE_NAME));
     LocalDate today = LocalDate.now();
     order.setOrderDate(today);
     order.setAmountPaid(cart.getTotalPrice());
  
     order.setModeOfPayment(mode);
     order.setOrderStatus("confirmed");
     Address address = addressRepository.findByFullName(fullName);
     order.setUserId(order.getUserId());
     order.setFullName(fullName); orderRepository.save(order);
     logger.info(("order is placed with" + order.getOrderId()));
     restTemplate.delete("http://Cart-Services/cart/delete/" + cart.getCartId()); 
     }
 
     public List<Order> getOrdersById(int orderId)   
     {  
       return  orderRepository.findByUserId(orderId);
      }  

     public Order saveOrUpdate(Order orders)   
     {  
        return orderRepository.save(orders);    
     }
     public void delete(int orderId)   
     {  
        orderRepository.deleteById(orderId);  
     }  
     public void update(Order orders, int orderId)   
     {  
        orderRepository.save(orders);  
     }
        public String deleteOrderByOrderId(int orderId) {
        orderRepository.deleteByOrderId(orderId);
            return "Successfully Deleted";
        }

        public Order updateOrderDetails(Order order)
        {
             return order;
        }
	
}
