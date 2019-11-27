package pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.mrzeszotarski.stock.portfoliooptimiser.domain.model.ShareStockData;
import pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure.data.AlphaventageClientResponse;
import pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure.data.StockSnapshotData;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AlphaventageStockValueProviderTest {

    private AlphaventageStockValueProvider provider;

    @Mock
    private AlphaventageClient alphaventageClient;

    @Mock
    private AlphaventageClientResponse response;

    @BeforeEach
    public void init() {
        provider = new AlphaventageStockValueProvider(alphaventageClient);
    }

    @Test
    public void testCurrentValueForStock() {
        //given
        LocalDate date = LocalDate.parse("2019-11-27");
        String symbol = "ABBV";
        BigDecimal value = new BigDecimal("88.76");
        when(alphaventageClient.query(any(), eq(symbol))).thenReturn(response);
        when(response.getLast()).thenReturn(new StockSnapshotData(date, value, symbol));

        //when
        ShareStockData shareStockData = provider.currentValueForStock(symbol);

        //then
        assertThat(shareStockData.getDate()).isEqualTo(date);
        assertThat(shareStockData.getSymbol()).isEqualTo(symbol);
        assertThat(shareStockData.getShareValue().getValue()).isEqualTo(value);
    }

    @Test
    public void testValueForStockAtDate() {
        //given
        LocalDate date = LocalDate.parse("2019-11-21");
        String symbol = "ABBV";
        BigDecimal value = new BigDecimal("88.76");
        when(alphaventageClient.query(any(), eq(symbol))).thenReturn(response);
        when(response.getFromDate(eq(date))).thenReturn(new StockSnapshotData(date, value, symbol));

        //when
        ShareStockData shareStockData = provider.valueForStockAtDate(symbol, date);

        //then
        assertThat(shareStockData.getDate()).isEqualTo(date);
        assertThat(shareStockData.getSymbol()).isEqualTo(symbol);
        assertThat(shareStockData.getShareValue().getValue()).isEqualTo(value);
    }
}
