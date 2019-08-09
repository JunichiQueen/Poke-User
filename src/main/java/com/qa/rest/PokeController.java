package com.qa.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.qa.entity.User;
import com.qa.service.PokeServiceImpl;


@RestController
@RequestMapping("/pokeuser")
public class PokeController {
	
	private PokeServiceImpl pokeService;
	
	private RestTemplate restTemplate;

	@Autowired
	public PokeController(PokeServiceImpl pokeService, RestTemplate restTemplate) {
		this.pokeService = pokeService;
		this.restTemplate = restTemplate;
	}
	
	@GetMapping
	public List<User> getAllUsers(){
		return pokeService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public Optional<User> getAUser(@PathVariable("id") Long id){
		return pokeService.getAUser(id);
	}
	
	@GetMapping("/PokemonName/{id}/{name}")
	public ResponseEntity<String> findPokemonByName(@PathVariable("id") Long id, @PathVariable("name") String name){
		return pokeService.findPokemonByName(id, name);
	}
	
	@GetMapping("/PokemonNumber/{id}/{number}")
	public ResponseEntity<String> findPokemonByNumber(@PathVariable("id") Long id, @PathVariable("number") String number){
		return pokeService.findPokemonByName(id, number);
	}
	
	@PostMapping
	public ResponseEntity<Object> createUser(@RequestBody User user){
		return pokeService.createUser(user);		
	}
	
	@DeleteMapping
	public ResponseEntity<Object> deleteUser(@PathVariable ("id") Long id){
		return pokeService.deleteUser(id);
	}
}
