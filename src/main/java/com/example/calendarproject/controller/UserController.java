package com.example.calendarproject.controller;


import com.example.calendarproject.dto.UserRequestDto;
import com.example.calendarproject.dto.UserResponseDto;
import com.example.calendarproject.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@AllArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser( @RequestBody @Valid UserRequestDto userRequestDto ){

        return new ResponseEntity<>(userService.createUser(userRequestDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCUser (
             @PathVariable @PositiveOrZero(message ="음수나 0값은 입력하시면 안됩니다.") Long id){

        return new ResponseEntity<>(userService.deleteUser(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
             @PathVariable @PositiveOrZero(message ="음수나 0값은 입력하시면 안됩니다.")Long id,
             @RequestBody @Valid UserRequestDto userRequestDto){
        return new ResponseEntity<>(userService.updateUser(id,userRequestDto),HttpStatus.OK);
    }
}
