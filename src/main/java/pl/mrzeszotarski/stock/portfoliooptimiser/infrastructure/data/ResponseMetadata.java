package pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseMetadata {
    @JsonProperty("2. Symbol")
    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
