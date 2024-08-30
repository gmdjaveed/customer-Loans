package com.javeed.customers.mapper;

import com.javeed.customers.dto.*;
import com.javeed.customers.entity.*;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setCustomerId(customer.getCustomerId());//OK to pass the PK if available for view.
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        //customer.setCustomerId(customerDto.getCustomerId()); //Not OK to update the PK to null during update operation!
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
