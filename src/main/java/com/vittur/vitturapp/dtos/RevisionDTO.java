package com.vittur.vitturapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevisionDTO {
    @JsonProperty("review_id")
    private Integer review_id;
    private String plate;
    @JsonProperty("user_id")
    private Integer user_id;
    @JsonProperty("review_date")
    private LocalDate review_date;
    @JsonProperty("actual_km")
    private Integer actual_km;
    @JsonProperty("review_note")
    private String review_note;
    @JsonProperty("import")
    private Float importe;
}
