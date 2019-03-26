package junit;


import driver.Driver;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.NoSuchElementException;
import pages.TutByHomePage;

import util.LoginTestWatcher;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(LoginTestWatcher.class)
public class TutByLoginLogoutTests {

    private static final String LOGIN = "seleniumtests@tut.by";
    private static final String PASSWORD = "123456789zxcvbn";
    private static final String LOGIN_EXPECTED_RESULT = "Selenium Test23535";
    private TutByHomePage homePage;


    @BeforeEach
    public void openBrowser() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        Driver.getInstance().openHomePage();
        homePage = new TutByHomePage();
    }

    @AfterEach
    public void closeBrowser() {
        Driver.getInstance().closeDriver();
    }

    //test logs in the app and check whether username appeared in the corresponding field
    @Feature("Login")
    @Description("Verify the ability to login")
    @Test
    public void loginProcessTest() {
        homePage.expandEnterPopup();
        homePage.login(LOGIN, PASSWORD);
        assertEquals(LOGIN_EXPECTED_RESULT, homePage.getCurrentUserName());
    }

    //test logs out the app and check whether username is not displayed
    @Feature("Logout")
    @Description("Verify the ability to logout")
    @Test
    public void logoutProcessTest() {
        homePage.expandEnterPopup();
        homePage.login(LOGIN, PASSWORD);
        homePage.expandEnterPopup();
        homePage.logout();
        assertThrows(NoSuchElementException.class, () ->
                homePage.getCurrentUserName());
    }


}