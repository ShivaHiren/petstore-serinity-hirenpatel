package petstore.CrudTest;


import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import petstore.petstoreinfo.UserSteps;
import petstore.testbase.TestBase;
import java.util.HashMap;
import static petstore.utils.TestUtils.getRandomDigits;
import static petstore.utils.TestUtils.getRandomValue;

@RunWith(SerenityRunner.class)
public class UserCRUDTest extends TestBase {
    static int id = getRandomDigits();
    static String username = "Jhon" + getRandomValue();
    static String firstName = "Jhon";
    static String lastName = "Wick";
    static String email = username + "@gmail.com";
    static String password = "abc" + getRandomValue();
    static String phone = "012345" + getRandomDigits();
    static int userStatus = 1;

    @Steps
    UserSteps userSteps;

    @Title("This will create a new user record")
    @Test
    public void test001() {
        userSteps.createUserByUserName(id, username, firstName, lastName, email, password, phone, userStatus);
    }

    @Title("This  will get and verify existing user record")
    @Test
    public void test002() {
        ValidatableResponse response = userSteps.getUserByUserName(username).statusCode(200);
        HashMap<String, ?> newUsername = response.extract().path("");
        Assert.assertTrue(newUsername.containsValue(username));
    }

    @Title("This  will update and verify an existing user record")
    @Test
    public void test003() {
        lastName = "Patel";
        ValidatableResponse response = userSteps.updateUserByUserName(id, username, firstName, lastName, email, password, phone, userStatus);
        HashMap<String, ?> update = response.extract().path("");
        Integer newId = new Integer(id);
        String id = newId.toString();
        Assert.assertTrue(update.containsValue(id));
    }

    @Title("This  will delete and verify an existing user record")
    @Test
    public void test004() {
        userSteps.deleteUserByUserName(username);
        userSteps.getUserByUserName(username).statusCode(404);
    }
}