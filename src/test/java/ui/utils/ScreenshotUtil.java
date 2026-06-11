package ui.utils;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;

public class ScreenshotUtil {

    public static void attachScreenshot(Page page){
        byte[] screenshot= page.screenshot(new Page.ScreenshotOptions().setFullPage(true));

        Allure.addAttachment("Failure Screenshot","image/png",new ByteArrayInputStream(screenshot),".png");

    }
}
