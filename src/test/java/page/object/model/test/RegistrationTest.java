package page.object.model.test;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.object.model.Header;
import page.object.model.LoginPage;
import page.object.model.ProfilePage;
import page.object.model.RegistrationPage;

import java.util.Random;


public class RegistrationTest extends ConfigObject{

    @Test
    public void Registration(){
        WebDriver driver = super.getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded());
        Assert.assertTrue(loginPage.checkLoginTitle());
        loginPage.clickRegistrationButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.isUrlLoaded();

        Assert.assertEquals(registrationPage.getSignUpElementText(), "Sign up");

        String randomName = generateRandomString(5, 10);
        registrationPage.typeUsername(randomName);

        String randomEmail = generateRandomEmail(5, 10);
        registrationPage.typeEmail(randomEmail);

        registrationPage.typeDateOfBirth("12/12/1212");

        registrationPage.typePassword("TestPassword1");
        registrationPage.confirmPassword("TestPassword1");

        registrationPage.typeInfo();
        registrationPage.clickSignInButton();

        Header header = new Header(driver);
        header.clickProfile();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.isUrlLoaded();
        Assert.assertTrue(profilePage.getProfileTextName(randomName));
    }
    private String generateRandomEmail(int minLengthInclusive, int maxLengthExclusive) {
        return  generateRandomString(minLengthInclusive, maxLengthExclusive) + "@gmail.com";
    }
    private String generateRandomString(int minLengthInclusive, int maxLengthExclusive) {
        return RandomStringUtils.randomAlphanumeric(minLengthInclusive, maxLengthExclusive);
    }
    public String getRandomNumber() {

        Random rnd = new Random();
        int number = rnd.nextInt(111111, 99999);

        return String.format("%06d", number);
    }
}
