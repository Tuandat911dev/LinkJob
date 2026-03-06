package vn.com.linkjob.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import vn.com.linkjob.domain.User;
import vn.com.linkjob.dto.CreateUserRequestDTO;
import vn.com.linkjob.dto.UpdateUserRequestDTO;
import vn.com.linkjob.dto.UserResponseDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(CreateUserRequestDTO request);

    UserResponseDTO toUserResponseDTO(User user);

    void updateUser(@MappingTarget User user, UpdateUserRequestDTO request);
}
