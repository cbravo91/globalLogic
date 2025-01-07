package com.evualuation.globallogic.services;


import com.evualuation.globallogic.JWT.JwtGenerator;
import com.evualuation.globallogic.dto.PhoneDTO;
import com.evualuation.globallogic.dto.ResponseDTO;
import com.evualuation.globallogic.dto.ResponseLoginDTO;
import com.evualuation.globallogic.dto.UserDTO;
import com.evualuation.globallogic.exception.UnprocessableEntityException;
import com.evualuation.globallogic.mapper.UserMapper;
import com.evualuation.globallogic.models.Phone;
import com.evualuation.globallogic.models.User;
import com.evualuation.globallogic.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtGenerator generator;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a", Locale.ENGLISH);

    @Autowired
    public UserService(UserRepository userRepository, JwtGenerator generator) {
        this.userRepository = userRepository;
        this.generator = generator;
    }

    public ResponseDTO createUser(UserDTO userDTO) {

        ResponseDTO responseDTO = new ResponseDTO();
        UserMapper.mapper.toEntity(userDTO);
        User user = UserMapper.mapper.toEntity(userDTO);
        user.setCreated(LocalDateTime.now());
        user.setActive(true);
        user.setUuid(UUID.randomUUID());
        responseDTO.setToken(generator.generateJwt(user));
        String hashedPassword = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userRepository.save(user);
        responseDTO.setCreated(user.getCreated().format(formatter));
        responseDTO.setActive(user.isActive());
        responseDTO.setUuid(user.getUuid());

        return responseDTO;
    }


    public ResponseLoginDTO getUserInfoByToken(String token) {
        ResponseLoginDTO responseLoginDTO = new ResponseLoginDTO();

        Claims claims = generator.decodeJwt(token);
        responseLoginDTO.setId(claims.get("uuid", String.class));
        responseLoginDTO.setCreated(claims.get("created", String.class));
        responseLoginDTO.setLastLogin(LocalDateTime.now().format(formatter));
        responseLoginDTO.setToken(token);
        responseLoginDTO.setActive(claims.get("active", Boolean.class));
        responseLoginDTO.setName(claims.get("name", String.class));
        responseLoginDTO.setEmail(claims.get("email", String.class));
        responseLoginDTO.setPassword(claims.get("password", String.class));
        try {
        ObjectMapper objectMapper = new ObjectMapper();

            List<PhoneDTO> phones = objectMapper.convertValue(claims.get("phones"), objectMapper.getTypeFactory().constructCollectionType(List.class, Phone.class));
            responseLoginDTO.setPhones(phones);
            return responseLoginDTO;
        } catch (Exception e) {
           throw new UnprocessableEntityException("Token inválido");
        }

    }
}
