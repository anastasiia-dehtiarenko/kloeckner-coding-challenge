package org.selenide.exmples;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.getElement;

public class TrelloBoardPage extends TrelloCommonPage{


    public SelenideElement boardTitle(String name){
        return $(By.xpath(".//span[@class='board-header-btn-text' and text()='" + name + "']"));
    }
}
