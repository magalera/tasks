package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {
    @Value("${admin.mail}")
    private String adminMail;

    @Value("${admin.name}")
    private String adminName;

    @Value("${info.app.administrator.email}")
    private String companyEmail;

    @Value("${info.app.administrator.adress.street}")
    private String companyAddressStreet;

    @Value("${info.app.administrator.adress.number}")
    private String companyAddressStreetNumber;

    @Value("${info.app.owner.name}")
    private String companyOwnerName;

    @Value("${info.app.owner.surname}")
    private String companyOwnerSurname;

    @Value("${info.app.name}")
    private String mailSubject;




}

