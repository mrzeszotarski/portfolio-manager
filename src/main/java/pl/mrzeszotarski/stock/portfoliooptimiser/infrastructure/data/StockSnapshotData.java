package pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class StockSnapshotData {
    private LocalDate date;
    private BigDecimal value;
    private String symbol;
}
