package com.auctionapi.api.service;

import com.auctionapi.api.entity.DemoUsersEntity;
import com.auctionapi.api.repositories.DemoUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DemoUsersService {

    @Autowired
    private DemoUsersRepository repository;

    public DemoUsersEntity createUser(DemoUsersEntity user) {
        return repository.save(user);
    }

    public DemoUsersEntity updateUser(DemoUsersEntity user) {
    	try {
            Optional<DemoUsersEntity> optionalUser = repository.findById(user.getUserId());

            if (optionalUser.isPresent()) {
            	DemoUsersEntity newUser = optionalUser.get();
                newUser.setUsername(user.getUsername());
                newUser.setEmail(user.getEmail());
                return repository.save(newUser);
            }
        } catch (Exception e) {
            e.printStackTrace(); // You may replace this with a proper logger
        }

        return null;
    }

    public List<DemoUsersEntity> getAllUsers(int pageNumber, int size) {
        Page<DemoUsersEntity> usersPage = repository.listAllUsersFromDB(PageRequest.of(pageNumber, size));
        return usersPage.getContent();
    }

    public Optional<DemoUsersEntity> getUserById(Long id) {
        return repository.findById(id);
    }

    public void deleteUser(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("User with ID " + id + " does not exist.");
        }

        try {
            repository.deleteById(id);
            System.out.println("User with ID " + id + " deleted successfully.");
        } catch (Exception e) {
            System.err.println("Failed to delete user with ID " + id + ": " + e.getMessage());
            throw new RuntimeException("Failed to delete user with ID " + id);
        }
    }

    public long countUsers() {
        return Long.parseLong(repository.countNumberOfUsers());
    }

    public DemoUsersEntity signupUser(String username, String email, String password) {
        try {
            DemoUsersEntity existingUser = repository.findByEmail(email);

            if (existingUser == null) {
                DemoUsersEntity newUser = new DemoUsersEntity();
                newUser.setUsername(username);
                newUser.setEmail(email);
                newUser.setPassword(password);
                newUser.setRole("USER"); // Default role
                newUser.setCreatedAt(new Date()); // Set current time

                return repository.save(newUser);
            }
        } catch (Exception e) {
            e.printStackTrace(); // You may replace this with a proper logger
        }

        return null;
    }
    
    	public DemoUsersEntity loginUser(String email, String password) {
    	        DemoUsersEntity user = repository.findByEmail(email);
    	        if (user != null && user.getPassword().equals(password)) {
    	            return user;
    	        }
    	        return null;
    	    }
    	    
    	    public boolean resetPassword(Long id, String currentPassword, String newPassword) {
    	        Optional<DemoUsersEntity> optionalUser = repository.findById(id);
    	        if (optionalUser.isPresent()) {
    	        	DemoUsersEntity user = optionalUser.get();
    	            if (currentPassword.equals(user.getPassword())) {
    	                user.setPassword(newPassword);
    	                repository.save(user);
    	                return true;
    	            }
    	        }
    	        return false;
    	    }

    	}

