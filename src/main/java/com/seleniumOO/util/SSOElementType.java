package com.seleniumOO.util;

/**
 * Created by daniela on 19/03/17.
 */
public enum SSOElementType {

    ID("id"),
    CLASS("class"),
    NAME("name"),
    LABEL("label"),
    TYPE("type");

    String value;

    SSOElementType(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
