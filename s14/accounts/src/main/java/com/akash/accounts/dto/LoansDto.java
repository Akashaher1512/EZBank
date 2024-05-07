package com.akash.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(
        name = "Loans",
        description = "Schema to hold loan information"
)
@Data
public class LoansDto {

    @NotEmpty(message = "Mobile Number can not be a null or empty")
    @Pattern(regexp = "($|[0-9]{10})",message = "Mobile Number must be 10 digit")
    @Schema(
            description = "Mobile Number of customer",
            example = "9988776655"
    )
    private String mobileNumber;

    @NotEmpty(message = "Loan Number can not be a null or empty")
    @Pattern(regexp = "($|[0-9]{12})",message = "Loan Number must be 12 digits")
    @Schema(
            description = "Loan Number of the customer",
            example = "998877006655"
    )
    private String loanNumber;

    @NotEmpty(message = "Loan Type can not be a null or empty")
    @Schema(
            description = "Type of the loan",
            example = "Home Loan"
    )
    private String loanType;

    @Positive(message = "Total Loan amount should be greater than zero")
    @Schema(
            description = "Total Loan amount",
            example = "1000000"
    )
    private int totalLoan;

    @PositiveOrZero(message ="Total amount paid should be equal or greater than zero")
    @Schema(
            description = "Total Loan amount paid",
            example = "10000"
    )
    private int amountPaid;

    @PositiveOrZero(message = "Outstanding amount should be equal or greater than zero")
    @Schema(
            description = "Outstanding Amount",
            example = "10000"
    )
    private int outstandingAmount;

}
