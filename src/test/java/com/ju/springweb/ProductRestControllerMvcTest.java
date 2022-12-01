package com.ju.springweb;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ju.springweb.entities.Product;
import com.ju.springweb.repository.ProductRepository;

@RunWith(SpringRunner.class)
@WebMvcTest
class ProductRestControllerMvcTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductRepository repository;
	
	@Test
	@WithMockUser(value="admin")
	void testFindAll() throws Exception {
		Product product = new Product();
		product.setId(1);
		product.setName("MacBook");
		product.setDescription("Its Awesome!");
		product.setPrice(1000);
		List<Product> products = Arrays.asList(product);
		when(repository.findAll()).thenReturn(products);
		
		mockMvc.perform(get("/productapi/products/").contextPath("/productapi")).andExpect(status().isOk());
	}
}
