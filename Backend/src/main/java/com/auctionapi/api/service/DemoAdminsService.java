package com.auctionapi.api.service;

import com.auctionapi.api.entity.DemoAdminEntity;
import com.auctionapi.api.repositories.DemoAdminsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemoAdminsService {

    @Autowired
    private DemoAdminsRepository repository;

    public DemoAdminEntity createAdmin(DemoAdminEntity admin) {
        return repository.save(admin);
    }

    public DemoAdminEntity updateAdmin(DemoAdminEntity admin) {
        return repository.save(admin);
    }

    public List<DemoAdminEntity> getAllAdmins(int pageNumber, int size) {
        Page<DemoAdminEntity> adminsPage = repository.listAllAdminsFromDB(PageRequest.of(pageNumber, size));
        return adminsPage.getContent();
    }

    public Optional<DemoAdminEntity> getAdminById(Long id) {
        return repository.findById(id);
    }

    public void deleteAdmin(Long id) {
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

    public long countAdmins() {
        return Long.parseLong(repository.countNumberOfAdmins());
    }
    
    public DemoAdminEntity loginAdmin(String email, String password) {
        DemoAdminEntity admin = repository.findByEmail(email);

        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }

        return null;
    }
    
    public DemoAdminEntity updateUsernameAndEmail(Long id, String username, String email) {
    	try {
    		Optional<DemoAdminEntity> adminOptional = repository.findById(id);
    		
    		if (adminOptional.isPresent()) {
    			DemoAdminEntity admin = adminOptional.get();
    			admin.setUsername(username);
    			admin.setEmail(email);
    			return repository.save(admin);
    		}    		
    	} catch(Exception e) {
    		e.printStackTrace(); // You may replace this with a proper logger
    	}
    	
    	return null;
    }
    
    public boolean resetPassword(Long id, String currentPassword, String newPassword) {
        Optional<DemoAdminEntity> optionalAdmin = repository.findById(id);
        if (optionalAdmin.isPresent()) {
            DemoAdminEntity admin = optionalAdmin.get();
            if (currentPassword.equals(admin.getPassword())) {
                admin.setPassword(newPassword);
                repository.save(admin);
                return true;
            }
        }
        return false;
    }


}
