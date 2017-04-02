package com.seleniumOO.util;

/**
 * Enum used for specified an attribute of a SOOUIElement(browser element) object.
 * Created by daniela on 19/03/17.
 */
public enum SSOElementType {

    ID("id"),
    CLASS("class"),
    NAME("name"),
    LABEL("label"),
    TYPE("type"),
    XPATH("xpath"),
    LINKTEXT("linkText");

    String value;

    SSOElementType(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
