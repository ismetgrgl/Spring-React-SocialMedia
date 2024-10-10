package com.socialmedia.controller;

import com.socialmedia.config.JwtManager;
import com.socialmedia.dto.request.FindAllByUsernameRequestDto;
import com.socialmedia.dto.request.UserLoginRequestDto;
import com.socialmedia.dto.request.UserSaveRequestDto;
import com.socialmedia.dto.request.UserUpdateRequestDto;
import com.socialmedia.dto.response.ResponseDto;
import com.socialmedia.dto.response.SearchUserResponseDto;
import com.socialmedia.entity.User;
import com.socialmedia.exception.AuthException;
import com.socialmedia.exception.ErrorType;
import com.socialmedia.service.UserService;
import com.socialmedia.views.VwSearchUser;
import com.socialmedia.views.VwUserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
public class UserController {
    private final UserService userService;
    private final JwtManager jwtManager;
    @PostMapping("/register")
    @CrossOrigin("*")
    public ResponseEntity<ResponseDto<Boolean>> save(@RequestBody UserSaveRequestDto dto){
        userService.save(dto);
        return ResponseEntity.ok(ResponseDto.<Boolean>builder()
                        .code(200)
                        .message("Kullanıcı başarı ile kayıt edildi.")
                        .data(true)
                .build());
    }

    @PostMapping("/login")
    @CrossOrigin("*")
    public ResponseEntity<ResponseDto<String>> login(@RequestBody UserLoginRequestDto dto){
        Optional<User> user = userService.login(dto);
        if(user.isEmpty())
            throw new AuthException(ErrorType.BAD_REQUEST_USERNAME_OR_PASSWORD_ERROR);
        String token = jwtManager.createToken(user.get().getId());
        return ResponseEntity.ok(ResponseDto.<String>builder()
                        .code(200)
                        .message("Başarılı şekilde giriş yapıldı")
                        .data(token)
                .build());
    }

    @PostMapping("/update")
    @CrossOrigin("*")
    public ResponseEntity<ResponseDto> save(@RequestBody UserUpdateRequestDto dto){
        userService.editProfile(dto);
        return ResponseEntity.ok(ResponseDto.builder()
                .code(200)
                .message("Kullanıcı başarı ile güncellendi .")
                .data(true)
                .build());
    }

    @GetMapping("/search")
    @CrossOrigin("*")
    public ResponseEntity<List<SearchUserResponseDto>> getUserList(String userName){
        return ResponseEntity.ok(userService.search(userName));
    }

    @GetMapping("/get-profile")
    @CrossOrigin("*")
    public ResponseEntity<ResponseDto<VwUserProfile>> getProfile(String token){
        return ResponseEntity.ok(ResponseDto.<VwUserProfile>builder()
                        .message("profile bilgileri")
                        .code(200)
                        .data(userService.getProfileByToken(token))
                .build());
    }



    @PostMapping("/search-user")
    public ResponseEntity<ResponseDto<List<VwSearchUser>>> findAllByUserName(@RequestBody FindAllByUsernameRequestDto dto){
        return  ResponseEntity.ok(
                ResponseDto.<List<VwSearchUser>>builder()
                        .code(200)
                        .message("kullanıcılar getirildi.")
                        .data(userService.getAllByUserName(dto))
                        .build()
        );
    }

}
