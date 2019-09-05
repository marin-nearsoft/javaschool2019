package com.java.school.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class City implements Serializable {

    private int id;
    private String name;
    private int tax;
    private boolean seaport;
    private boolean airport;

}
