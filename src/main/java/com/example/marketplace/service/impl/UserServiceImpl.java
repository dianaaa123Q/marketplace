package com.example.marketplace.service.impl;

import com.example.marketplace.config.JwtService;
import com.example.marketplace.dto.LoginRequest;
import com.example.marketplace.dto.LoginResponse;
import com.example.marketplace.dto.UserRequest;
import com.example.marketplace.dto.UserResponse;
import com.example.marketplace.emailSender.EmailSenderService;
import com.example.marketplace.entities.User;
import com.example.marketplace.exception.BadRequestException;
import com.example.marketplace.exception.NotFoundException;
import com.example.marketplace.mapper.UserMapper;
import com.example.marketplace.repositories.UserRepository;
import com.example.marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final EmailSenderService senderService;
    private final AuthenticationManager authenticationManager;

    @Override
    public void register(UserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent())
            throw new BadRequestException("this email is already taken!: "+ request.getEmail());
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setPassword(encoder.encode(request.getPassword()));
        String confirmCode = UUID.randomUUID().toString();
        user.setConfirmCode(confirmCode);
        userRepository.save(user);
        senderService.sendEmail(user.getEmail(), "Confirm your email", "http://localhost:8099/user/confirm/" + confirmCode);
    }

    @Override
    public String confirm(String confirmCode) {
        Optional<User> user = userRepository.findByConfirmCode(confirmCode);
        if (user.isPresent()){
            user.get().setConfirmCode("authenticated");
            userRepository.save(user.get());
            return "successfully confirmed!";
        }

        return "user not found!";
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isEmpty())
            throw new NotFoundException("user not found with this email: "+ request.getEmail()+" !", HttpStatus.NOT_FOUND);
        if (!user.get().getConfirmCode().equals("authenticated")){
            throw new BadRequestException("please confirm your email!");
        }
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        }catch (Exception e){
            throw new BadRequestException("bad credentials!");
        }
        String token = jwtService.generateToken(user.get());
        return new LoginResponse(user.get().getId(), user.get().getEmail(), token);
    }
    @Override
    public void add(UserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent())
            throw new BadRequestException("user with this email is already exist! "+ request.getEmail());
        User user = new User();
        user.setEmail(request.getEmail());
        user.setLastname(request.getLastname());
        user.setFirstname(request.getFirstname());
        userRepository.save(user);
    }

    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserResponse> getAll() {
        return userMapper.toDtoS(userRepository.findAll());
    }

    @Override
    public UserResponse byId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty())
            throw new BadRequestException("user with this id is already exist! "+ userId);
        return userMapper.toDto(user.get());
    }


}
