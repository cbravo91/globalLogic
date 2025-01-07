package com.evualuation.globallogic.mapper;

import com.evualuation.globallogic.dto.PhoneDTO;
import com.evualuation.globallogic.dto.UserDTO;
import com.evualuation.globallogic.models.Phone;
import com.evualuation.globallogic.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);

    PhoneDTO phoneToPhoneDTO(Phone phone);

    Phone phoneDTOToPhone(PhoneDTO phone);

    List<UserDTO> toListDTO(List<User> User);
}
