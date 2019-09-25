package com.java.school.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Builder
@EqualsAndHashCode
public class CityRoute implements Serializable {
    private String from;
    private String to;
    @EqualsAndHashCode.Exclude private String distance;
}
