/*
package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class User_Management_PO extends Base_PO {

    public User_Management_PO() {
        super();
    }



    public int extractNumberFromElement() {
        WebElement records = getDriver().findElement(By.cssSelector(".orangehrm-horizontal-padding .oxd-text--span"));
        String elementText = records.getText();

        // Regular expression to extract the number inside parentheses
        String regex = "\\((\\d+)\\)";

        // Create a pattern and matcher for the text
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(elementText);

        // Check if a match is found
        if (matcher.find()) {
            // Extract the number from the parentheses and return it
            return Integer.parseInt(matcher.group(1)); // Extracted number inside parentheses
        } else {
            throw new IllegalArgumentException("No number found in parentheses");
        }
    }
    public void printNumber(){
        System.out.println(extractNumberFromElement());
    }
}
*/
