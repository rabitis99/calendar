package com.example.calendarproject.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 일정 요청 DTO
 * 클라이언트에서 일정을 생성하거나 수정할 때 사용
 */
@Getter
@Setter
public class CalendarRequestDto {

    private String name;
    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    private String password;
    @NotBlank(message = "할일의 입력은 필수입니다.")
    @Size(max = 200,message = "할일의 최대입력은 200입니다")
    private String task;
    long userId;
}

