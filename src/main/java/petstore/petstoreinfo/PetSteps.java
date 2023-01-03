package petstore.petstoreinfo;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import petstore.constants.EndPoints;
import io.swagger.petstore.model.PetBodyDataPojo;
import java.util.ArrayList;

public class PetSteps {


    @Step("Creating Pet record with Id: {0}, categoryData: {1}, Name: {2}, photoList: {3}, tagDataList: {4}and status: {5}")
    public ValidatableResponse createPet(int Id, io.swagger.petstore.model.PetBodyDataPojo.CategoryData categoryData, String Name, ArrayList<String> photoList, ArrayList<io.swagger.petstore.model.PetBodyDataPojo.TagData> tagDataList, String status) {


        io.swagger.petstore.model.PetBodyDataPojo petBodyDataPojo = new io.swagger.petstore.model.PetBodyDataPojo();
        petBodyDataPojo.setId(Id);
        petBodyDataPojo.setCategory(categoryData);
        petBodyDataPojo.setName(Name);
        petBodyDataPojo.setPhotoUrls(photoList);
        petBodyDataPojo.setTags(tagDataList);
        petBodyDataPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(petBodyDataPojo)
                .post(EndPoints.GET_PET)
                .then().log().all().statusCode(200);
    }

    @Step("Getting existing Pet record with Id: {0}")
    public ValidatableResponse getUserByID(int id) {
        return SerenityRest.given()
                .pathParam("id", id)
                .when()
                .get(EndPoints.GET_SINGLE_PET_BY_ID)
                .then();
    }

    @Step("Updating existing Pet record with Id: {0}, categoryData: {1}, Name: {2}, photoList: {3}, tagDataList: {4}and status: {5}")
    public ValidatableResponse updatePetRecord(int Id, io.swagger.petstore.model.PetBodyDataPojo.CategoryData categoryData, String Name, ArrayList<String> photoList, ArrayList<io.swagger.petstore.model.PetBodyDataPojo.TagData> tagDataList, String status) {
        io.swagger.petstore.model.PetBodyDataPojo petBodyDataPojo = new io.swagger.petstore.model.PetBodyDataPojo();
        petBodyDataPojo.setId(Id);
        petBodyDataPojo.setCategory(categoryData);
        petBodyDataPojo.setName(Name);
        petBodyDataPojo.setPhotoUrls(photoList);
        petBodyDataPojo.setTags(tagDataList);
        petBodyDataPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(petBodyDataPojo)
                .put(EndPoints.GET_All_PET_BY_IDS)
                .then().log().all().statusCode(200);
    }

    @Step("Deleting existing Pet record with Id: {0}")
    public void deleteUser(int Id) {
        SerenityRest.given().log().all()
                .when()
                .pathParam("id", Id)
                .delete(EndPoints.DELETE_PET_BY_ID)
                .then().log().all().statusCode(200);

    }

}