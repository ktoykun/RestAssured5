package GoRest;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.github.javafaker.Faker;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GoRestUserTest {
//bu dersin son saatini al
    Faker randomVeriUreteci=new Faker ();
    int userID;

    RequestSpecification reqSpec;

    @BeforeClass
    public void setup() {

        baseURI = "https://gorest.co.in/public/v2/users"; // base uri req.spec'ten önce tanımlanmalı
        //baseURI ="https://test.gorest.co.in/public/v2/users/";

        reqSpec = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer e4b22047188da067d3bd95431d94259f63896347f9864894a0a7013ee5f9c703")
                .setContentType(ContentType.JSON)
                .setBaseUri(baseURI)
                .build();

    }
    @Test
    public void createUserJson () {

        //Postmande yaptıklarımızı bununla deniyoruz.bize bir user
        // oluşturmak için ne lazımdı bakalım
        //POST URL lazım: https://gorest.co.in/public/v2/users
        // token lazım: Bearer 1ea97653d55c114c2dfb7922a7c1abbe1b60968a6cf036f872e221b4c9b27ee5
        // body lazım: {"name":"{{$randomFullName}}", "gender":"male", "email":"{{$randomEmail}}", "status":"active"}

        // bir tane veri üretici koyalım. 422 hatası dönmesin

        String randomFullName=randomVeriUreteci.name().fullName();
        String randomEmail=randomVeriUreteci.internet().emailAddress();

        int userID= given()
                .header("Authorization", "Bearer 1ea97653d55c114c2dfb7922a7c1abbe1b60968a6cf036f872e221b4c9b27ee5")
                .contentType(ContentType.JSON) // gönderilecek datanın türü
                .body("{\"name\":\""+randomFullName+"\", \"gender\":\"male\", \"email\":\""+randomEmail+"\", \"status\":\"active\"}")
                .log().uri()
                .log().body()


                .when()
                .post("https://gorest.co.in/public/v2/users") // burada get değil post seçiyoruz


                .then() // buraya kontrol edilecekleri yazıyoruz
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract().path("id")
                ;
    }


    @Test
    public void createUserMap(){

        String randomFullName=randomVeriUreteci.name().fullName();
        String randomEmail=randomVeriUreteci.internet().emailAddress();


        Map<String, String> newUser=new HashMap<>();
        newUser.put("name", randomFullName);
        newUser.put("gender", "female");
        newUser.put("email", randomEmail);
        newUser.put("status", "active");

        given()
                .header("Authorization", "Bearer 1ea97653d55c114c2dfb7922a7c1abbe1b60968a6cf036f872e221b4c9b27ee5")
                .contentType(ContentType.JSON) // gönderilecek data JSON
                .body(newUser)


        .when().post("https://gorest.co.in/public/v2/users/").
                then().
                log().body()
                .statusCode(201).
                contentType(ContentType.JSON).
               extract().path("id");

    }
    @Test(dependsOnMethods = "createUser")
    public void getUserByID() {

        given()
                .header("Authorization", "Bearer 1ea97653d55c114c2dfb7922a7c1abbe1b60968a6cf036f872e221b4c9b27ee5")

                .when()
                .get("https://gorest.co.in/public/v2/users/"+userID)

                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id",equalTo(userID))
        ;

    }


    @Test
    public void createUserClass () {
        String rndFullname = randomVeriUreteci.name().fullName();
        String rndEmail = randomVeriUreteci.internet().emailAddress();

       /*
        User newUser=new User();
        newUser.name=rndFullname;
        newUser.gender="male";
        newUser.email=rndEmail;
        newUser.status="active";
        */


        userID =
                given()
                        .header("Authorization", "Bearer cefb893f50cf78daf593cb194b2d219e0419a4bb10b032388e0144943a20a166")
                        .contentType(ContentType.JSON) // gönderilecek data JSON
                      //  .body(newUser)
                        //.log().uri()
                        //.log().body()

                        .when()
                        .post("https://gorest.co.in/public/v2/users")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .extract().path("id");



    }




    @Test
    public void updateUser(){
        String randomFullName=randomVeriUreteci.name().fullName();
        String randomEmail=randomVeriUreteci.internet().emailAddress();

        given()
                .header("Authorization", "Bearer cefb893f50cf78daf593cb194b2d219e0419a4bb10b032388e0144943a20a166")
                .contentType(ContentType.JSON)

                .when()
                .put("https://gorest.co.in/public/v2/users")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteUser(){

    }

    @Test
    public void deleteUserNegative(){

    }


}
