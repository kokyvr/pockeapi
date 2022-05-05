package com.pokedex.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvolvesTo {

	private boolean is_baby;
	
	private Species species;
	
	private List<EvolvesTo> evolves_to;
	
	private List<EvolutionDetails> evolution_details;
}
