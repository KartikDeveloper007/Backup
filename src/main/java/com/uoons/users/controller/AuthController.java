package com.uoons.users.controller;

import com.uoons.users.dto.JwtResponse;
import com.uoons.users.dto.LoginRequest;
import com.uoons.users.enitity.UserEntity;
import com.uoons.users.jwt.JwtUtils;
import com.uoons.users.service.UserService;
import com.uoons.users.serviceImpl.CustomUserDetails;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    ModelMapper modelMapper = new ModelMapper();

    @PostMapping("/token")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity user = modelMapper.map(loginRequest, UserEntity.class);
        userService.isActive(user.getEmail());

        String generatetoken = jwtUtils.generateJwtToken(authentication);
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        List<String> roles = customUserDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok().body(new JwtResponse(generatetoken, customUserDetails.getUsername(), roles, customUserDetails.getUser().getId()));
    }

}
