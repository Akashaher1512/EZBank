package com.akash.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold customer and account information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer",example = "Akash Aher"
    )
    @NotEmpty(message = "Name Can Not Be Null Or Empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30 ")
    private String name;

    @Schema(
            description = "Email of the customer",example = "akash@gmail.com"
    )
    @NotEmpty(message = "Name Can Not Be Null Or Empty")
    @Email(message = "Email address should be valid value")
    private String email;

    @Schema(
            description = "Mobile Number of the customer",example = "9080706050"
    )
    @Pattern(regexp = "($|[0-9]{10})",message = "Mobile number must be 10  digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of the customer"
    )
    private AccountsDto accountsDto;
}
