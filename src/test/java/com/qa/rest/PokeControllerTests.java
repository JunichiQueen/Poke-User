package com.qa.rest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.entity.User;
import com.qa.repository.PokeRepository;
import com.qa.service.PokeService;
import com.qa.service.PokeServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PokeControllerTests {
	
	@InjectMocks
	PokeController controller;
	
	@Mock
	PokeServiceImpl service;
	
	private static final User MOCK_USER = new User("1", "ash");
	private static final User MOCK_USER2 = new User("2", "misty");
	public static final Optional<User> MOCK_USER_OPTIONAL = Optional.of(MOCK_USER);
	
	@Test
	public void getAllUsersTest() {
		List<User> MOCK_ARRAY = new ArrayList<User>();
		MOCK_ARRAY.add(MOCK_USER);
		MOCK_ARRAY.add(MOCK_USER2);
		Mockito.when(service.getAllUsers()).thenReturn(MOCK_ARRAY);
		assertEquals(controller.getAllUsers(), MOCK_ARRAY);
		Mockito.verify(service).getAllUsers();
	}
	
	@Test
	public void getAUserTest() {
		List<User> MOCK_ARRAY = new ArrayList<User>();
		MOCK_ARRAY.add(MOCK_USER);
		MOCK_ARRAY.add(MOCK_USER2);
		Mockito.when(service.getAUser((long) 1)).thenReturn(MOCK_USER_OPTIONAL);
		assertEquals(controller.getAUser((long) 1), MOCK_USER_OPTIONAL);
		Mockito.verify(service).getAUser((long) 1);
		
	}
	
	@Test
	public void deleteUserTest() {
		List<User> MOCK_ARRAY = new ArrayList<User>();
		MOCK_ARRAY.add(MOCK_USER);
		MOCK_ARRAY.add(MOCK_USER2);
		Mockito.when(service.deleteUser((long) 1)).thenReturn(ResponseEntity.ok().build());
		assertEquals(controller.deleteUser((long) 1), ResponseEntity.ok().build());
		Mockito.verify(service).deleteUser((long) 1);
		
	}
	
	@Test
	public void deleteUserTest2() {
		List<User> MOCK_ARRAY = new ArrayList<User>();
		MOCK_ARRAY.add(MOCK_USER);
		MOCK_ARRAY.add(MOCK_USER2);
		Mockito.when(service.deleteUser((long) 3)).thenReturn(ResponseEntity.notFound().build());
		assertEquals(controller.deleteUser((long) 3), ResponseEntity.notFound().build());
		Mockito.verify(service).deleteUser((long) 3);
	}
	
	@Test
	public void createUserTest() {
		Mockito.when(service.createUser(MOCK_USER)).thenReturn(ResponseEntity.ok().build());
		assertEquals(controller.createUser(MOCK_USER), ResponseEntity.ok().build());
		Mockito.verify(service).createUser(MOCK_USER);
	}

}