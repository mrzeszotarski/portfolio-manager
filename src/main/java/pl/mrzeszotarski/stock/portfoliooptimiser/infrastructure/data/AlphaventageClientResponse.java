package pl.mrzeszotarski.stock.portfoliooptimiser.infrastructure.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;

public class AlphaventageClientResponse {

    @JsonProperty("Meta Data")
    private ResponseMetadata metadata;

    @JsonProperty("Time Series (Daily)")
    private Map<String, StockSnapshot> stockSnapshotMap;

    public ResponseMetadata getMetadata() {
        return metadata;
    }

    public StockSnapshotData getLast() {
        return stockSnapshotMap.entrySet().stream().collect(Collectors.toMap(
                entry -> LocalDate.parse(entry.getKey()),
                Map.Entry::getValue)).entrySet().stream().max((o1, o2) -> (o1.getKey().compareTo(o2.getKey())))
                .map(entry -> new StockSnapshotData(entry.getKey(), entry.getValue().getCloseValue(), metadata.getSymbol()))
                .orElseThrow(() -> new RuntimeException("Empty stock history"));
    }

    public StockSnapshotData getFromDate(LocalDate localDate) {
        return new StockSnapshotData(localDate,
                stockSnapshotMap.get(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).getCloseValue(),
                metadata.getSymbol());
    }

}
