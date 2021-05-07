package br.com.aws.sgt.repositories.customized.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.aws.sgt.dto.SocioDTO;
import br.com.aws.sgt.repositories.filters.SocioFilter;



public interface SocioCustomRepository {

	Page<SocioDTO> pagedList(SocioFilter filter, Pageable pageable);
}
