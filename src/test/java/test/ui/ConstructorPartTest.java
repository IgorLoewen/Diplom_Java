package test.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;

import static org.junit.Assert.assertTrue;

@Epic("Navigation in the constructor section")
public class ConstructorPartTest extends TestsSetUp {

    private MainPage mainPage;

    @Before
    @Step("Initializing the test environment")
    @Description("Performs environment initialization for the test")
    public void setUp() {
        super.setUp();
        mainPage = new MainPage(driver);
        mainPage.open();
    }

    @Test
    @Description("The test verifies that clicking on the 'Sauces' section activates the corresponding tab while the others remain inactive")
    @DisplayName("Activation of the 'Sauces' tab")
    public void testActivateSaucesTab() {

        mainPage.clickSaucesTab();

        assertTrue("The 'Sauces' tab did not activate correctly", mainPage.isCorrectTabActive(1));
    }


    @Test
    @Description("The test verifies that clicking on the 'Fillings' section activates the corresponding tab while the others remain inactive")
    @DisplayName("Activation of the 'Fillings' tab")
    public void testActivateFillingsTab() {

        mainPage.clickFillingsTab();

        assertTrue("The 'Fillings' tab did not activate correctly", mainPage.isCorrectTabActive(2));
    }

    @Test
    @Description("The test verifies that clicking on the 'Buns' section activates the corresponding tab while the others remain inactive")
    @DisplayName("Activation of the 'Buns' tab")
    public void testActivateBunsTab() {

        mainPage.clickBunsTab();

        assertTrue("The 'Buns' tab did not activate correctly", mainPage.isCorrectTabActive(0));
    }


    @After
    @Step("Clearing data after the test")
    @Description("Closing the browser")
    public void tearDown() {
        super.tearDown();
    }

}
