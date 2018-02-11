package org.selenide.exmples;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import static com.codeborne.selenide.Selenide.page;

public class TrelloMainPage{

    @FindBy(xpath = ".//a[@class='global-header-section-button' and text()='Log In']") public SelenideElement goToLoginPage;
    @FindBy(xpath = ".//*[@id='user']") public SelenideElement userTrello;
    @FindBy(xpath = ".//*[@id='password']") public SelenideElement passwordTrello;
    @FindBy(xpath = ".//*[@id='login']") public SelenideElement loginTrelloButton;

    public TrelloAllBoardsPage login(String email, String password){
        goToLoginPage.click();
        userTrello.shouldBe(Condition.visible).setValue(email);
        passwordTrello.shouldBe(Condition.visible).setValue(password);
        loginTrelloButton.click();
        return page(TrelloAllBoardsPage.class);
    }
}
