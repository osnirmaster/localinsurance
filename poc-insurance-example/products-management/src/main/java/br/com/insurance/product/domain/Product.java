package br.com.insurance.product.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Product {

    private Meta metaData;
    private String code;
    private String name;
    private Integer groupCode;
    private String groupName;
    private String branchCode;
    private String branchName;
    private String image;
    private String description;
    private List<Cover> covers;
    private List<Question> questions;
    private int maxNumberOfInsured;
    private String icon;
    private Category category;
    private LocalDate createdDate;
    private LocalDate validatyFrom;
    private LocalDate validatyUntil;
    private Version version;
    private BigDecimal price;
    private Status status;
    private Partners partner;

}
