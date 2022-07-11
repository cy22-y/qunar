package org.example.question3;

import lombok.Data;

@Data
public class Prop_Entity {
    public Integer row; //自然行数
    public String context; //文本内容

    public Prop_Entity(Integer row, String context) {
        this.row = row;
        this.context = context;
    }
}
