package br.com.insurance.market.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Proposal {


    private BigDecimal price;
    private Cover covers;
    private Map<String, BigDecimal> coversPrices;
    private OfferStatus status;
    private Map<String, String> answers;
    private Map<Questions,String> questions;
    private LocalDate creationDate;
}
