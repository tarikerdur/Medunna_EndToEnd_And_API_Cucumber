package stepdefinitions.e2e_test;

import io.cucumber.java.en.*;
import pages.MedunnaHomePage;
import pages.MedunnaLoginPage;
import pages.MedunnaRoomPage;
import utilities.ConfigReader;
import utilities.Driver;

public class MedunnaSignInStepDefs {
    MedunnaHomePage medunnaHomePage = new MedunnaHomePage();
    MedunnaLoginPage medunnaLoginPage = new MedunnaLoginPage();
    MedunnaRoomPage medunnaRoomPage = new MedunnaRoomPage();

    @Given("go to {string}")
    public void go_to(String string) {
        Driver.getDriver().get(ConfigReader.getProperty(string));
    }
    @When("click on user icon")
    public void click_on_user_icon() {
        medunnaHomePage.userIcon.click();
    }
    @When("click on sign in option")
    public void click_on_sign_in_option() {
        medunnaHomePage.signInOption.click();
    }
    @When("enter username")
    public void enter_username() {
        medunnaLoginPage.usernameInput.sendKeys(ConfigReader.getProperty("medunna_username"));
    }
    @When("enter password")
    public void enter_password() {
        medunnaLoginPage.passwordInput.sendKeys(ConfigReader.getProperty("medunna_password"));
    }
    @When("click on sign in submit button")
    public void click_on_sign_in_submit_button() {
        medunnaLoginPage.rememberMeCheckbox.click();
        medunnaLoginPage.signInSubmitButton.click();
    }
}
