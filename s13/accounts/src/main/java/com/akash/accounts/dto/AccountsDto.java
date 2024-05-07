package com.akash.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Schema(
        name = "Account",
        description = "Schema to hold account information"
)
public class AccountsDto {

    @Schema(
            description = "Account Number of EZBank account",example = "0000000001"
    )
    @Pattern(regexp = "($|[0-9]{10})",message = "Account number must be 10  digits")
    private Long accountNumber;

    @Schema(
            description = "Account type of EZBank account",example = "Savings"
    )
    @NotEmpty(message = "Account type can not be null or empty")
    private String accountType;

    @Schema(
            description = "Branch Address of EZBank account",example = "123 NeyYork"
    )
    @NotEmpty(message = "Branch address can not be null or empty")
    private String branchAddress;
}
