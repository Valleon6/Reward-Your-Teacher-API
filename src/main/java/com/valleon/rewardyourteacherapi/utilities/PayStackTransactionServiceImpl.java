package com.valleon.rewardyourteacherapi.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.valleon.rewardyourteacherapi.usecase.services.PaymentService;
import com.valleon.rewardyourteacherapi.usecase.payload.request.PayStackTransactionRequest;
import com.valleon.rewardyourteacherapi.usecase.payload.response.PayStackTransactionResponse;
import lombok.AllArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStreamReader;


@Service
@AllArgsConstructor
@Transactional
public class PayStackTransactionServiceImpl implements PaymentService {

    /**
     * PayStack secret key
     */
    @Value("${paystack_secret_key:paystack}")
    private String payStackSecretKey;
    private final PayStackVerification payStackVerification;
    public PayStackTransactionResponse initTransaction(PayStackTransactionRequest request) throws Exception {
        PayStackTransactionResponse PayStackTransactionResponse;
        try {
            // Converts object to json
            Gson gson = new Gson();
            // add payStack charges to the amount
            StringEntity postingString = new StringEntity(gson.toJson(request));

            //Consuming paystack api using web client
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost("https://api.paystack.co/transaction/initialize");
            post.setEntity(postingString);
            post.addHeader("Content-type", "application/json");
            post.addHeader("Authorization", "Bearer "+payStackSecretKey);
            StringBuilder result = new StringBuilder();
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

            } else {
                throw new AuthenticationException("Error Occurred while initializing transaction");
            }
            ObjectMapper mapper = new ObjectMapper();

            PayStackTransactionResponse = mapper.readValue(result.toString(), PayStackTransactionResponse.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Failure initializing payStack transaction");
        }
        PayStackVerification payStackVerification1 = payStackVerification.verifyTransaction(PayStackTransactionResponse.getData().getReference());
        if(payStackVerification1 == null){
            throw new Exception("Paystack verification failed");
        }
        return PayStackTransactionResponse;
    }
}
