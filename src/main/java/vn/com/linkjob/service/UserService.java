package vn.com.linkjob.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.com.linkjob.domain.User;
import vn.com.linkjob.dto.paginate.Meta;
import vn.com.linkjob.dto.paginate.ResultPaginationDTO;
import vn.com.linkjob.dto.user.CreateUserRequestDTO;
import vn.com.linkjob.dto.user.UpdateUserRequestDTO;
import vn.com.linkjob.dto.user.UserResponseDTO;
import vn.com.linkjob.exception.AppException;
import vn.com.linkjob.exception.ErrorCode;
import vn.com.linkjob.mapper.UserMapper;
import vn.com.linkjob.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(CreateUserRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        User newUser = userMapper.toUser(request);
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponseDTO(userRepository.save(newUser));
    }

    public ResultPaginationDTO getAllUser(Pageable pageable, Specification<User> spec) {
        Page<User> userPages = userRepository.findAll(spec, pageable);
        List<UserResponseDTO> result = userPages.getContent().stream()
                .map(userMapper::toUserResponseDTO)
                .toList();

        return ResultPaginationDTO.builder()
                .meta(Meta.builder()
                        .pageSize(userPages.getSize())
                        .page(userPages.getNumber() + 1)
                        .total(userPages.getTotalElements())
                        .pages(userPages.getTotalPages())
                        .build())
                .result(result)
                .build();
    }

    public UserResponseDTO getUserById(long id) {
        return userMapper.toUserResponseDTO(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST))
        );
    }

    public UserResponseDTO updateUser(long id, UpdateUserRequestDTO request) {
        User oldUser = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
        userMapper.updateUser(oldUser, request);

        return userMapper.toUserResponseDTO(userRepository.save(oldUser));
    }

    public void deleteUser(long id) {
        User deleteUser = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
        userRepository.delete(deleteUser);
    }

    public User getUserForLogin(String username) {
        return userRepository.findUserByEmail(username).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXIST)
        );
    }

    public void updateRefreshToken(String email, String refreshToken) {
        User currentUser = getUserForLogin(email);
        currentUser.setRefreshToken(refreshToken);

        userRepository.save(currentUser);
    }
}
