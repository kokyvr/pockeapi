package com.pokedex.service;

import java.util.List;

import com.pokedex.dto.PokemonSpeciesDto;

public interface PokemonService {


	
	 PokemonSpeciesDto findPokemons(int offset,int limit);
}
