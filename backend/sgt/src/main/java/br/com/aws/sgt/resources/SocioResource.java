package br.com.aws.sgt.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aws.sgt.dto.SocioDTO;
import br.com.aws.sgt.repositories.filters.SocioFilter;
import br.com.aws.sgt.service.SocioService;
import io.swagger.annotations.Api;

@Api(value = "Socios", description = "Socios")
@RequestMapping("/socios")
@RestController
public class SocioResource {
	
	@Autowired
	private SocioService service;
	
	@GetMapping("/paged")
	public ResponseEntity<Page<SocioDTO>> pagedList(
			@RequestBody SocioFilter filter,
			@RequestParam(required = false) String cpf,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size){
		
		filter.setCpfSelecionado(cpf);
		
		return ResponseEntity.ok().body(service.pagedList(filter, page, size));
	}
}
