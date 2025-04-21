package com.auctionapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.auctionapi.api.entity.DemoAdminEntity;
import com.auctionapi.api.model.DemoResetPasswordRequestBody;
import com.auctionapi.api.service.DemoAdminsService;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DemoAdminsController {

    @Autowired
    private DemoAdminsService demoAdminsService;

    @RequestMapping(value = "/createAdmin", method = RequestMethod.POST)
    public ResponseEntity<?> createAdmin(@RequestBody DemoAdminEntity admin) {
        return ResponseEntity.ok(demoAdminsService.createAdmin(admin));
    }

    @RequestMapping(value = "/updateAdmin", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAdmin(@RequestBody DemoAdminEntity admin) {
        return ResponseEntity.ok(demoAdminsService.updateAdmin(admin));
    }

    @RequestMapping(value = "/listAllAdmins", method = RequestMethod.GET)
    public ResponseEntity<?> listAllAdmins(@RequestParam(defaultValue = "0") final Integer pageNumber,
                                           @RequestParam(defaultValue = "10") final Integer size) {
        return ResponseEntity.ok(demoAdminsService.getAllAdmins(pageNumber, size));
    }

    @RequestMapping(value = "/getAdminById", method = RequestMethod.GET)
    public ResponseEntity<?> getAdminById(@RequestParam Long id) {
        Optional<DemoAdminEntity> admin = demoAdminsService.getAdminById(id);
        return admin.isPresent() ? ResponseEntity.ok(admin) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id) {
        try {
        	System.out.println("Received request to delete user with ID: " + id);
            demoAdminsService.deleteAdmin(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(401).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred.");
        }
    }

    @RequestMapping(value = "/countAdmins", method = RequestMethod.GET)
    public ResponseEntity<?> countAdmins() {
        return ResponseEntity.ok(demoAdminsService.countAdmins());
    }
    
    @RequestMapping(value = "/loginAdmin", method = RequestMethod.POST)
    public ResponseEntity<?> loginAdmin(@RequestBody DemoAdminEntity loginRequest) {
        DemoAdminEntity loggedInAdmin = demoAdminsService.loginAdmin(loginRequest.getEmail(), loginRequest.getPassword());
        
        if (loggedInAdmin != null) {
            return ResponseEntity.ok(loggedInAdmin);
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
    
    @PutMapping("/editProfile")
    public ResponseEntity<?> editProfile(@RequestBody DemoAdminEntity admin) {
    	DemoAdminEntity updatedAdmin = demoAdminsService.updateUsernameAndEmail(admin.getAdminId(), admin.getUsername(), admin.getEmail());
    	if (updatedAdmin != null) {
            return ResponseEntity.ok(updatedAdmin);
        } else {
            return ResponseEntity.status(401).body("Failed to update admin data...");
        }
    }
    
    @PostMapping("/admin/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody DemoResetPasswordRequestBody request) {
    	boolean isReset = demoAdminsService.resetPassword(
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
