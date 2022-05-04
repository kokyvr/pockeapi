package com.pokedex.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonSpeciesDto {
	
	private int count;
	
	private String next;
	
	private String previous;
	
	private List<ResultDto> results;
	
	
}
