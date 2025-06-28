package com.durga.loans.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class ResponseDto {

    private String statusCode;

    private String statusMsg;
}
