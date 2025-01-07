package com.evualuation.globallogic.mapper;

import com.evualuation.globallogic.dto.PhoneDTO;
import com.evualuation.globallogic.dto.UserDTO;
import com.evualuation.globallogic.models.Phone;
import com.evualuation.globallogic.models.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-06T23:53:14-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.11.1.jar, environment: Java 17.0.13 (Amazon.com Inc.)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setName( user.getName() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setPhones( phoneListToPhoneDTOList( user.getPhones() ) );

        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setName( userDTO.getName() );
        user.setEmail( userDTO.getEmail() );
        user.setPassword( userDTO.getPassword() );
        user.setPhones( phoneDTOListToPhoneList( userDTO.getPhones() ) );

        return user;
    }

    @Override
    public PhoneDTO phoneToPhoneDTO(Phone phone) {
        if ( phone == null ) {
            return null;
        }

        PhoneDTO phoneDTO = new PhoneDTO();

        phoneDTO.setNumber( phone.getNumber() );
        phoneDTO.setCitycode( phone.getCitycode() );
        phoneDTO.setContrycode( phone.getContrycode() );

        return phoneDTO;
    }

    @Override
    public Phone phoneDTOToPhone(PhoneDTO phone) {
        if ( phone == null ) {
            return null;
        }

        Phone phone1 = new Phone();

        phone1.setNumber( phone.getNumber() );
        phone1.setCitycode( phone.getCitycode() );
        phone1.setContrycode( phone.getContrycode() );

        return phone1;
    }

    @Override
    public List<UserDTO> toListDTO(List<User> User) {
        if ( User == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( User.size() );
        for ( User user : User ) {
            list.add( toDTO( user ) );
        }

        return list;
    }

    protected List<PhoneDTO> phoneListToPhoneDTOList(List<Phone> list) {
        if ( list == null ) {
            return null;
        }

        List<PhoneDTO> list1 = new ArrayList<PhoneDTO>( list.size() );
        for ( Phone phone : list ) {
            list1.add( phoneToPhoneDTO( phone ) );
        }

        return list1;
    }

    protected List<Phone> phoneDTOListToPhoneList(List<PhoneDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Phone> list1 = new ArrayList<Phone>( list.size() );
        for ( PhoneDTO phoneDTO : list ) {
            list1.add( phoneDTOToPhone( phoneDTO ) );
        }

        return list1;
    }
}
