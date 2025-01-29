package com.refla.reportes.service;

import com.refla.reportes.dto.UserDTO;
import com.refla.reportes.entity.User;
import com.refla.reportes.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserReportService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> Users() {
        return userRepository.findAll().stream()
                .map(user -> UserDTO.builder()
                        .id(user.getCodigo())
                        .username(user.getUsername())
                        .build())
                .collect(Collectors.toList());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
//    public List<User> getAllUsers() {
//        return userRepository.findAll();

        //       List<User> users = userRepository.findAll();
//        return users.stream()
//                .map(user -> UserDTO.builder()
//                       .id(user.getId())
//                        .username(user.getUsername())
//                        .roles(user.getRoles())
//                        .active(user.getActive())
//                        .createdDate(user.getCreatedDate())
//                        .build())
//                .collect(Collectors.toList());
//   }
//
//    public List<UserDTO> Users() {
//        return List.of();
//    }
//}
