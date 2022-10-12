package com.valleon.rewardyourteacherapi.service.payload.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailDetailsRequest {

    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;

}
