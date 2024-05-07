package com.akash.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "schema to hold error response information"
)
public class ErrorResponseDto {

    @Schema(
            description = "Api path invoke by client"
    )
    private String apiPath;
    @Schema(
            description = "Error Code representing the error happened"
    )
    private HttpStatus errorCode;
    @Schema(
            description = "Error Message representing the error happened"
    )
    private String errorMessage;
    @Schema(
            description = "Time representing when th error happened"
    )
    private LocalDateTime errorTime;

}
