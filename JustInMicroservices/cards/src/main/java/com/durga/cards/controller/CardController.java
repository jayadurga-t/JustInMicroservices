package com.durga.cards.controller;

import com.durga.cards.constants.CardConstants;
import com.durga.cards.dto.CardDto;
import com.durga.cards.dto.ResponseDto;
import com.durga.cards.serviceInt.ICardService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Validated
public class CardController {

    private ICardService iCardService;

    @PostMapping("/createCard")
    public ResponseEntity<ResponseDto> createCard(@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber){
        iCardService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
    }

    @GetMapping("/fetchCard")
    public ResponseEntity<ResponseDto> fetchCard(@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber){
        iCardService.fetchCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
    }

    @PutMapping("/updateCard")
    public ResponseEntity<ResponseDto> updateCard(@Valid @RequestBody CardDto cardDto){
        boolean isUpdated=iCardService.updateCard(cardDto);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/deleteCard")
    public ResponseEntity<ResponseDto> deleteCard(@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber){
        boolean isDeleted=iCardService.deleteCard(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_DELETE));
        }
    }
}
