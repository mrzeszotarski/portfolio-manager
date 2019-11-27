package pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure;

import com.google.common.collect.Maps;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure.data.AlphaventageClientResponse;
import pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure.data.AlphaventageFuntion;

import java.util.LinkedHashMap;

@Repository
public class AlphaventageClient {

    private static final String REST_URI
            = "?function={function}&symbol={symbol}&apikey={apikey}";

    private final String apiKey;
    private final String baseUrl;

    private RestTemplate restClient = new RestTemplate();

    public AlphaventageClient(AlphaventageClientProperties properties) {
        this.apiKey = properties.getApiKey();
        this.baseUrl = properties.getUrl();
    }

    public AlphaventageClientResponse query(AlphaventageFuntion function, String symbol){
        LinkedHashMap<String, String> parameters = Maps.newLinkedHashMap();
        parameters.put("function", function.name());
        parameters.put("symbol", symbol);
        parameters.put("apikey", apiKey);
        ResponseEntity<AlphaventageClientResponse> entity = restClient.getForEntity(baseUrl + REST_URI, AlphaventageClientResponse.class, parameters);
        return entity.getBody();
    }
}
