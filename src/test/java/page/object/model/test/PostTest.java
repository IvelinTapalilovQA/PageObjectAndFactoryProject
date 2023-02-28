package page.object.model.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.object.upload.*;

import java.io.File;

public class PostTest extends TestObject {
    @DataProvider(name = "getUser")
    public Object[][] username() {
        File picture = new File("src/main/java/TestResourses/qa_pic.jpg");
        String captionText = "Testing create post caption";


        return new Object[][]{
                {"ivelinQA", "Ivelin123","ivelinQA", picture, captionText}};
    }
    @Test (dataProvider = "getUser")
    public void createNewPost(String user, String password,String username,File file,String caption) throws InterruptedException {


        WebDriver driver = super.getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigatoTo();
        loginPage.typeUsername(user);
        loginPage.typePassword(password);
        loginPage.clickSignInButton();

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUrlLoaded());

        Header header = new Header(driver);
        header.clickNewPost();

        NewPostPage newPostPage = new NewPostPage(driver);
        Assert.assertTrue(newPostPage.isUrlLoaded());

        newPostPage.uploadPicture(file);
        Assert.assertTrue(newPostPage.isImageVisible());

        Assert.assertEquals(newPostPage.getInputTitle(), file.getName());

        newPostPage.typingCaption(caption);
        newPostPage.clickCreatePostButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.isUrlLoaded();

        Assert.assertTrue(profilePage.isUsernameTitleVisible(user));
        Assert.assertEquals(profilePage.getPostsCount(), 1);

        profilePage.clickPost(0);

        PostModalWindow postModalWindow = new PostModalWindow(driver);
        Assert.assertTrue(postModalWindow.isImageVisible());

        Assert.assertEquals(postModalWindow.getModalWindowCaption(), caption);
        Assert.assertEquals(postModalWindow.getPostUsernameTitle(), username);
    }
}
