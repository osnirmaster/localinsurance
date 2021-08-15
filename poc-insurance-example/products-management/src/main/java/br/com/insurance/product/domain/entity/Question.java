package br.com.insurance.product.domain.entity;

public class Question {

    private String code;
    private int index;
    private String text;

    public Question(String code, int index, String text) {
        this.code = code;
        this.index = index;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
