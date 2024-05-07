package com.akash.loans.service.impl;

import com.akash.loans.constants.LoanConstants;
import com.akash.loans.dto.LoansDto;
import com.akash.loans.entity.Loans;
import com.akash.loans.exception.LoanAlreadyExistsException;
import com.akash.loans.exception.ResourceNotFountException;
import com.akash.loans.mapper.LoansMapper;
import com.akash.loans.repository.LoansRepository;
import com.akash.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    @Autowired
    private LoansRepository loansRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);

        if (optionalLoans.isPresent()){
            throw new LoanAlreadyExistsException("Loan Already registered with given mobile number : "+mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();

        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);

        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFountException("Loan", "Mobile Number", mobileNumber));

        return LoansMapper.mapToLoansDto(loans,new LoansDto());
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(()->new ResourceNotFountException("Loan","Loans Number",loansDto.getLoanNumber()));
        LoansMapper.mapToLoans(loansDto,loans);

        loansRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFountException("Loan", "mobile number", mobileNumber));

        loansRepository.deleteById(loans.getId());
        return true;
    }
}
