package com.javeed.customer.dto;

import io.swagger.v3.oas.annotations.media.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(
            description = "Customer ID", example = "1000001"
    )
    private Long customerId;

    @Schema(
            description = "Customer name", example = "Javeed Ghani"
    )
    @NotEmpty(message = "Customer name can not be a null or empty")
    private String name;

    @Schema(
            description = "Email address of the customer", example = "javeed@nodomain.com"
    )
    @NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Schema(
            description = "Mobile Number of the customer", example = "1234567890"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

}
