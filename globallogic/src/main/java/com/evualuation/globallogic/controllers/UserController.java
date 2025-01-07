package com.evualuation.globallogic.controllers;

import com.evualuation.globallogic.dto.ResponseDTO;
import com.evualuation.globallogic.dto.ResponseLoginDTO;
import com.evualuation.globallogic.dto.UserDTO;
import com.evualuation.globallogic.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDTO> signUp(@RequestBody @Valid UserDTO userDTO) {
       return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDTO> login(@RequestHeader ("token") String token) {
        return  ResponseEntity.status(HttpStatus.OK).body(userService.getUserInfoByToken(token));

    }
}
