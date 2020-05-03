package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    TrelloMapper trelloMapper = new TrelloMapper();

    private List<TrelloList> createTrelloLists() {
        List<TrelloList> lists = new ArrayList<>();
        TrelloList list1 = new TrelloList("20", "ListHomework", true);
        TrelloList list2 = new TrelloList("22", "ListCooking", false);
        TrelloList list3 = new TrelloList("23", "ListSinging", false);
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        return lists;
    }

    private List<TrelloListDto> createTrelloListsDto() {
        List<TrelloListDto> listsDto = new ArrayList<>();
        TrelloListDto listDto1 = new TrelloListDto("10", "ListToDo", false);
        TrelloListDto listDto2 = new TrelloListDto("11", "ListInProgress", false);
        TrelloListDto listDto3 = new TrelloListDto("11", "ListDone", true);
        listsDto.add(listDto1);
        listsDto.add(listDto2);
        listsDto.add(listDto3);
        return listsDto;
    }

    private void compare(TrelloListDto trelloListDto, TrelloList trelloList) {
        assertEquals(trelloListDto.getId(), trelloList.getId(), trelloListDto + " not equal to " + trelloList);
        assertEquals(trelloListDto.getName(), trelloList.getName(), trelloListDto + " not equal to " + trelloList);
        assertEquals(trelloListDto.isClosed(), trelloList.isClosed(), trelloListDto + " not equal to " + trelloList);
    }

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloListDto> listsDto = createTrelloListsDto();

        List<TrelloBoardDto> boardsDto = new ArrayList<>();
        TrelloBoardDto boardDto1 = new TrelloBoardDto("1", "Monday", listsDto);
        TrelloBoardDto boardDto2 = new TrelloBoardDto("2", "Wednesday", listsDto);
        TrelloBoardDto boardDto3 = new TrelloBoardDto("3", "Friday", listsDto);
        boardsDto.add(boardDto1);
        boardsDto.add(boardDto2);
        boardsDto.add(boardDto3);

        //When
        List<TrelloBoard> boards = trelloMapper.mapToBoards(boardsDto);

        //Then
        assertEquals(boardsDto.size(), boards.size());
        for (int i = 0; i < boardsDto.size(); i++) {
            TrelloBoardDto trelloBoardDto = boardsDto.get(i);
            TrelloBoard actualBoard = boards.get(i);
            assertEquals(actualBoard.getName(), trelloBoardDto.getName());
            assertEquals(actualBoard.getId(), trelloBoardDto.getId());

            for (int j = 0; j < listsDto.size(); j++) {
                compare(listsDto.get(j), actualBoard.getLists().get(j));
            }
        }
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloList> lists = createTrelloLists();

        List<TrelloBoard> boards = new ArrayList<>();
        TrelloBoard board1 = new TrelloBoard("1", "Monday", lists);
        TrelloBoard board2 = new TrelloBoard("2", "Wednesday", lists);
        TrelloBoard board3 = new TrelloBoard("3", "Friday", lists);
        boards.add(board1);
        boards.add(board2);
        boards.add(board3);

        //When
        List<TrelloBoardDto> actual = trelloMapper.mapToBoardsDto(boards);

        //Then
        assertEquals(boards.size(), actual.size());
        for (int i = 0; i < boards.size(); i++) {
            TrelloBoard board = boards.get(i);
            TrelloBoardDto actualDto = actual.get(i);
            assertEquals(board.getName(), actualDto.getName());
            assertEquals(board.getId(), actualDto.getId());

            for (int j = 0; j < lists.size(); j++) {
                compare(actualDto.getLists().get(j), lists.get(j));
            }
        }
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> listsDto = createTrelloListsDto();

        //When
        List<TrelloList> actual = trelloMapper.mapToList(listsDto);

        //Then
        assertEquals(listsDto.size(), actual.size());
        for (int i = 0; i < listsDto.size(); i++) {
            compare(listsDto.get(i), actual.get(i));
        }
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> lists = createTrelloLists();

        //When
        List<TrelloListDto> actual = trelloMapper.mapToListDto(lists);

        //Then
        assertEquals(lists.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            compare(actual.get(i), lists.get(i));
        }
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard card = new TrelloCard("Clean flat", "Vacuum and clean the floor.", "top", "10");

        //When
        TrelloCardDto actual = trelloMapper.mapToCardDto(card);

        //Then
        assertEquals(card.getName(), actual.getName());
        assertEquals(card.getDescription(), actual.getDescription());
        assertEquals(card.getPos(), actual.getPos());
        assertEquals(card.getListId(), actual.getIdList());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto cardDto = new TrelloCardDto("Learn a song", "Despacito", "top", "23");

        //When
        TrelloCard actual = trelloMapper.mapToCard(cardDto);

        //Then
        assertEquals(cardDto.getName(), actual.getName());
        assertEquals(cardDto.getDescription(), actual.getDescription());
        assertEquals(cardDto.getPos(), actual.getPos());
        assertEquals(cardDto.getIdList(), actual.getListId());
    }
}