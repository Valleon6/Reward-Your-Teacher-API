package com.valleon.rewardyourteacherapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.valleon.rewardyourteacherapi.domain.entities.School;
import com.valleon.rewardyourteacherapi.usecase.services.impl.SchoolServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class RewardYourTeacherJavaApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(RewardYourTeacherJavaApiApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(SchoolServiceImpl schoolServiceimpl){
        if(schoolServiceimpl.getSchoolCount() < 1){
            return args -> {
                //read json and write to DB
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<School>> typeReference = new TypeReference<>() {
                };
                InputStream inputStream = TypeReference.class.getResourceAsStream("/Schools.json");
                try{
                    List<School> schools = mapper.readValue(inputStream, typeReference);
                    schoolServiceimpl.saveSchool(schools);
                } catch (IOException e){
                    throw new RuntimeException("Cannot save school");
                }

            };
        }
        return null;
    }

}
