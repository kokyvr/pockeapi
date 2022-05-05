package com.pokedex.service;

import com.pokedex.dto.EvolvesToDto;
import com.pokedex.dto.PokemonSpeciesDto;
import com.pokedex.dto.ResponseUrlChainDto;

public interface PokemonService {

	PokemonSpeciesDto findPokemons(String url);
	
	ResponseUrlChainDto getObjetPokemonEspecies(String url);
	
	
	EvolvesToDto getEvolvesToDto(String url);
	
}
