package br.com.aws.sgt.repositories.customized.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.aws.sgt.repositories.filters.FiltroAbstrato;

public interface GenericCustomRepository<T, DTO> {
	
	Page<DTO> pagedList(FiltroAbstrato<T> filtroAbstrato, Pageable pageable);

}
