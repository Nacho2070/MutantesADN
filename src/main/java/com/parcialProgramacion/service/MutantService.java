package com.parcialProgramacion.service;

import com.parcialProgramacion.controller.Dto.MutantRequest;
import com.parcialProgramacion.controller.Dto.MutantResponse;
import com.parcialProgramacion.controller.exceptionHandler.exception.InvalidDnaSequenceException;
import com.parcialProgramacion.controller.exceptionHandler.exception.NoDnaFoundException;
import com.parcialProgramacion.model.MutantEntity;
import com.parcialProgramacion.service.repository.MutantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MutantService {
    private MutantRepository mutantRepository;

    private static final int MIN_SEQUENCE_LENGTH = 4;
    private static final char[] BASES = {'A', 'T', 'C', 'G'};
    public Boolean isMutant(MutantRequest request) {

        // Check if the sequence is valid
        for ( String  sequenceDna : request.getDnaSequence()){
            if(sequenceDna .length() != 6){
                throw new InvalidDnaSequenceException("The sequence must be exactly 6 characters long");
            }
        }

        Boolean response = searchSequence(request);
        MutantEntity mutant = new MutantEntity();
        String dnaSequenceStr = request.getDnaSequence().toString();

        if(!response){
            mutant.setDNA(dnaSequenceStr);
            mutant.setIsHuman(true);
            mutant.setIsMutant(false);
            mutantRepository.save(mutant);
            return false;
        }

        mutant.setDNA(dnaSequenceStr);
        mutant.setIsHuman(false);
        mutant.setIsMutant(true);
        mutantRepository.save(mutant);
        return true;
    }

    public MutantResponse stats() {

        List<MutantEntity> mutant = mutantRepository.findAll();
        if(mutant.isEmpty()){
            throw new NoDnaFoundException("No se encontró ningún DNA");
        }

        int humanCounter = 0;
        int mutantCounter = 0;
        for (MutantEntity m : mutant){
            if(m.getIsMutant()){
                mutantCounter ++;
                continue;
            }
            humanCounter ++;
        }
        //Avoid multiplier by zero
        double ratio =  mutantCounter == 0 ? 0.0 : (double)mutantCounter/humanCounter;

        MutantResponse response = MutantResponse.builder()
                        .count_human_dna(humanCounter)
                        .count_mutant_dna(mutantCounter)
                        .ratio(ratio)
                        .build();
        log.info("DNA response: {}",response);
        return response;
    }
    protected static Boolean searchSequence(MutantRequest request){
        // Convert all sequences to uppercase
        List<String> dna = request.getDnaSequence().stream().map(String::toUpperCase).toList();
        int n = dna.size();
        // Check rows
        for (int i = 0; i < n; i++) {
            int consecutiveCount = 1;
            for (int j = 1; j < n; j++) {
                if (dna.get(i).charAt(j) == dna.get(i).charAt(j - 1)) {
                    consecutiveCount++;
                    if (consecutiveCount == MIN_SEQUENCE_LENGTH) {
                        return true;
                    }
                } else {
                    consecutiveCount = 1;
                }
            }
        }
        // Check columns
        for (int j = 0; j < n; j++) {
            int consecutiveCount = 1;
            for (int i = 1; i < n; i++) {
                if (dna.get(i).charAt(j) == dna.get(i - 1).charAt(j)) {
                    consecutiveCount++;
                    if (consecutiveCount == MIN_SEQUENCE_LENGTH) {
                        return true;
                    }
                } else {
                    consecutiveCount = 1;
                }
            }
        }
        // Check diagonals
        for (int i = 0; i <= n - MIN_SEQUENCE_LENGTH; i++) {
            for (int j = 0; j <= n - MIN_SEQUENCE_LENGTH; j++) {
                StringBuilder sb1 = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                for (int k = 0; k < MIN_SEQUENCE_LENGTH; k++) {
                    sb1.append(dna.get(i+k).charAt(j + k));
                    sb2.append(dna.get(i+k).charAt(j + MIN_SEQUENCE_LENGTH - 1 - k));
                }
                if (checkSequence(sb1.toString()) || checkSequence(sb2.toString())) {
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean checkSequence(String sequence) {
        for (char base : BASES) {
            if (sequence.equals(new String(new char[MIN_SEQUENCE_LENGTH]).replace('\0', base))) {
                return true;
            }
        }
        return false;
    }

}

