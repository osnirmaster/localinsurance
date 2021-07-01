package br.com.insurance.market.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class DateRange {
    private LocalDate from;
    private LocalDate to;

    
    
    public DateRange(LocalDate from, LocalDate to) {
		super();
		this.from = from;
		this.to = to;
	}

	public static DateRange between(LocalDate from, LocalDate to) {
        return new DateRange(from, to);
    }

    public boolean contains(LocalDate eventDate) {
        if (eventDate.isAfter(to))
            return false;

        if (eventDate.isBefore(from))
            return false;

        return true;
    }

    public DateRange endOn(LocalDate endDate) {
        return DateRange.between(from, endDate);
    }

    public BigDecimal days() {
        return BigDecimal.valueOf(ChronoUnit.DAYS.between(from,to) + 1);
    }
}
