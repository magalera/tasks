package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://magalera.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("admin_mail", adminConfig.getAdminMail());
        context.setVariable("mail_subject", adminConfig.getMailSubject());
        context.setVariable("company_owner_name", adminConfig.getCompanyOwnerName());
        context.setVariable("company_owner_surname", adminConfig.getCompanyOwnerSurname());
        context.setVariable("company_owner_email", adminConfig.getCompanyEmail());
        context.setVariable("company_owner_address_street", adminConfig.getCompanyAddressStreet());
        context.setVariable("company_owner_address_number", adminConfig.getCompanyAddressStreetNumber());
        return templateEngine.process("mail/created-trello-card-mail.html", context);
    }
}
