//package com.valleon.rewardyourteacherapi.utilities;
//
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.Random;
//
//@Service
//@AllArgsConstructor
//public class Utility {
//    public Long generateUniqueId() {
//
//        String chars = "0123456789";
//        Random rnd = new Random();
//        StringBuilder sb = new StringBuilder(16);
//        for (int i = 0; i < 16; i++)
//            sb.append(chars.charAt(rnd.nextInt(chars.length())));
//        return Long.parseLong(sb.toString());
//
//
////        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
////                + "lmnopqrstuvwxyz!@#$%&";
////        Random rnd = new Random();
////        StringBuilder sb = new StringBuilder(16);
////        for (int i = 0; i < 16; i++)
////            sb.append(chars.charAt(rnd.nextInt(chars.length())));
////        return sb.toString();
//    }
//}
