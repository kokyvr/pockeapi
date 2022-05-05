package com.pokedex.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Chain {
	private List<EvolvesTo> evolves_to;
	
	private boolean is_baby;
	
	private Species species;
	
	private List<EvolutionDetails> evolution_details;
}
