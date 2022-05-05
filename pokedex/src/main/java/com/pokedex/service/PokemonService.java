package com.pokedex.service;

import com.pokedex.dto.PokemonSpeciesDto;

public interface PokemonService {

	PokemonSpeciesDto findPokemons(String url);
}
