package com.akash.accounts.service;

import com.akash.accounts.dto.AccountsDto;
import com.akash.accounts.dto.CustomerDto;


public interface IAccountsService {

    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccountDetails(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);

    boolean updateCommunicationStatus(Long accountNumber);
}
