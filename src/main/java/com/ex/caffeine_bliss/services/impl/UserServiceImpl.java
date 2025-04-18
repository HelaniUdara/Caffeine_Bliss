package com.ex.caffeine_bliss.services.impl;

import com.ex.caffeine_bliss.DTOs.UserDTO;
import com.ex.caffeine_bliss.DTOs.paginated.PaginatedResponse;
import com.ex.caffeine_bliss.DTOs.request.RequestAddUserDTO;
import com.ex.caffeine_bliss.DTOs.request.RequestResetPasswordDTO;
import com.ex.caffeine_bliss.DTOs.request.RequestUpdateUserDTO;
import com.ex.caffeine_bliss.entities.User;
import com.ex.caffeine_bliss.entities.enums.UserRole;
import com.ex.caffeine_bliss.exceptions.DuplicateElementException;
import com.ex.caffeine_bliss.exceptions.ResourceNotFoundException;
import com.ex.caffeine_bliss.repositories.UserRepository;
import com.ex.caffeine_bliss.services.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUser(RequestAddUserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new DuplicateElementException("This email is already registered!");
        } else {
            User user = modelMapper.map(userDTO, User.class);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userRepository.save(user);
            return "Added user " + userDTO.getFirstName();
        }
    }

    @Override
    public UserDTO updateUser(RequestUpdateUserDTO userDTO) {
        if(userRepository.existsById(userDTO.getId())){
            User user = userRepository.getReferenceById(userDTO.getId());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            userRepository.save(user);
            return modelMapper.map(user, UserDTO.class);
        }else {
            throw new ResourceNotFoundException("Record not exists by ID: " + userDTO.getId());
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAllByActiveEquals(true);
        if(users.size() < 1){
            throw new ResourceNotFoundException("There isn't any active users in the database.");
        }else{
            List<UserDTO> userDTOList = modelMapper.map(users, new TypeToken<List<UserDTO>>(){}.getType());
            return userDTOList;
        }
    }

    @Override
    public PaginatedResponse<UserDTO> getLimitedUsers(boolean active, int page, int limit) {
        Page<User> users = userRepository.findAllByActiveEquals(active, PageRequest.of(page, limit));
        if (users.getSize() < 1) {
            throw new ResourceNotFoundException("There aren't users according to the request.");
        } else {
            List<User> userEntities = users.getContent();
            List<UserDTO> userList = modelMapper.map(userEntities, new TypeToken<List<UserDTO>>() {
            }.getType());
            int totalCount = userRepository.countAllByActiveEquals(active);
            return new PaginatedResponse<UserDTO>(userList, page, limit, totalCount);
        }
    }

    @Override
    public List<UserDTO> getUsersByRole(UserRole role) {
        List<User> users = userRepository.findAllByActiveEqualsAndRoleEquals(true, role);
        if(users.size() < 1){
            throw new ResourceNotFoundException("There isn't any active " + role + " users in the database.");
        }else{
            List<UserDTO> userDTOList = modelMapper.map(users, new TypeToken<List<UserDTO>>(){}.getType());
            return userDTOList;
        }
    }

    @Override
    public List<UserDTO> getAllOldUsers() {
        List<User> users = userRepository.findAllByActiveEquals(false);
        if(users.size() < 1){
            throw new ResourceNotFoundException("There isn't any retired users in the database.");
        }else{
            List<UserDTO> userDTOList = modelMapper.map(users, new TypeToken<List<UserDTO>>(){}.getType());
            return userDTOList;
        }
    }

    @Override
    public String deactivateUser(UUID id) {
        if(userRepository.existsById(id)){
            User user = userRepository.getReferenceById(id);
            user.setActive(false);
            userRepository.save(user);
            return "User Deactivated!";
        }else {
            throw new ResourceNotFoundException("Record not exists by ID: " + id);
        }
    }

    @Override
    public UserDTO getUserById(UUID id) {
        User user = userRepository.getReferenceById(id);
        if(user != null){
            return modelMapper.map(user, UserDTO.class);
        }else {
            throw new ResourceNotFoundException("There is no user with Id: " + id);
        }
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user != null){
            return modelMapper.map(user, UserDTO.class);
        }else {
            throw new ResourceNotFoundException("This email is not registered!");
        }
    }

    @Override
    public String resetPassword(RequestResetPasswordDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail());
        if (user == null) {
            throw new ResourceNotFoundException("This user is not registered!");
        }
        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            throw new ResourceNotFoundException("Current password is incorrect.");
        }
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        user.setNeedsPasswordReset(false);
        userRepository.save(user);
        return "Password has been reset successfully for " + user.getFirstName();
    }

    @Override
    public String resetPasswordFromLink(String email, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("This user is not registered!");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setNeedsPasswordReset(false);
        userRepository.save(user);
        return "Password has been reset successfully for " + user.getFirstName();
    }

}
