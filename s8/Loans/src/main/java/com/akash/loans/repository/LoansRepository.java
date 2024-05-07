package com.akash.loans.repository;

import com.akash.loans.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoansRepository extends JpaRepository<Loans,Long> {

    Optional<Loans> findByMobileNumber(String mobileNumer);

    Optional<Loans> findByLoanNumber(String loanNumber);

}