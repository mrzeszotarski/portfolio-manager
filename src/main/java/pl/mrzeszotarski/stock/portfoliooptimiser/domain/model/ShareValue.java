package pl.mrzeszotarski.stock.portfoliooptimiser.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class ShareValue {
    private BigDecimal value;
    private Currency currency;
}
