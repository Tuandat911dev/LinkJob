package vn.com.linkjob.controller;

import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.linkjob.domain.User;
import vn.com.linkjob.dto.paginate.ResultPaginationDTO;
import vn.com.linkjob.dto.user.CreateUserRequestDTO;
import vn.com.linkjob.dto.user.UpdateUserRequestDTO;
import vn.com.linkjob.dto.user.UserResponseDTO;
import vn.com.linkjob.service.UserService;
import vn.com.linkjob.util.annotation.ApiMessage;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {
    UserService userService;

    @PostMapping
    @ApiMessage("Create new user")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody CreateUserRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(request));
    }

    @GetMapping
    @ApiMessage("Get user with pagination, sort, filter")
    public ResponseEntity<ResultPaginationDTO> getAllUser(Pageable pageable,
                                                          @Filter Specification<User> spec) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getAllUser(pageable, spec));
    }

    @GetMapping("/{id}")
    @ApiMessage("Get user by id")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    @ApiMessage("Edit user")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable long id,
                                                      @RequestBody UpdateUserRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.updateUser(id, request));
    }

    @DeleteMapping("/{id}")
    @ApiMessage("Delete user")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
