package com.javeed.customers.service.impl;

import com.javeed.customers.dto.*;
import com.javeed.customers.entity.*;
import com.javeed.customers.exception.*;
import com.javeed.customers.mapper.*;
import com.javeed.customers.repository.*;
import com.javeed.customers.service.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {

    private CustomerRepository customerRepository;

    /**
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createCustomer(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());

        //Ensure the same customer does not exist
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException(
                    String.format("Customer is already registered with the given mobile#%s", customer.getMobileNumber()));
        }
        customerRepository.save(customer);
    }

    /**
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Customer details is successful or not
     */
    @Override
    public boolean updateCustomer(CustomerDto customerDto) {
        boolean isUpdated = false;
        if (Objects.nonNull(customerDto)) {
            String mobileNumber = customerDto.getMobileNumber();
            Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber)
            );
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * @param mobileNumber
     * @return boolean indicating if the deletion of Customer details is successful or not
     */
    @Override
    public boolean deleteCustomer(String mobileNumber) {
        customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber)
        );
        customerRepository.deleteByMobileNumber(mobileNumber);
        return true;
    }

    /**
     * @param mobileNumber
     * @return boolean indicating if the delete of Customer details is successful or not
     */
    @Override
    public CustomerDto fetchCustomer(String mobileNumber) {
        if (Objects.nonNull(mobileNumber)) {
            Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber)
            );
            return CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        } else {
            throw new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber);
        }
    }
}
