package pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure.data;

import com.google.common.base.Preconditions;
import pl.mrzeszotarski.stock.portfoliooptimiser.domain.model.Currency;
import pl.mrzeszotarski.stock.portfoliooptimiser.domain.model.ShareStockData;
import pl.mrzeszotarski.stock.portfoliooptimiser.domain.model.ShareValue;

public class ShareStockMapper {

    private ShareStockMapper() {
    }

    public static ShareStockData map(StockSnapshotData stockSnapshotData) {
        Preconditions.checkNotNull(stockSnapshotData);
        Preconditions.checkNotNull(stockSnapshotData.getDate());
        Preconditions.checkNotNull(stockSnapshotData.getSymbol());
        Preconditions.checkNotNull(stockSnapshotData.getValue());

        return ShareStockData.builder()
                .date(stockSnapshotData.getDate())
                .symbol(stockSnapshotData.getSymbol())
                .shareValue(ShareValue.builder()
                        .value(stockSnapshotData.getValue())
                        .currency(Currency.USD)
                        .build())
                .build();
    }
}
