package com.valleon.rewardyourteacherapi.service.payload.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * the response returned from the paystack verification
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VerifyTransactionResponse {
    private String status;

    private String message;
    private Data data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public class Data {
        private int id;
        private String domain;
        private String status;
        private String reference;
        private int amount;
        private String message;
        private String gateway_response;
        private String paid_at;
        private String channel;
        private String NGN;

    }

    public VerifyTransactionResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
