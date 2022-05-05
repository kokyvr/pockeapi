package com.pokedex.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pokedex.dto.EvolvesTo;
import com.pokedex.dto.EvolvesToDto;
import com.pokedex.dto.PaginationDto;
import com.pokedex.dto.PokemonSpeciesDto;
import com.pokedex.dto.ResultDto;
import com.pokedex.dto.Species;
import com.pokedex.service.PokemonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PokemonController {

	@Autowired
	private PokemonService pokemonService;

	@Value("${url-pokedex.principal}")
	private String urlprincipal;

	@Value("${url-pokedex.pokemonss-existing}")
	private String urlspecimens;
	/*
	 * @PostMapping public ResponseEntity<PokemonSpeciesDto> getPokemon(){
	 * PokemonSpeciesDto pokemonDto = new PokemonSpeciesDto(); try {
	 * 
	 * //pokemonDto = this.pokemonService.findPokemons(0, 0); } catch (Exception e)
	 * { e.printStackTrace(System.out); }
	 * 
	 * 
	 * return new ResponseEntity<PokemonSpeciesDto>(pokemonDto,HttpStatus.OK); }
	 */
	/*
	 * @PostMapping(path = "/especies") public ResponseEntity<EvolvesToDto>
	 * getPokomenEspecies(@RequestParam String url){ EvolvesToDto dto = new
	 * EvolvesToDto(); log.info("ruta {}",url); try { dto =
	 * this.pokemonService.getEvolvesToDto(url); } catch (Exception e) { // TODO:
	 * handle exception e.printStackTrace(System.out); }
	 * 
	 * return new ResponseEntity<EvolvesToDto>(dto,HttpStatus.OK); }
	 */
	private List<Species> species = new ArrayList<Species>();

	@GetMapping
	public String listPokemons(Model model) {
		PokemonSpeciesDto pokemonDto = new PokemonSpeciesDto();
		PaginationDto paginationDto = new PaginationDto();
		String url = urlprincipal + urlspecimens;
		pokemonDto = this.pokemonService.findPokemons(url);
		paginationDto.setNext(pokemonDto.getNext());
		paginationDto.setPrevious(pokemonDto.getPrevious());
		model.addAttribute("pokemons", pokemonDto.getResults());
		model.addAttribute("count", pokemonDto.getResults().size());
		model.addAttribute("paginationDto", paginationDto);
		return "listPokemons";
	}

	@PostMapping
	public String showPokemons(@ModelAttribute PaginationDto pagination, Model model,
			@RequestParam(value = "action", required = true) String action) {
		log.info("action {}", action);
		String url = action;
		switch (url) {
		case "Antes":
			url = pagination.getPrevious();
			break;

		case "Siguiente":
			url = pagination.getNext();
			break;
		}

		PokemonSpeciesDto pokemonDto = new PokemonSpeciesDto();
		PaginationDto paginationDto = new PaginationDto();
		pokemonDto = this.pokemonService.findPokemons(url);
		paginationDto.setNext(pokemonDto.getNext());
		paginationDto.setPrevious(pokemonDto.getPrevious());
		model.addAttribute("pokemons", pokemonDto.getResults());
		model.addAttribute("count", pokemonDto.getResults().size());
		model.addAttribute("paginationDto", paginationDto);

		return "listPokemons";
	}

	@PostMapping("evolucion")
	public String evolucion(@ModelAttribute("pokemon") ResultDto pokemon, Model model) {
		this.species = new ArrayList<Species>();
		EvolvesToDto EvolvesToDto = this.pokemonService.getEvolvesToDto(pokemon.getUrl());
		log.info("url {}", pokemon.getUrl());

		this.species = getEvo(EvolvesToDto.getChain().getEvolves_to());

		model.addAttribute("especie", EvolvesToDto.getChain().getSpecies());

		model.addAttribute("especies", this.species);
		log.info("especies {}", species.toString());
		// model.addAttribute("chain",EvolvesToDto.getChain());
		return "verespecimenes";
	}

	private List<Species> getEvo(List<EvolvesTo> ev) {

		if (ev.size() > 0) {
			for (EvolvesTo evt : ev) {
				this.species.add(evt.getSpecies());
				getEvo(evt.getEvolves_to());
				// log.info("especies {}",species);

			}
		}
		return this.species;

	}

}
