package vn.com.linkjob.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import vn.com.linkjob.domain.User;
import vn.com.linkjob.dto.user.CreateUserRequestDTO;
import vn.com.linkjob.dto.user.UpdateUserRequestDTO;
import vn.com.linkjob.dto.user.UserResponseDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(CreateUserRequestDTO request);

    @Mapping(source = "company.id", target = "company.id")
    @Mapping(source = "company.name", target = "company.name")
    UserResponseDTO toUserResponseDTO(User user);

    void updateUser(@MappingTarget User user, UpdateUserRequestDTO request);
}
