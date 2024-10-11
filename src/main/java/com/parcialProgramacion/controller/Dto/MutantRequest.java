package com.parcialProgramacion.controller.Dto;


import lombok.Data;

import java.util.List;

@Data
public class MutantRequest {

    private List<String> dnaSequence;
}
