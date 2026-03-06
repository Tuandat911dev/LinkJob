package vn.com.linkjob.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import vn.com.linkjob.domain.User;
import vn.com.linkjob.dto.CreateUserRequestDTO;
import vn.com.linkjob.dto.UserResponseDTO;
import vn.com.linkjob.mapper.UserMapper;
import vn.com.linkjob.repository.UserRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public UserResponseDTO createUser(CreateUserRequestDTO request) {
        User newUser = userMapper.toUser(request);

        return userMapper.toUserResponseDTO(userRepository.save(newUser));
    }
}
