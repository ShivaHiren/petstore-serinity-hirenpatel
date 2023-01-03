package petstore.CrudTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import petstore.model.PetPojo;
import petstore.petstoreinfo.PetSteps;
import petstore.testbase.TestBase;
import java.util.ArrayList;
import java.util.HashMap;
import static org.hamcrest.Matchers.hasValue;
import static petstore.utils.TestUtils.getRandomDigits;


    @RunWith(SerenityRunner.class)
    public class PetCRUDTest extends TestBase {
    static int id = getRandomDigits();
    static String Name = "Sky";
    static String status = "Available";

    @Steps
    PetSteps petSteps;


    @Title("This will create new record of Pet")
    @Test
    public void test001() {
        io.swagger.petstore.model.PetBodyDataPojo.CategoryData categoryData = new io.swagger.petstore.model.PetBodyDataPojo.CategoryData(getRandomDigits(), "Dog");
        ArrayList<String> photoList = new ArrayList<>();
        photoList.add("www.husky.com/photo");
        ArrayList<io.swagger.petstore.model.PetBodyDataPojo.TagData> tagDataList = new ArrayList<>();
        io.swagger.petstore.model.PetBodyDataPojo.TagData tagData = new io.swagger.petstore.model.PetBodyDataPojo.TagData(getRandomDigits(), "Black and White spots");
        tagDataList.add(tagData);
        petSteps.createPet(id, categoryData, Name, photoList, tagDataList, status);
    }

    @Title("This will get existing record of Pet")
    @Test
    public void test002() {
        ValidatableResponse response = petSteps.getUserByID(id).statusCode(200);
        String name = response.extract().path("name");
        Assert.assertTrue(name.matches(Name));
    }

    @Title("This will update an existing record of Pet")
    @Test
    public void test003() {
        status = "not available";
        io.swagger.petstore.model.PetBodyDataPojo.CategoryData categoryData = new io.swagger.petstore.model.PetBodyDataPojo.CategoryData(getRandomDigits(), "Dog");
        ArrayList<String> photoList = new ArrayList<>();
        photoList.add("www.husky.com/photo");
        ArrayList<io.swagger.petstore.model.PetBodyDataPojo.TagData> tagDataList = new ArrayList<>();
        io.swagger.petstore.model.PetBodyDataPojo.TagData tagData = new io.swagger.petstore.model.PetBodyDataPojo.TagData(getRandomDigits(), "Blue eyes");
        tagDataList.add(tagData);
        ValidatableResponse response = petSteps.updatePetRecord(id, categoryData, Name, photoList, tagDataList, status);
        HashMap<String, ?> update = response.extract().path("");
        Assert.assertThat(update, hasValue(status));

    }

    @Title("This will delete an existing record of Pet")
    @Test
    public void test004() {
        petSteps.deleteUser(id);
        petSteps.getUserByID(id).statusCode(404);
    }
}