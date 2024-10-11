package com.parcialProgramacion.controller.Dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MutantResponse {
    private Integer count_mutant_dna;
    private Integer count_human_dna;
    private Double ratio;
}
