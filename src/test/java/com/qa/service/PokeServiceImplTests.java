package com.qa.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.qa.entity.User;
import com.qa.repository.PokeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PokeServiceImplTests {
	
	@InjectMocks
	PokeServiceImpl service;
	
	@Mock
	PokeRepository repo;
	
	@Mock
	RestTemplate restTemplate;
	
	@Mock
	JmsTemplate jmsTemplate;
	
	private static final User MOCK_USER = new User("1", "ash");
	private static final User MOCK_USER2 = new User("2", "misty");
	public static final Optional<User> MOCK_USER_OPTIONAL = Optional.of(MOCK_USER);
	
	@Test
	public void getAllUsersTest() {
		List<User> MOCK_ARRAY = new ArrayList<User>();
		MOCK_ARRAY.add(MOCK_USER);
		MOCK_ARRAY.add(MOCK_USER2);
		Mockito.when(repo.findAll()).thenReturn(MOCK_ARRAY);
		assertEquals(service.getAllUsers(), MOCK_ARRAY);
		Mockito.verify(repo).findAll();
	}
	
	@Test
	public void getAUserTest() {
		List<User> MOCK_ARRAY = new ArrayList<User>();
		MOCK_ARRAY.add(MOCK_USER);
		MOCK_ARRAY.add(MOCK_USER2);
		Mockito.when(repo.findById((long) 1)).thenReturn(MOCK_USER_OPTIONAL);
		assertEquals(service.getAUser((long) 1), MOCK_USER_OPTIONAL);
		Mockito.verify(repo).findById((long) 1);
		
	}
	
	@Test
	public void findPokemonByNameTest() {
		List<User> MOCK_ARRAY = new ArrayList<User>();
		MOCK_ARRAY.add(MOCK_USER);
		MOCK_ARRAY.add(MOCK_USER2);
		Mockito.when(repo.findById((long) 1)).thenReturn(MOCK_USER_OPTIONAL);
		Mockito.when(restTemplate.exchange("http://localhost:8081/source/", HttpMethod.GET, null, String.class)).thenReturn(ResponseEntity.ok().build());
		//Mockito.when(jmsTemplate.convertAndSend("AccountQueue", MOCK_USER_OPTIONAL)).thenReturn(ResponseEntity.ok().build());
		assertEquals(service.findPokemonByName((long) 1, "ditto"), ResponseEntity.ok().build().toString());
	}
	
	@Test
	public void deleteUserTest() {
		List<User> MOCK_ARRAY = new ArrayList<User>();
		MOCK_ARRAY.add(MOCK_USER);
		MOCK_ARRAY.add(MOCK_USER2);
		Mockito.when(repo.findById((long) 1)).thenReturn(MOCK_USER_OPTIONAL);
		assertEquals(service.deleteUser((long) 1), ResponseEntity.ok().build());
		Mockito.verify(repo).findById((long) 1);
		
	}
	
	@Test
	public void deleteUserTest2() {
		List<User> MOCK_ARRAY = new ArrayList<User>();
		MOCK_ARRAY.add(MOCK_USER);
		MOCK_ARRAY.add(MOCK_USER2);
		Mockito.when(repo.findById((long) 3)).thenReturn(null);
		assertEquals(service.deleteUser((long) 3), ResponseEntity.notFound().build());
		Mockito.verify(repo).findById((long) 3);
	}
	
	@Test
	public void createUserTest() {
		Mockito.when(repo.save(MOCK_USER)).thenReturn(MOCK_USER);
		assertEquals(service.createUser(MOCK_USER), ResponseEntity.ok().build());
		Mockito.verify(repo).save(MOCK_USER);
	}

}
