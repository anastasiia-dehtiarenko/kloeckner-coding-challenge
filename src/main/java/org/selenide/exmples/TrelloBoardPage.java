package org.selenide.exmples;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


public class TrelloBoardPage{

    @FindBy(xpath = ".//a[@class='board-menu-navigation-item-link js-open-more']") public SelenideElement openMoreLink;
    @FindBy(xpath = ".//a[@class='board-menu-navigation-item-link js-close-board']") public SelenideElement closeBoardButton;
    @FindBy(xpath = ".//input[@class='js-confirm full negate' and @value='Close']") public SelenideElement confirmCloseButton;
    @FindBy(xpath = ".//a[@class='quiet js-delete' and text()='Permanently Delete Board…']") public SelenideElement deleteBoard;
    @FindBy(xpath = ".//input[@class='js-confirm full negate' and @value='Delete']") public SelenideElement confirmDeleteBoard;
    @FindBy(xpath = ".//input[@class='list-name-input']") public SelenideElement addListName;
    @FindBy(xpath = ".//input[@class='primary mod-list-add-button js-save-edit' and @value='Save']") public SelenideElement saveListButton;
    @FindBy(xpath = ".//a[@class='open-card-composer js-open-card-composer']") public SelenideElement addCard;
    @FindBy(xpath = ".//*[@class='list-card-composer-textarea js-card-title']") public SelenideElement addCardsText;
    @FindBy(xpath = ".//a[@class='icon-lg icon-overflow-menu-horizontal dark-background-hover js-cc-menu']") public SelenideElement showCardsMenu;
    @FindBy(xpath = ".//a[@class='js-label-selector']") public SelenideElement chooseLabelsOption;
    @FindBy(xpath = ".//input[@class='js-autofocus js-label-search']") public SelenideElement searchLabelField;
    @FindBy(xpath = ".//input[@class='primary confirm mod-compact js-add-card' and @value='Add']") public SelenideElement addCardButton;



    public SelenideElement boardTitle(String name){
        return $(By.xpath(".//span[@class='board-header-btn-text' and text()='" + name + "']"));
    }

    public SelenideElement deleteCreatedBoard(){
        openMoreLink.shouldBe(Condition.visible).click();
        closeBoardButton.shouldBe(Condition.visible).click();
        confirmCloseButton.click();
        deleteBoard.shouldBe(Condition.visible).click();
        confirmDeleteBoard.click();
        return $(By.xpath("text()='Board not found.'"));
    }

    public SelenideElement createNewList(String listName){
        addListName.shouldBe(Condition.visible).setValue(listName);
        saveListButton.click();
        return $(By.xpath(".//textarea[text()='" + listName + "']"));
    }

    public SelenideElement addNewCardToList(String cardName, String label){
        addCard.shouldBe(Condition.visible).click();
        addCardsText.shouldBe(Condition.visible).setValue(cardName);
        showCardsMenu.shouldBe(Condition.visible).click();
        chooseLabelsOption.shouldBe(Condition.visible).click();
        searchLabelField.setValue(label).pressEnter();
        addCardButton.click();
        return $(By.xpath(".//span[@class='list-card-title js-card-name' and text()='"+ cardName +"']"));
    }

    public SelenideElement moveCardToAnotherList(String cardName, String listName){
        $(By.xpath(".//span[@class='list-card-title js-card-name' and text()='" + cardName + "']")).
                dragAndDropTo($x(".//textarea[@class='list-header-name mod-list-name js-list-name-input' and text()='" + listName + "']"));
        return $(By.xpath(".//span[text()='" + cardName + "']"));
    }
}
