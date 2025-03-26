package com.example.calendarproject.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserRequestDto {
    @NotBlank(message = "이름은 비워지면 안됩니다.")
    String name;
    @NotBlank(message = "이메일은 비워지면 안됩니다")
    @Email(message = "이메일형식으로 입력하셔야 합니다.")
    String email;
}
