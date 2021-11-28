package br.com.insurance.market;

import br.com.insurance.market.adapters.controller.QuoteService;
import br.com.insurance.market.domain.Quote;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@SpringBootTest
public class testCreateQuote {

    private final QuoteService quoteService;

    public testCreateQuote(QuoteService quoteService) {
        this.quoteService = quoteService;
    }


    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
    }

    @DisplayName("Test Spring @Autowired Integration")
    @Test
    void testCreateQuote(){
        quoteService.createQuoteTest(new Quote());
    }
}
