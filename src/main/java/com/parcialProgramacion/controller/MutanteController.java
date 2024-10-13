package com.parcialProgramacion.controller;

import com.parcialProgramacion.controller.Dto.MutantRequest;
import com.parcialProgramacion.controller.Dto.MutantResponse;
import com.parcialProgramacion.service.MutantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/mutant")
@RestController
@AllArgsConstructor
public class MutanteController{

    private MutantService mutantService;
    @PostMapping
    public ResponseEntity<String>askForMutant(@RequestBody MutantRequest request){
        if (!mutantService.isMutant(request)){
            return new ResponseEntity<>("Humano", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("Mutante", HttpStatus.ACCEPTED);
    }
    @GetMapping("/stats")
    public ResponseEntity<MutantResponse>mutantStats(){
        return ResponseEntity.ok(mutantService.stats());
    }
}
/*Human DNA*/
/*
"dnaSequence": [
        "ATCGGA",
        "CGGTGC",
        "TTATAT",
        "AGATGG",
        "CCTCTA",
        "TCACTG"
        ]
 */
