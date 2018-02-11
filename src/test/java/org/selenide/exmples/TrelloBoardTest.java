package org.selenide.exmples;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import java.util.Random;
import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class TrelloBoardTest {

//**********************************************************************************************************
    @Rule
    public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests().succeededTests();

    @After
    public void close() {
        Selenide.close();
    }
//**********************************************************************************************************

    String baseUrl = "https://trello.com/";
    String email = "anastasiia.de2011@gmail.com";
    String password = "TrelloTest66";


    public String generateName() {
        int randomValue = new Random().nextInt();
        String name = "autotest" + randomValue;
        log.info("Generated name is {}", name);
        return name;
    }

    @Test
    public void createNewBoard(){

        String boardName = generateName();

        TrelloMainPage mainPage = open(baseUrl, TrelloMainPage.class);
        TrelloAllBoardsPage boardsPage = mainPage.login(email, password);
        TrelloBoardPage boardPage = boardsPage.createNewBoard(boardName);
        boardPage.boardTitle(boardName).shouldBe(Condition.visible);
        boardPage.deleteCreatedBoard();
    }

    @Test
    public void createNewList(){

        String boardName = generateName();
        String listName = generateName();

        TrelloMainPage mainPage = open(baseUrl, TrelloMainPage.class);
        TrelloAllBoardsPage boardsPage = mainPage.login(email, password);
        TrelloBoardPage boardPage = boardsPage.createNewBoard(boardName);
        boardPage.createNewList(listName).shouldHave(Condition.text(listName));
        boardPage.deleteCreatedBoard();
    }

    @Test
    public void createNewCardWithLabel(){

        String boardName = generateName();
        String listName = generateName();
        String cardName = generateName();
        String label = "yellow";

        TrelloMainPage mainPage = open(baseUrl, TrelloMainPage.class);
        TrelloAllBoardsPage boardsPage = mainPage.login(email, password);
        TrelloBoardPage boardPage = boardsPage.createNewBoard(boardName);
        boardPage.createNewList(listName).shouldHave(Condition.text(listName));
        boardPage.addNewCardToList(cardName,label).shouldHave(Condition.text(cardName));
        boardPage.deleteCreatedBoard();
    }

    @Test
    public void moveCardToAnotherList(){

        String boardName = generateName();
        String listName1 = generateName();
        String listName2 = generateName();
        String cardName = generateName();
        String label = "red";

        TrelloMainPage mainPage = open(baseUrl, TrelloMainPage.class);
        TrelloAllBoardsPage boardsPage = mainPage.login(email, password);
        TrelloBoardPage boardPage = boardsPage.createNewBoard(boardName);
        boardPage.createNewList(listName1).shouldHave(Condition.text(listName1));
        boardPage.addNewCardToList(cardName,label).shouldHave(Condition.text(cardName));
        boardPage.createNewList(listName2);
        boardPage.moveCardToAnotherList(cardName, listName2);
        boardPage.deleteCreatedBoard();


    }

}
