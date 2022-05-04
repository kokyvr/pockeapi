package com.pokedex.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pokedex.config.RestTemplateClass;
import com.pokedex.dto.PokemonSpeciesDto;
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
	

	
	@Override
	public PokemonSpeciesDto findPokemons(int offset, int limit) {
		// TODO Auto-generated method stub
		String url = this.urlprincipal + urlspecimens;
		
		return getListPokemonsExistens(url,offset, limit);
	}
	
	private  PokemonSpeciesDto getListPokemonsExistens(String url, int offset,int limit){
		ResponseEntity<PokemonSpeciesDto> responseEntity=null;
		PokemonSpeciesDto pokemonDto = new PokemonSpeciesDto();
		try {
			if(offset >0 || limit >0) {
				 responseEntity =restTemplate.getForEntity(url+"?offsett="+offset+"&limit="+limit, PokemonSpeciesDto.class);
				 pokemonDto = responseEntity.getBody();
				 log.info("with params : {}",responseEntity.getBody());
				
				
			}else {
				  responseEntity =restTemplate.getForEntity(url, PokemonSpeciesDto.class);
				  pokemonDto = responseEntity.getBody();
				  log.info("with out params : {}",responseEntity.getBody());
				  
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

		
			return pokemonDto;
		
	
	}

}
