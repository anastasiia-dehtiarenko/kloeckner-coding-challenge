package org.selenide.exmples;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class TrelloAllBoardsPage extends TrelloCommonPage{

    @FindBy(xpath = ".//a[@class='board-tile mod-add']")
    public SelenideElement createNewBoard;
    @FindBy(xpath = ".//*[@id='boardNewTitle']")
    public SelenideElement boardTitle;
    @FindBy(xpath = "//input[@class='primary wide js-submit']")
    public SelenideElement createBoardButton;

    public TrelloBoardPage createNewBoard(String name) {
        createNewBoard.shouldBe(Condition.visible).click();
        boardTitle.shouldBe(Condition.visible).setValue(name);
        createBoardButton.shouldBe(Condition.visible).click();
        return page(TrelloBoardPage.class);
    }
}
