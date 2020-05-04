package com.crud.tasks.trello.validator;


import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTestSuite {

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void validateCardContainsTestInName() {
        // Given
        TrelloCard card = new TrelloCard("test", "test", "top", "1");

        // When
        trelloValidator.validateCard(card);

        // Then
        // do nothing
    }

    @Test
    public void validateCard() {
        // Given
        TrelloCard card = new TrelloCard("asdf", "test", "top", "1");

        // When
        trelloValidator.validateCard(card);

        // Then
        // do nothing
    }

    @Test
    public void validateBoard() {
        // Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "list", false));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "something", trelloLists));

        // When
        List<TrelloBoard> actual = trelloValidator.validateBoard(trelloBoards);

        //Then
        assertEquals(1, actual.size());
    }

    @Test
    public void validateBoardContainsTestInName() {
        // Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "test_list", false));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "test", trelloLists));

        // When
        List<TrelloBoard> actual = trelloValidator.validateBoard(trelloBoards);

        //Then
        assertEquals(0, actual.size());
    }
}