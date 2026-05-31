package com.vittur.vitturapp.dtos.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevisionCreateDTO {
    @NotNull(message = "La fecha de la revisión no puede ser nula")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("review_date")
    private LocalDate review_date;
    @NotNull(message = "El kilometraje actual no puede ser nulo")
    @PositiveOrZero(message = "El kilometraje actual debe ser un valor positivo o cero")
    @JsonProperty("actual_km")
    private Integer actual_km;
    @NotBlank(message = "El diagnóstico no puede estar vacío")
    @Size(max = 1000, message = "El diagnóstico no puede tener más de 1000 caracteres")
    @JsonProperty("review_note")
    private String review_note;
    @NotNull(message = "El importe no puede ser nulo")
    @PositiveOrZero(message = "El importe debe ser un valor positivo o cero")
    @JsonProperty("import")
    private Float importe;

    @NotBlank(message = "La matrícula no puede estar vacía")
    @Size(max = 15, message = "La matrícula no puede tener más de 15 caracteres")
    private String plate;
    @NotNull(message = "El identificador del usuario no puede ser nulo")
    @JsonProperty("user_id")
    private Integer user_id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("next_review_date")
    private LocalDate next_review_date;
}
