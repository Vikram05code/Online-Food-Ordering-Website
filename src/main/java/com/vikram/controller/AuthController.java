package com.vikram.controller;

import com.vikram.Exception.UserException;
import com.vikram.config.JwtProvider;
import com.vikram.domain.USER_ROLE;
import com.vikram.model.Cart;
import com.vikram.model.PasswordResetToken;
import com.vikram.model.User;
import com.vikram.repository.CartRepository;
import com.vikram.repository.UserRepository;
import com.vikram.request.LoginRequest;
import com.vikram.request.ResetPasswordRequest;
import com.vikram.response.ApiResponse;
import com.vikram.response.AuthResponse;
import com.vikram.service.CustomUserDetailsService;
import com.vikram.service.PasswordResetTokenService;
import com.vikram.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@Valid @RequestBody User user) throws UserException {
       String email = user.getEmail();
       String password = user.getPassword();
       String fullName = user.getFullName();
       USER_ROLE role = user.getRole();

        User isEmailExist = userRepository.findByEmail(email);

        if(isEmailExist != null){
            throw new UserException("Email is already used with another account");
        }

        //create new user
        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setFullName(fullName);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setRole(role);

        User savedUser = userRepository.save(createdUser);

        Cart cart = new Cart();
        cart.setCustomer(savedUser);
        Cart savedcart = cartRepository.save(cart);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Registration Successful");
        authResponse.setRole(savedUser.getRole());

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signIn(@RequestBody LoginRequest loginRequest){

        String userName = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(userName, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();

        authResponse.setMessage("Login Successful");
        authResponse.setJwt(token);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String roleName = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

        authResponse.setRole(USER_ROLE.valueOf(roleName));

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String userName, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);

        if(userDetails==null){
            throw new BadCredentialsException("Invalid UserName or Password");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid UserName or Password");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());

        return authentication;
    }



    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(

            @RequestBody ResetPasswordRequest req) throws UserException {

        PasswordResetToken resetToken = passwordResetTokenService.findByToken(req.getToken());

        if (resetToken == null ) {
            throw new UserException("token is required...");
        }
        if(resetToken.isExpired()) {
            passwordResetTokenService.delete(resetToken);
            throw new UserException("token get expired...");

        }

        // Update user's password
        User user = resetToken.getUser();
        userService.updatePassword(user, req.getPassword());

        // Delete the token
        passwordResetTokenService.delete(resetToken);

        ApiResponse res=new ApiResponse();
        res.setMessage("Password updated successfully.");
        res.setStatus(true);

        return ResponseEntity.ok(res);
    }

    @PostMapping("/reset-password-request")
    public ResponseEntity<ApiResponse> resetPassword(@RequestParam("email") String email) throws UserException {
        User user = userService.findUserByEmail(email);
        System.out.println("ResetPasswordController.resetPassword()");

        if (user == null) {
            throw new UserException("user not found");
        }

        userService.sendPasswordResetEmail(user);

        ApiResponse res=new ApiResponse();
        res.setMessage("Password reset email sent successfully.");
        res.setStatus(true);

        return ResponseEntity.ok(res);
    }
    


}
