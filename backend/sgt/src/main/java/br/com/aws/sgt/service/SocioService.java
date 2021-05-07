package br.com.aws.sgt.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.aws.sgt.dto.SocioDTO;
import br.com.aws.sgt.repositories.filters.SocioFilter;
import br.com.aws.sgt.repositories.natives.SocioRepository;

@Service
public class SocioService implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private SocioRepository socioRepository;
	
	public Page<SocioDTO> pagedList(SocioFilter filter, int page, int size){
		Pageable paginacao = PageRequest.of(page, size);
		return socioRepository.pagedList(filter, paginacao);
	}

}
