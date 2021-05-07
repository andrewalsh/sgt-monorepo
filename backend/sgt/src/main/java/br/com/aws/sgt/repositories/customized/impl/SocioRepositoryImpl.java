package br.com.aws.sgt.repositories.customized.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.aws.sgt.domain.Socio;
import br.com.aws.sgt.dto.SocioDTO;
import br.com.aws.sgt.repositories.customized.repo.SocioCustomRepository;
import br.com.aws.sgt.repositories.filters.SocioFilter;

@Repository
public class SocioRepositoryImpl implements SocioCustomRepository{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<SocioDTO> pagedList(SocioFilter filter, Pageable pageable) {
		Page<SocioDTO> result = null;
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<SocioDTO> cq = cb.createQuery(SocioDTO.class);
		Root<Socio> root = cq.from(Socio.class);
		
		cq.select(cb.construct(SocioDTO.class, filter.configureProjection(cb, root)));
		
		cq.where(filter.configureWhereClausule(cb, root));
		
		cq.orderBy(filter.orderBy(cb, root));
		
		TypedQuery<SocioDTO> query = em.createQuery(cq);
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
		result = new PageImpl<>(query.getResultList(), pageable, total(filter, em));
		
		return result;
	}

	private Long total(SocioFilter filter, EntityManager em) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Socio> root = cq.from(Socio.class);
		
		cq.where(filter.configureWhereClausule(cb, root));
		cq.select(cb.count(root));
		
		return em.createQuery(cq).getSingleResult();
	}
	
	

}
