package com.java.school.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class City {

    private int id;
    private String name;
    private int tax;
    private boolean seaport;
    private boolean airport;

}
