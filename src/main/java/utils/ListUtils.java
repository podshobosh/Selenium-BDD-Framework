package utils;

import org.openqa.selenium.WebElement;
import java.util.List;


public class ListUtils {

    public static WebElement findByText(List<WebElement> elements, String targetText) {
        for (WebElement el : elements) {
            if (el.getText().trim().equalsIgnoreCase(targetText)) {
                return el;
            }
        }
        throw new RuntimeException("Element with text '" + targetText + "' not found");
    }
}
