package pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class StockSnapshot {

    @JsonProperty("4. close")
    private BigDecimal closeValue;

    public BigDecimal getCloseValue() {
        return closeValue;
    }

    public void setCloseValue(BigDecimal closeValue) {
        this.closeValue = closeValue;
    }
}
