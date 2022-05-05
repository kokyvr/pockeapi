package com.pokedex.service;

import com.pokedex.dto.ResponseUrlChainDto;
import com.pokedex.dto.EvolvesToDto;
import com.pokedex.dto.PokemonSpeciesDto;
import com.pokedex.dto.Ruta;

public interface PokemonService {

	PokemonSpeciesDto findPokemons(String url);
	
	ResponseUrlChainDto getObjetPokemonEspecies(Ruta url);
	
	
	EvolvesToDto getEvolvesToDto(Ruta url);
	
}
