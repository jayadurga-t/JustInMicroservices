package com.durga.loans.controller;

import com.durga.loans.constants.LoanConstants;
import com.durga.loans.dto.LoanDto;
import com.durga.loans.dto.ResponseDto;
import com.durga.loans.serviceInt.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class LoanController {

    private ILoanService iLoanService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam String mobileNumber){
        iLoanService.createLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<ResponseDto> fetchLoan(@RequestParam String mobileNumber){
        iLoanService.fetchLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoan(@RequestBody LoanDto loanDto){
        boolean isUpdated = iLoanService.updateLoan(loanDto);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoan(@RequestParam String mobileNumber){
        boolean isdeleted = iLoanService.deleteLoan(mobileNumber);
        if(isdeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_DELETE));
        }
    }

}
