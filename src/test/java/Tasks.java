import Model.ToDo;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

import static io.restassured.RestAssured.given;

public class Tasks {

    /**
     * Task 2
     * create a request to https://httpstat.us/203
     * expect status 203
     * expect content type TEXT
     */

    @Test
    public void Task2() {
/** Task 1

 create a request to https://jsonplaceholder.typicode.com/todos/2
 expect status 200
 Converting Into POJO*/


        ToDo todo = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/todos/2")

                .then()
                .log().body()    // dönen body json datat sı,   log.all()
                .statusCode(200)
                .extract().as(ToDo.class);

        System.out.println("todo = " + todo);
        System.out.println("todo.getTitle() = " + todo.getTitle());

    }


    @Test
    public void Task3() {
        /**
         * Task 3
         * create a request to https://jsonplaceholder.typicode.com/todos/2
         * expect status 200
         * expect content type JSON
         * expect title in response body to be "quis ut nam facilis et officia qui"
         */

        given().when()
                .get("https://jsonplaceholder.typicode.com/todos/2")

                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON).body("title", equalTo("quis ut nam facilis et officia qui"));


    }



    @Test
    public void Task4() {
        /**

         Task 4
         create a request to https://jsonplaceholder.typicode.com/todos/2
         expect status 200
         expect content type JSON
         expect response completed status to be false (hamcrest)
         extract completed field and testNG assertion (TestNG)
         */

        // 1. yol TestNg
       Boolean completed= given().when()
                .get("https://jsonplaceholder.typicode.com/todos/2")

                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().path("completed");

        Assert.assertFalse(completed);

        //2. yol hamcrest
        given().when()
                .get("https://jsonplaceholder.typicode.com/todos/2")

                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON);
        // burada bir satır kod eksik al

}

}