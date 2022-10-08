package com.valleon.rewardyourteacherapi.infrastructure.controllers;

import com.valleon.rewardyourteacherapi.service.payload.TransactionService;
import com.valleon.rewardyourteacherapi.service.payload.response.TransactionResponse;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<List<TransactionResponse>> getStudentTransactions(
            @RequestParam("offset") int offset,
            @RequestParam("pageSize") int pageSize
    ){
        return ResponseEntity.ok(transactionService.getStudentTransactions(offset,pageSize));
    }
    @GetMapping("/teacher")
    public ResponseEntity<List<TransactionResponse>> getTeacherTransactions(
            @RequestParam("offset") int offset,
            @RequestParam("pageSize") int pageSize
    ){
        return ResponseEntity.ok(transactionService.getTeacherTransactions(offset,pageSize));
    }
}
