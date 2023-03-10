/*package com.order.orderservice;

import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.order.orderservice.model.Address;
import com.order.orderservice.model.Cart;
import com.order.orderservice.model.Order;
import com.order.orderservice.repository.OrderRepository;
import com.order.orderservice.service.OrderService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderApplicationTests {
	
	@MockBean
	private OrderService orderService;
	
	@MockBean
	private OrderRepository orderRepository;
	
	@Autowired
	private MockMvc mockMvc;

	
	@Test
	public void testGetAllOrders() throws Exception {
		List<Cart> cart = new ArrayList<>(Arrays.asList(new Cart("John","1","Capti",50.0,5,100,"very useful")));
		List<Address> address = new ArrayList<>(Arrays.asList(new Address("John","Ritik","rraj02668@gmail.com",721304L,"Mathura",8972188185L)));
		Order order = new Order("123","John", cart,500,"pending",address);
		doReturn(Lists.newArrayList(order)).when(orderService).getAllOrders();
		mockMvc.perform(
				get("/user/get")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(1)));
		
	}
	
	
	@Test
	public void testGetOrdersByUserId() throws Exception {
		List<Cart> cart = new ArrayList<>(Arrays.asList(new Cart("John","1","Capti",50.0,5,100,"very useful")));
		List<Address> address = new ArrayList<>(Arrays.asList(new Address("John","Ritik","rraj02668@gmail.com",721304L,"Mathura",8972188185L)));
		Order order = new Order("123","John", cart,500,"pending",address);
		doReturn(Lists.newArrayList(order)).when(orderService).getOrdersById("John");
		mockMvc.perform(get("/user/get/{id}","John")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(1)));
	}
	
	

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}*/
