package jp.co.volante.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.volante.user.dto.RequestUserDto;
import jp.co.volante.user.dto.ResponseUserDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<ResponseUserDto> getUser() {
        return userService.getUser(0, 10);
    }

    @GetMapping("/id/{id}")
    public ResponseUserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/uid/{uid}")
    public ResponseUserDto getUserByUid(@PathVariable String uid) {
        return userService.getUserByUid(uid);
    }

    @PostMapping
    public ResponseUserDto createUser(@RequestBody RequestUserDto requestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = (String) authentication.getPrincipal();
        return userService.createUser(requestDto, uid);
    }

    // @PutMapping("/{id}")
    // public UserResponseDto updateUser(
    // @PathVariable Long id,
    // @RequestBody UserRequestDto requestDto) {
    // return userService.updateUser(id, requestDto);
    // }

    // @DeleteMapping("/{id}")
    // public void deleteUser(@PathVariable Long id) {
    // userService.deleteUser(id);
    // }
}
