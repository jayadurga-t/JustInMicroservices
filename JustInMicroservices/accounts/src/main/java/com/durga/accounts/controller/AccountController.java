package com.durga.accounts.controller;

import com.durga.accounts.constants.AccountConstants;
import com.durga.accounts.dto.CustomerDto;
import com.durga.accounts.dto.ResponseDto;
import com.durga.accounts.serviceInt.IAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AccountController {

    private IAccountService iAccountService;

    @PostMapping("/createAccount")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @GetMapping("/fetchAccountDetails")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam @NotEmpty(message = "Mobile number can not be a null or empty") @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iAccountService.fetchAccountDetails(mobileNumber));
    }

    @PutMapping("/updateAccountDetails")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdated=iAccountService.updateAccountDetails(customerDto);

        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/deleteAccountDetails")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam @NotEmpty(message = "Mobile number can not be a null or empty") @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber){
        boolean isdeleted=iAccountService.deleteAccount(mobileNumber);
        if(isdeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_DELETE));
        }
    }
}
