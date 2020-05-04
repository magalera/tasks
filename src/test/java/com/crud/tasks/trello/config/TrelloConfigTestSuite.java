package com.crud.tasks.trello.config;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloConfigTestSuite {

    @Autowired
    private TrelloConfig trelloConfig;

    @Test
    public void testGetTrelloConfig() {
        // Given

        // When
        String trelloUserName = trelloConfig.getTrelloUserName();
        String trelloApiEndpoint = trelloConfig.getTrelloApiEndpoint();
        String trelloAppKey = trelloConfig.getTrelloAppKey();
        String trelloToken = trelloConfig.getTrelloToken();

        // Then
        Assert.assertNotNull(trelloUserName);
        Assert.assertNotNull(trelloApiEndpoint);
        Assert.assertNotNull(trelloAppKey);
        Assert.assertNotNull(trelloToken);
    }

}