import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ZippoTest2 {

    @Test
    public void test2 () {
                 given()
                // hazırlık işlemlerini yapıyoruz. token, send body, parametrele vb

                .when()
                // endpoint (url), metodu

                .then();
                // assertion ve data işlemi yapıyoruz.


    }

    @Test
    public void StatusCodeTest () {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()// ekrana dönen bilgiyi yazdır. bodynin logunu yazdır dedik. bütün bilgiyi göstersin isteseydik
               // log.all diyebilirdik. postmana göre daha kolay. isim verme vs yok
                .statusCode(200); // dönüş kodu 200 'mü?




    }

    @Test
    public void ContentTypeTest () {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.TEXT); // dönen tip Json mı?

    }














}
