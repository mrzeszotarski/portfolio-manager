package pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "alphaventage")
public class AlphaventageClientProperties {

    @Value("${url}")
    private String url;

    @Value("${apikey}")
    private String apiKey;
}
