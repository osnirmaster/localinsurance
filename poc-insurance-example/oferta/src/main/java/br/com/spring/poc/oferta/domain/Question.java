package br.com.spring.poc.oferta.domain;

public class Question {
	
    private String code;
    private int index;
    private String text;

    public Question(String code, int index, String text) {
        this.code = code;
        this.index = index;
        this.text = text;
    }

}
