package com.parcialProgramacion.controller.Dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MutantRequest {

    private List<String> dnaSequence;
}
