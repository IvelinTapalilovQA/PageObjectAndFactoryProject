package page.factory.mode.test;


import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.factory.model.HeaderFactory;
import page.factory.model.LoginPageFactory;
import page.factory.model.ProfilePageFactory;
import page.factory.model.RegistrationPageFactory;
import page.object.model.Header;
import page.object.model.LoginPage;
import page.object.model.ProfilePage;
import page.object.model.RegistrationPage;
import page.object.model.test.ConfigObject;

import java.util.Random;


public class RegistrationTestFactory extends ConfigObject {

    @Test
    public void Registration(){
        WebDriver driver = super.getDriver();

        LoginPageFactory loginPageFactory = new LoginPageFactory(driver);
        loginPageFactory.navigateTo();
        Assert.assertTrue(loginPageFactory.isUrlLoaded());
        Assert.assertTrue(loginPageFactory.checkLoginTitle());
        loginPageFactory.clickRegistrationButton();

        RegistrationPageFactory registrationPageFactory = new RegistrationPageFactory(driver);
        registrationPageFactory.isUrlLoaded();

        Assert.assertEquals(registrationPageFactory.getSignUpElementText(), "Sign up");

        String randomName = generateRandomString(5, 10);
        registrationPageFactory.typeUsername(randomName);

        String randomEmail = generateRandomEmail(5, 10);
        registrationPageFactory.typeEmail(randomEmail);

        registrationPageFactory.typeDateOfBirth("12/12/1212");

        registrationPageFactory.typePassword("TestPassword1");
        registrationPageFactory.confirmPassword("TestPassword1");

        registrationPageFactory.typeInfo();
        registrationPageFactory.clickSignInButton();

        HeaderFactory headerFactory = new HeaderFactory(driver);
        headerFactory.clickProfile();

        ProfilePageFactory profilePageFactory = new ProfilePageFactory(driver);
        profilePageFactory.isUrlLoaded();
        Assert.assertTrue(profilePageFactory.getProfileTextName(randomName));
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
