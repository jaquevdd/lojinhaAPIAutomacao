package modulos.produto;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ComponentePojo;
import pojo.ProdutoPojo;
import pojo.UsuarioPojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do modulo de Produto")

public class ProdutoTest {
    private String token;

    @BeforeEach //Antes de cada teste, faça algo
    public void beforeEach(){
        //Configurando os dados da API Rest da Lojinha
        baseURI = "http://165.227.93.41";
        //port = 8080;
        basePath = "/lojinha";

        UsuarioPojo usuario = new UsuarioPojo();
        usuario.setUsuarioLogin("admin");
        usuario.setUsuarioLogin("admin");

        //Obter o token do usuário Admin
        this.token = given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.criarUsuarioAdministrador())
                .when()
                    .post("v2/login")
                .then()
                    .extract()
                        .path("data.token");
    }

    @Test
    @DisplayName("Validar que o valor do produto igual a R$ 0,00 não é permitido")
    public void testValidarLimiteZeradoProibidoValorProduto() {
        //Tentar inserir um produto com valor 0.00 e validar que a mensagem de erro foi apresentada e o
        //status code retornado foi 422
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumComValorIgualA(0.00))
            .when()
                .post("v2/produtos")
            .then()
                .assertThat()
                        .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                        .statusCode(422);
    }

    @Test
    @DisplayName("Validar que o valor do produto não ultrapasse R$ 7.000,00")
    public void testValidarLimiteMaximoValorProduto() {
        //Tentar inserir um produto com valor maior que R$ 7.000,00 e validar que a mensagem de erro foi apresentada e o
        //status code retornado foi 422
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumComValorIgualA(7000.01))
            .when()
                .post("v2/produtos")
            .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);
    }
}
