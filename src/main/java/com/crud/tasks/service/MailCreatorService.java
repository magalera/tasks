package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello account");
        functionality.add("Application allows sending tasks to Trello");
        
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
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("created-trello-card-mail.html", context);
    }
}
