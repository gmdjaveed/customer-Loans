package com.javeed.loans.service.impl;

import com.javeed.loans.constants.*;
import com.javeed.loans.dto.*;
import com.javeed.loans.entity.*;
import com.javeed.loans.exception.*;
import com.javeed.loans.mapper.*;
import com.javeed.loans.repository.*;
import com.javeed.loans.service.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@AllArgsConstructor
@Service
public class LoansServiceImpl implements ILoansService{

    private LoansRepository loansRepository;

    /**
     * Loan created with pre-defined types and amount.
     *
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber "+mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new loan details
     */
    private Loans createNewLoan(String mobileNumber) {
        //Generate Loan Id within range 1B to 1.9B
        Loans newLoan = new Loans();
        Long loanNumber = 100_000_000_000L + new Random().nextInt(900_000_000);
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanNumber(String.valueOf(loanNumber));
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    /**
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        return LoansMapper.mapToLoansDto(loans, new LoansDto());
    }

    /**
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    @Override
    public boolean updateLoan(LoansDto loansDto) {
        String mobileNumber = loansDto.getMobileNumber();
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loansRepository.save(LoansMapper.mapToLoans(loansDto, loans));
        return true;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of loan details is successful or not
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );

        loansRepository.deleteById(loans.getLoanId());
        return true;
    }
}
