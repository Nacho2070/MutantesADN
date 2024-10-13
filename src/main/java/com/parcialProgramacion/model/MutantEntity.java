package com.parcialProgramacion.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MutantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mutantId;

    @NonNull
    @Column(name = "dna")
    private String DNA;
    private Boolean isMutant = false;
    private Boolean isHuman = false;
}
