

# Mutante ADN API

### Parcial Programacion



## Endpoints

### 1. **Verificar ADN Mutante** (`POST /mutant/verify`)

#### Request (JSON):
- **URL**: `/mutant/verify`
- **Método**: `POST`
  
##### Ejemplo de ADN mutante:
```json
{
  "dnaSequence": [
    "ATCGGA",
    "CGGTGC",
    "TTATAT",
    "AGATGG",
    "CTTTTA",
    "TCACTG"
  ]
}
```

##### Ejemplo de ADN humano:
```json
{
  "dnaSequence": [
    "ATCGGA",
    "CGGTGC",
    "TTATAT",
    "AGATGG",
    "CCTCTA",
    "TCACTG"
  ]
}
```
---

### 2. **Obtener Estadísticas** (`GET /mutant/stats`)

#### Response (JSON):
```json
{
  "count_mutant_dna": 40,
  "count_human_dna": 100,
  "ratio": 0.4
}
```
