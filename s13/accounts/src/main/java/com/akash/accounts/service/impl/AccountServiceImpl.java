package com.akash.accounts.service.impl;

import com.akash.accounts.constatnts.AccountsConstants;
import com.akash.accounts.controller.CustomerController;
import com.akash.accounts.dto.AccountsDto;
import com.akash.accounts.dto.AccountsMesDto;
import com.akash.accounts.dto.CustomerDto;
import com.akash.accounts.entity.Accounts;
import com.akash.accounts.entity.Customer;
import com.akash.accounts.exception.CustomerAlreadyExistsException;
import com.akash.accounts.exception.ResourceNotFoundException;
import com.akash.accounts.mapper.AccountsMapper;
import com.akash.accounts.mapper.CustomerMapper;
import com.akash.accounts.repository.AccountsRepository;
import com.akash.accounts.repository.CustomerRepository;
import com.akash.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    private StreamBridge streamBridge;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());

        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer Already Registered With Given mobileNumber : "+customerDto.getMobileNumber());
        }

        /*commented because we added jpa auditing in application so it happens automatically*/
        /*customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");*/
        Customer savedCustomer = customerRepository.save(customer);
        Accounts newAccount = createNewAccount(savedCustomer);

        Accounts savedAccount = accountsRepository.save(newAccount);

        sendCommunication(savedAccount,savedCustomer);
    }

    private void sendCommunication(Accounts accounts, Customer customer){
        AccountsMesDto accountsMesDto = new AccountsMesDto(accounts.getAccountNumber(), customer.getName(), customer.getEmail(), customer.getMobileNumber());
        log.info("sending communication request for the details: {}",accountsMesDto);
        var result =streamBridge.send("sendCommunication-out-0",accountsMesDto);
        log.info("Is the communication request successfully processed: {}",result);
    }

    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccountNumber = 10000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        /*commented because we added jpa auditing in application so it happens automatically*/
       /* newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");*/
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccountDetails(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("customer", "mobileNumber", mobileNumber));

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(accounts, new AccountsDto());

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());

        customerDto.setAccountsDto(accountsDto);

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();

        if(accountsDto != null){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(() -> new ResourceNotFoundException("account", "accountNumber", accountsDto.getAccountNumber().toString()));

            AccountsMapper.mapToAccounts(accountsDto,accounts);

            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();

            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("customer", "mobileNumber", customerId.toString()));

            CustomerMapper.mapToCustomer(customerDto,customer);

            customerRepository.save(customer);
            isUpdated=true;

        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    @Override
    public boolean updateCommunicationStatus(Long accountNumber) {
        boolean isUpdated =false;
        if(accountNumber != null){
            Accounts account= accountsRepository.findById(accountNumber).orElseThrow(
                    ()-> new ResourceNotFoundException("Account","AccountNumber",accountNumber.toString())
            );

            account.setCommunicationSw(true);
            accountsRepository.save(account);
            isUpdated = true;
        }
        return isUpdated;
    }

}








