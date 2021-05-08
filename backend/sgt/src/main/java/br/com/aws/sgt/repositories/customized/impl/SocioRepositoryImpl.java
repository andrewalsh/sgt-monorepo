package br.com.aws.sgt.repositories.customized.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.aws.sgt.domain.Socio;
import br.com.aws.sgt.dto.SocioDTO;
import br.com.aws.sgt.repositories.filters.FiltroAbstrato;

@Service
public class SocioRepositoryImpl extends GenericRepositoryImpl<Socio, SocioDTO>{

	private static final long serialVersionUID = 1L;

	public SocioRepositoryImpl() {
		super(Socio.class, SocioDTO.class);
	}

	@Override
	public Page<SocioDTO> pagedList(FiltroAbstrato<Socio> filtroAbstrato, Pageable pageable) {
		return super.pagedList(filtroAbstrato, pageable);
	}
}
