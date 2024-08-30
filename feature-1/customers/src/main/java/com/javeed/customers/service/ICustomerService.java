package com.javeed.customers.service;

import com.javeed.customers.dto.*;

public interface ICustomerService {

    /**
     * @param customerDto - CustomerDto Object
     */
    void createCustomer(CustomerDto customerDto);

    /**
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Customer details is successful or not
     */
    boolean updateCustomer(CustomerDto customerDto);

    /**
     * @param mobileNumber
     * @return boolean indicating if the deletion of Customer details is successful or not
     */
    boolean deleteCustomer(String mobileNumber);

    /**
     * @param mobileNumber
     * @return boolean indicating if the delete of Customer details is successful or not
     */
    CustomerDto fetchCustomer(String mobileNumber);
}
