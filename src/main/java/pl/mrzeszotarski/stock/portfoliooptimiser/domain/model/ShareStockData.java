package pl.mrzeszotarski.stock.portfoliooptimiser.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class ShareStockData {
    private String symbol;
    private ShareValue shareValue;
    private LocalDate date;
}
