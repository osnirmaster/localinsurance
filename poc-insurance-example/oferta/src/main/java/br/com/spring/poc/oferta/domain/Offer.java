package br.com.spring.poc.oferta.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Offer {

	private Product product;
	private BigDecimal price;
	private Cover covers;
	private LocalDate creationDate;
}
