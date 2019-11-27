package pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure;

import org.springframework.stereotype.Component;
import pl.mrzeszotarski.stock.portfoliooptimiser.domain.StockValueProvider;
import pl.mrzeszotarski.stock.portfoliooptimiser.domain.model.ShareStockData;
import pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure.data.AlphaventageFuntion;
import pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure.data.ShareStockMapper;

import java.time.LocalDate;

@Component
public class AlphaventageStockValueProvider implements StockValueProvider {

    private final AlphaventageClient alphaventageClient;

    public AlphaventageStockValueProvider(AlphaventageClient alphaventageClient) {
        this.alphaventageClient = alphaventageClient;
    }


    @Override
    public ShareStockData currentValueForStock(String stockName) {
        return ShareStockMapper.map(alphaventageClient.query(AlphaventageFuntion.TIME_SERIES_DAILY, stockName).getLast());
    }

    @Override
    public ShareStockData valueForStockAtDate(String stockName, LocalDate date) {
        return ShareStockMapper.map(alphaventageClient.query(AlphaventageFuntion.TIME_SERIES_DAILY, stockName).getFromDate(date));
    }
}
