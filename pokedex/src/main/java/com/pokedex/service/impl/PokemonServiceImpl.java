package com.pokedex.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pokedex.config.RestTemplateClass;
import com.pokedex.dto.EvolvesToDto;
import com.pokedex.dto.PokemonSpeciesDto;
import com.pokedex.dto.ResponseUrlChainDto;
import com.pokedex.service.PokemonService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PokemonServiceImpl implements PokemonService {

	@Autowired
	private RestTemplateClass restTemplate;

	@Value("${url-pokedex.principal}")
	private String urlprincipal;

	@Value("${url-pokedex.pokemonss-existing}")
	private String urlspecimens;

	public static final int limit = 10;

	@Override
	public PokemonSpeciesDto findPokemons(String url) {
		return getListPokemonsExistens(url);
	}

	private PokemonSpeciesDto getListPokemonsExistens(String url) {
		ResponseEntity<PokemonSpeciesDto> responseEntity = null;
		PokemonSpeciesDto pokemonDto = new PokemonSpeciesDto();
		try {
			responseEntity = restTemplate.getForEntity(url, PokemonSpeciesDto.class);
			pokemonDto = responseEntity.getBody();
			log.info("with params : {}", responseEntity.getBody());

		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

		return pokemonDto;

	}

	@Override
	public ResponseUrlChainDto getObjetPokemonEspecies(String url) {
		// TODO Auto-generated method stub
		
		
		return getPokemonEspecies(url);
	}
	private ResponseUrlChainDto getPokemonEspecies(String url) {
		ResponseEntity<ResponseUrlChainDto> responseentity= null;
		ResponseUrlChainDto evolutionDto = new ResponseUrlChainDto();
		try {
			responseentity = this.restTemplate.getForEntity(url,ResponseUrlChainDto.class);
			log.info("responseentity {}",responseentity);
			evolutionDto = responseentity.getBody();
			log.info("evolutiondto {}",evolutionDto);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
		return evolutionDto;
	}

	@Override
	public EvolvesToDto getEvolvesToDto(String url) {
		ResponseUrlChainDto EvolutionChainDto = this.getObjetPokemonEspecies(url);
		
		ResponseEntity<EvolvesToDto> responseentity = null;
		EvolvesToDto EvolvesToDto = new EvolvesToDto();
		
		try {
			responseentity = this.restTemplate.getForEntity(EvolutionChainDto.getEvolution_chain().getUrl(), EvolvesToDto.class);
			EvolvesToDto = responseentity.getBody();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(System.out);
		}
			
		return EvolvesToDto;
	}

}
