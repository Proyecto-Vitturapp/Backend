package com.vittur.vitturapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiculoDTO {
    private String plate;
    private String brand;
    private String model;
    @JsonProperty("fabrication_year")
    private String fabrication_year;
    @JsonProperty("vehicle_type")
    private String vehicle_type;
    @JsonProperty("next_review_date")
    private LocalDate next_review_date;
    private Integer status;
}
