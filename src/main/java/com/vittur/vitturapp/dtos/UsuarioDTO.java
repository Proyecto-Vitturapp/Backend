package com.vittur.vitturapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    @JsonProperty("user_id")
    private Integer user_id;
    private String username;
    @JsonProperty("creation_date")
    private LocalDate creation_date;
    private String name;
    @JsonProperty("first_last_name")
    private String first_last_name;
    @JsonProperty("second_last_name")
    private String second_last_name;
    private String email;
    private Integer role;
    @JsonProperty("phone_number")
    private String phone_number;
}
