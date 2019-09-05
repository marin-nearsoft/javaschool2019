package com.java.school.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PackageType implements Serializable {

    private int id;
    private String description;
    private int price ;

}
