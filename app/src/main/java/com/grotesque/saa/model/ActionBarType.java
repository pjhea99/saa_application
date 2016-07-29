package com.grotesque.saa.model;


public enum  ActionBarType
{
    COMMON ("COMMON", 0, "common"),
    TOP ("TOP", 1, "draft&secret"),
    SINGLE_IMG ("SINGLE_IMG", 2, "singleImg");

    private String type;
    private int num;
    private String description;

    ActionBarType(String type, int num, String desciption){
        this.type = type;
        this.num = num;
        this.description = desciption;
    }
}