package com.pokedex.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import com.pokedex.dto.PokemonSpeciesDto;
import com.pokedex.service.PokemonService;

@Controller
public class PokemonController {

	@Autowired
	private PokemonService pokemonService; 
	
	
	
	@PostMapping
	public ResponseEntity<PokemonSpeciesDto> getPokemon(){
		PokemonSpeciesDto pokemonDto = new PokemonSpeciesDto();
		try {
			
			pokemonDto = this.pokemonService.findPokemons(0, 0);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
		
		return new ResponseEntity<PokemonSpeciesDto>(pokemonDto,HttpStatus.OK);
	}
	
	@GetMapping
	public String listPokemons() {
		
		
		return "listPokemons";
	}
	
}
