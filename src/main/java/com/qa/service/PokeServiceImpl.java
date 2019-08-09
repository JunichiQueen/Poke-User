package com.qa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.qa.entity.SearchInfo;
import com.qa.entity.SentUser;
import com.qa.entity.User;
import com.qa.repository.PokeRepository;

@Service
public class PokeServiceImpl implements PokeService{
	
	private PokeRepository repository;
	
	private RestTemplate restTemplate;
	
    private JmsTemplate jmsTemplate;

	@Autowired
	public PokeServiceImpl(PokeRepository repository, RestTemplate restTemplate, JmsTemplate jmsTemplate) {
		this.repository=repository;
		this.restTemplate=restTemplate;
		this.jmsTemplate=jmsTemplate;
	}

	@Override
	public List<User> getAllUsers() {
		return repository.findAll();
	}

	@Override
	public Optional<User> getAUser(Long id) {
		return repository.findById(id);
	}

	@Override
	public ResponseEntity<Object> deleteUser(Long id) {
		if(repository.findById(id) != null) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<String> findPokemonByName(Long id, String name){
		Optional<User> userHolder = repository.findById(id);
		
		if (userHolder.isPresent()) {
			ResponseEntity<String> getPokemon = restTemplate.exchange("http://localhost:8081/source/" + name, HttpMethod.GET, null, String.class);
			
			User userdata = userHolder.get();
			
			SearchInfo newSearch = new SearchInfo();
			newSearch.setName(name);
			newSearch.setTime();
			newSearch.setMemberNumber(userdata.getMemberNumber());
			sendToQueue(newSearch);
			
			return getPokemon;			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<String> findPokemonByNumber(Long id, String number){
		Optional<User> userHolder = repository.findById(id);
		if (userHolder.isPresent()) {
			ResponseEntity<String> getPokemon = restTemplate.exchange("http://localhost:8081/source/" + number, HttpMethod.GET, null, String.class);
			
			User userdata = userHolder.get();
			
			SearchInfo newSearch = new SearchInfo();
			newSearch.setName(number);
			newSearch.setTime();
			newSearch.setMemberNumber(userdata.getMemberNumber());
			sendToQueue(newSearch);
			return getPokemon;			
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<Object> createUser(User user) {
		
		repository.save(user);
		return ResponseEntity.ok().build();
	}

	@Override
	public String updateUser(Long id, User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
    private void sendToQueue(SearchInfo searchinfo){
        SentUser userToStore =  new SentUser(searchinfo);
        System.out.println(searchinfo);
        jmsTemplate.convertAndSend("AccountQueue", userToStore);
    }


}
