package org.selenide.exmples;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit.ScreenShooter;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

import java.util.Random;

import static com.codeborne.selenide.Selenide.getElement;
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


    @Test
    public void createNewBoard(){

        String name = generateName();

        TrelloMainPage mainPage = open(baseUrl, TrelloMainPage.class);
        TrelloAllBoardsPage boardsPage = mainPage.login(email, password);
        TrelloBoardPage boardPage = boardsPage.createNewBoard(name);
        boardPage.boardTitle(name).shouldBe(Condition.visible);
    }

    public String generateName() {
        int randomValue = new Random().nextInt();
        String name = "autotest" + randomValue;
        log.info("Generated name is {}", name);
        return name;
    }
}