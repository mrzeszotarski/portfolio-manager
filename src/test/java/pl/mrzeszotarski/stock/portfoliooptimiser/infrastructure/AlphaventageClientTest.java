package pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure.data.AlphaventageClientResponse;
import pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure.data.AlphaventageFuntion;
import pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure.data.StockSnapshotData;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AlphaventageClient.class)
@ComponentScan(basePackages = "pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure")
@ConfigurationPropertiesScan("pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure")
@ActiveProfiles("test")
public class AlphaventageClientTest {

    @Autowired
    private AlphaventageClient client;

    private WireMockServer wireMockServer;

    @BeforeEach
    public void init(){
        wireMockServer = new WireMockServer(8090);
        wireMockServer.start();
        wireMockServer.stubFor(get(urlEqualTo("/alphaventagemock/query?function=TIME_SERIES_DAILY&symbol=ABBV&apikey=test"))
                .withHeader("Accept", equalTo("application/json, application/*+json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("mock/query.json")));
    }

    @AfterEach
    public void after(){
        wireMockServer.stop();
    }

    @Test
    public void testGetLast(){
        //given
        AlphaventageFuntion function = AlphaventageFuntion.TIME_SERIES_DAILY;
        String symbol = "ABBV";
        LocalDate localDate = LocalDate.parse("2019-11-27");

        //when
        AlphaventageClientResponse response = client.query(function, symbol);

        //then
        assertThat(response.getMetadata().getSymbol()).isEqualTo(symbol);
        assertThat(response.getLast().getValue()).isEqualTo(new BigDecimal("88.7100"));
        assertThat(response.getLast().getDate()).isEqualTo(localDate);
        assertThat(response.getLast().getSymbol()).isEqualTo(symbol);
    }

    @Test
    public void testGetDefinedDate(){
        //given
        AlphaventageFuntion function = AlphaventageFuntion.TIME_SERIES_DAILY;
        String symbol = "ABBV";
        LocalDate localDate = LocalDate.parse("2019-11-21");

        //when
        AlphaventageClientResponse response = client.query(function, symbol);
        StockSnapshotData fromDate = response.getFromDate(localDate);

        //then
        assertThat(response.getMetadata().getSymbol()).isEqualTo(symbol);
        assertThat(fromDate.getValue()).isEqualTo(new BigDecimal("86.5200"));
        assertThat(fromDate.getDate()).isEqualTo(localDate);
        assertThat(fromDate.getSymbol()).isEqualTo(symbol);
    }
}
