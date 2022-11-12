package com.valleon.rewardyourteacherapi.infrastructure.configuration.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "springdoc.swagger-ui.enabled", havingValue = "true", matchIfMissing = true)
public class SwaggerConfig {

    @Value("${api.info.title: Reward Your Teacher}")
    private String title;

    @Value("${api.info.description: api.info.description}")
    private String description;

    @Value("${api.info.version: v1}")
    private String version;

    @Value("${api.info.term-of-service: Terms}")
    private String termOfService;

    @Value("${api.info.contact.name: Valentine Ekechukwu}")
    private String contactName;

    @Value("${api.info.contact.email: val.ekechukwu@gmail.com}")
    private String contactEmail;

    @Value("${api.info.contact.url: github/valleon6}")
    private String contactUrl;

    @Value("${api.info.license.name: api.info.license.name}")
    private String licenseName;

    @Value("${api.info.license.url: api.info.license.url")
    private String licenseUrl;


    @Bean
    public OpenAPI productApi() {
        return new OpenAPI()
                .info(getApiInfo());
    }


    private Info getApiInfo() {
        Contact contact = new Contact().name(contactName).email(contactEmail).url(contactUrl);
        License license = new License().name(licenseName).url(licenseUrl);

        return new Info()
                .title(title)
                .description(description)
                .version(version)
                .contact(contact)
                .license(license);
    }
}
