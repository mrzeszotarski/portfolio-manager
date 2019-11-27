package pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.mrzeszotarski.stock.portfoliooptimiser.domain.model.ShareStockData;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.mrzeszotarski.stock.portfoliooptimiser.domain.model.Currency.USD;

@ExtendWith(SpringExtension.class)
public class ShareStockMapperTest {

    @Test
    public void testMap(){
        //given
        LocalDate localDate = LocalDate.parse("2019-11-27");
        BigDecimal value = new BigDecimal("87.66");
        String symbol = "ABBV";
        StockSnapshotData data = new StockSnapshotData(localDate, value, symbol);

        //when
        ShareStockData shareStockData = ShareStockMapper.map(data);

        //then
        assertThat(shareStockData.getDate()).isEqualTo(localDate);
        assertThat(shareStockData.getSymbol()).isEqualTo(symbol);
        assertThat(shareStockData.getShareValue().getValue()).isEqualTo(value);
        assertThat(shareStockData.getShareValue().getCurrency()).isEqualTo(USD);
    }

    @Test
    public void testMapNullValuesThrowsException(){
        //given
        StockSnapshotData data = new StockSnapshotData(null, null, null);

        //when
        Executable executable = () -> ShareStockMapper.map(data);

        //then
        Assertions.assertThrows(NullPointerException.class, executable);

    }
}
