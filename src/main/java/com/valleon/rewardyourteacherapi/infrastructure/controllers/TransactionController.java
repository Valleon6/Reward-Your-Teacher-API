package com.valleon.rewardyourteacherapi.infrastructure.controllers;

import com.valleon.rewardyourteacherapi.service.payload.TransactionService;
import com.valleon.rewardyourteacherapi.service.payload.response.ApiResponse;
import com.valleon.rewardyourteacherapi.service.payload.response.TransactionResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/student")
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getStudentTransactions(
            @RequestParam("offset") int offset,
            @RequestParam("pageSize") int pageSize
    ){
        List<TransactionResponse> transaction = transactionService.getStudentTransactions(offset,pageSize);
        return new ResponseEntity<>(new ApiResponse<>("success",true,transaction), HttpStatus.OK);    }
    @GetMapping("/teacher")
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getTeacherTransactions(
            @RequestParam("offset") int offset,
            @RequestParam("pageSize") int pageSize
    ){
        List<TransactionResponse> transaction = transactionService.getTeacherTransactions(offset,pageSize);
        return new ResponseEntity<>(new ApiResponse<>("success",true,transaction),HttpStatus.OK);
    }
}
