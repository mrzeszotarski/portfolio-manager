package pl.mrzeszotarski.stock.portfoliooptimiser.domain;

import pl.mrzeszotarski.stock.portfoliooptimiser.domain.model.ShareStockData;

import java.time.LocalDate;

public interface StockValueProvider {

    ShareStockData currentValueForStock(String stockName);
    ShareStockData valueForStockAtDate(String stockName, LocalDate date);
}
