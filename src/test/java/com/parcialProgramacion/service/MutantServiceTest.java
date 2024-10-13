package com.parcialProgramacion.service;

import com.parcialProgramacion.controller.Dto.MutantRequest;
import com.parcialProgramacion.controller.Dto.MutantResponse;
import com.parcialProgramacion.model.MutantEntity;
import com.parcialProgramacion.service.repository.MutantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class MutantServiceTest {
    @Mock
    private MutantRepository mutantRepository;
    @InjectMocks
    private MutantService mutantService;
    private List<MutantEntity> mockMutants;

    @BeforeEach
    void setUp() {

        mockMutants = Arrays.asList(
                MutantEntity.builder().isMutant(true).isHuman(false).DNA("Test1").build(),   // Mutante
                MutantEntity.builder().isMutant(false).isHuman(true).DNA("Test2").build(),  // Humano
                MutantEntity.builder().isMutant(true).isHuman(false).DNA("Test3").build()    // Mutante
        );
    }

    @Test
    void whenTheSequenceIsNotMutantWillReturnFalse() {
        List<String> mutantSecuence = Arrays.asList(
                "ATCGGA",
                "CGGTGC",
                "TTATAT",
                "AGATGG",
                "CCTCTA",
                "TCACTG"
        );


        MutantRequest mutantRequest = MutantRequest.builder()
                .dnaSequence(mutantSecuence)
                .build();
        when(mutantRepository.save(any(MutantEntity.class))).thenReturn(new MutantEntity());
        boolean responseService = mutantService.isMutant(mutantRequest);

        assertFalse(responseService);
        verify(mutantRepository).save(any(MutantEntity.class));
    }

    @Test
    void whenTheSequenceIsMutantWillReturnTrue() {
        List<String> mutantSecuence = Arrays.asList(
                "ATCGGA",
                "CGGTGC",
                "TTATGT",
                "AGATGG",
                "CCTCTA",
                "TCACTG"
        );


        MutantRequest mutantRequest = MutantRequest.builder()
                .dnaSequence(mutantSecuence)
                .build();
        when(mutantRepository.save(any(MutantEntity.class))).thenReturn(new MutantEntity());
        boolean responseService = mutantService.isMutant(mutantRequest);

        assertTrue(responseService);
        verify(mutantRepository).save(any(MutantEntity.class));
    }
    @Test
    void stats_whenDataExists_returnsCorrectResponse() {

        when(mutantRepository.findAll()).thenReturn(mockMutants);

        MutantResponse response = mutantService.stats();

        assertEquals(1, response.getCount_human_dna());     // human
        assertEquals(2, response.getCount_mutant_dna());    // mutant
        assertEquals(2.0, response.getRatio());             // Ratio: 2 mutant / 1 human

        // Verify if the repository was called
        verify(mutantRepository).findAll();
    }

    @Test
    void stats() {
    }
}