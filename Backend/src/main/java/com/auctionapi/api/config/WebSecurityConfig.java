package com.auctionapi.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // You should enable this line if your UserDetailsService and PasswordEncoder are working
        // auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
            .authorizeRequests()
            .antMatchers(
                "/editProfile", "/createPayment", "/updatePayment", "/listAllPayments", "/getPaymentById",
                "/deletePayment", "/countPayments", "/updateBid", "/listAllBids", "/getBidId", "/deleteBid",
                "/countBids", "/createBid", "/createLog", "/updateLog", "/listAllLogs", "/getLogById",
                "/deleteLog", "/countLogs", "/createAdmin", "/updateAdmin", "/loginAdmin", "/listAllAdmins",
                "/getAdminById", "/deleteAdmin/{id}", "/countAdmins", "/deleteAuction", "/Auctionscount",
                "/createAuction", "/updateAuction", "/listAllAuctions", "/countUsers", "/createUser",
                "/signupUser", "/updateUser", "/listAllUsers", "/getUserById",
                "/deleteUser/{id}","/loginUser","/admin/reset-password","/user/reset-password","/updateProfile"  // ✅ Allow DELETE with path variable
            ).permitAll()
            .anyRequest().authenticated();

        httpSecurity.cors();
    }
}
