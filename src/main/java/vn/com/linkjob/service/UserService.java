package vn.com.linkjob.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import vn.com.linkjob.domain.User;
import vn.com.linkjob.dto.user.CreateUserRequestDTO;
import vn.com.linkjob.dto.user.UserResponseDTO;
import vn.com.linkjob.exception.AppException;
import vn.com.linkjob.exception.ErrorCode;
import vn.com.linkjob.mapper.UserMapper;
import vn.com.linkjob.repository.UserRepository;

import java.util.List;
import java.util.stream.Collector;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public UserResponseDTO createUser(CreateUserRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        User newUser = userMapper.toUser(request);

        return userMapper.toUserResponseDTO(userRepository.save(newUser));
    }

    public List<UserResponseDTO> getAllUser() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponseDTO)
                .toList();
    }
}
