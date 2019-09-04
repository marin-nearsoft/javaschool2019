package com.java.school.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PackageType {

    private int id;
    private String description;
    private int price ;

}
