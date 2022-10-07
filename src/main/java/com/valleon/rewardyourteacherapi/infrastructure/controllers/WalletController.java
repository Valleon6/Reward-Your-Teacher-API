package com.valleon.rewardyourteacherapi.infrastructure.controllers;

import com.valleon.rewardyourteacherapi.service.payload.WalletService;
import com.valleon.rewardyourteacherapi.service.payload.request.FundWalletRequest;

import com.valleon.rewardyourteacherapi.service.payload.response.PaymentResponse;
import com.valleon.rewardyourteacherapi.service.payload.response.WalletResponse;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/fund")
    public ResponseEntity<PaymentResponse> fundWallet(@RequestBody FundWalletRequest amount) throws Exception {
        return ResponseEntity.ok(walletService.fundWallet(amount));
    }

    @GetMapping("/student/balance")
    public ResponseEntity<WalletResponse> getStudentBalance(){
        return  ResponseEntity.ok(walletService.getStudentWalletBalance());
    }

    @GetMapping("/teacher/teacher")
    public ResponseEntity<WalletResponse> getTeacherBalance(){
        return ResponseEntity.ok(walletService.getTeacherWalletResponse());
    }

}
