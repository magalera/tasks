package com.crud.tasks;

import com.crud.tasks.config.AdminConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigsTestSuite {

    @Autowired
    private AdminConfig adminConfig;

    @Test
    public void testGetAdminConfig() {
        // Given

        // When
        String mail = adminConfig.getAdminMail();

        // Then
        Assert.assertNotNull(mail);
    }
}