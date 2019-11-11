package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ACMEPage extends BasePage {

    @FindBy(id = "transactionsTable")
    private WebElement TransactionTable = null;

    public ACMEPage(WebDriver webDriver) {
        this.Driver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void clickOnAmountColumn() {
        TransactionTable.findElement(By.tagName("thead")).findElement(By.tagName("tr")).findElement(By.id("amount")).click();
    }

    public List<Integer> getAllAmountsAfterClick() {
        List<Integer> obtainedList = new ArrayList<>();
        List<WebElement> elementList=  TransactionTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        for(WebElement element:elementList){
            obtainedList.add(Math.round(Float.parseFloat(element.findElement(By.className("text-right")).findElement(By.tagName("span")).getText().replace("USD", "").replace(" ", "").replace(",", ""))));
        }
        System.out.println("Obtained List is" + obtainedList);
        return obtainedList;
    }

}
