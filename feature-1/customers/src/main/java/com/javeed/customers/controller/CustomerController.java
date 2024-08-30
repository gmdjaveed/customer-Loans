package com.javeed.customers.controller;

import com.javeed.customers.constants.*;
import com.javeed.customers.dto.*;
import com.javeed.customers.service.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Customer in CustomerLoans Applications",
        description = "CRUD REST APIs in Customer to CREATE, UPDATE, FETCH AND DELETE Customer details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class CustomerController {

    private ICustomerService iCustomerService;

    @Operation(
            summary = "Create Customer REST API",
            description = "REST API to create new Customer inside Customer Application"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    /*
    Ex: http://localhost:8080/api/create
    Method:Post
    Body:
    {
    "name": "Javeed",
    "email": "Javeed@nodomain.com",
    "mobileNumber": "1234567890"
    }
     */
    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        iCustomerService.createCustomer(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CustomerConstants.STATUS_201, CustomerConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch Customer details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    //Ex Request: http://localhost:8080/api/fetch?mobileNumber=1234567890
    @GetMapping(path = "/fetch")
    public ResponseEntity<CustomerDto> fetchCustomerDetails(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber) {
        CustomerDto customerDto = iCustomerService.fetchCustomer(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @Operation(
            summary = "Update Customer Details REST API",
            description = "REST API to update Customer details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    /*
    Ex: http://localhost:8080/api/update
        Method:Put
        Body:
        {
        "name": "Javeed Ghani",
        "email": "Javeed.ghani@nodomain.com",
        "mobileNumber": "1234567890"
        }
     */
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCustomerDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iCustomerService.updateCustomer(customerDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CustomerConstants.STATUS_200, CustomerConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CustomerConstants.STATUS_417, CustomerConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Customer Details REST API",
            description = "REST API to delete Customer details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    //Ex: http://localhost:8080/api/delete?mobileNumber=1234567890
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCustomerDetails(@RequestParam
                                                             @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                             String mobileNumber) {
        boolean isDeleted = iCustomerService.deleteCustomer(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CustomerConstants.STATUS_200, CustomerConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CustomerConstants.STATUS_417, CustomerConstants.MESSAGE_417_DELETE));
        }
    }

}
