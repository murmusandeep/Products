package com.example.categories.api.models;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private List<Content> content = new ArrayList<>();
    private int code;

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
