package com.auctionapi.api.controller;

import com.auctionapi.api.entity.DemoUsersEntity;
import com.auctionapi.api.service.DemoUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.auctionapi.api.model.DemoResetPasswordRequestBody;


import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Adjust if needed
public class DemoUsersController {

    @Autowired
    private DemoUsersService demoUsersService;

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody DemoUsersEntity user) {
        return ResponseEntity.ok(demoUsersService.createUser(user));
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody DemoUsersEntity user) {
    	DemoUsersEntity updatedUser = demoUsersService.updateUser(user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(401).body("Failed to update user data...");
        }
    }

    @GetMapping("/listAllUsers")
    public ResponseEntity<?> listAllUsers(@RequestParam(defaultValue = "0") Integer pageNumber,
                                          @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.ok(demoUsersService.getAllUsers(pageNumber, size));
    }

    @GetMapping("/getUserById")
    public ResponseEntity<?> getUserById(@RequestParam Long id) {
        Optional<DemoUsersEntity> user = demoUsersService.getUserById(id);
        return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
        	System.out.println("Received request to delete user with ID: " + id);
            demoUsersService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(401).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred.");
        }
    }

    @GetMapping("/countUsers")
    public ResponseEntity<?> countUsers() {
        return ResponseEntity.ok(demoUsersService.countUsers());
    }

    @PostMapping("/signupUser")
    public ResponseEntity<?> signupUser(@RequestBody DemoUsersEntity user) {
        DemoUsersEntity createdUser = demoUsersService.signupUser(
                user.getUsername(), user.getEmail(), user.getPassword()
        );

        if (createdUser != null) {
            return ResponseEntity.ok(createdUser);
        } else {
            return ResponseEntity.status(400).body("Email already exists or signup failed");
        }
    }
    
	@PostMapping("/loginUser")
    public ResponseEntity<?> loginUser(@RequestBody DemoUsersEntity loginRequest) {
        DemoUsersEntity user = demoUsersService.loginUser(
            loginRequest.getEmail(), loginRequest.getPassword()
        );

        if (user != null) {
            return ResponseEntity.ok(user); // You could return a token here in real-world apps
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
    	    
    @PostMapping("/user/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody DemoResetPasswordRequestBody request) {
    	boolean isReset = demoUsersService.resetPassword(
    	        request.getId(), 
    	        request.getCurrentPassword(), 
    	        request.getNewPassword()
    	    );
        if (isReset) {
            return ResponseEntity.ok("Password reset successful...");
        } else {
            return ResponseEntity.badRequest().body("Invalid email or user not found...");
        }
    }

}