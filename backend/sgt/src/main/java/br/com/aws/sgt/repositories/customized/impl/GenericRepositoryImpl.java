package br.com.aws.sgt.repositories.customized.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.aws.sgt.repositories.customized.repo.GenericCustomRepository;
import br.com.aws.sgt.repositories.filters.FiltroAbstrato;

@Repository
public abstract class GenericRepositoryImpl<T, DTO> implements GenericCustomRepository<T, DTO>, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em;
	
	private Class<DTO> dtoClass;
	private Class<T> entityClass;
	
	protected GenericRepositoryImpl(Class<T> entityClass, Class<DTO> dtoClass) {
		this.entityClass = entityClass;
		this.dtoClass = dtoClass;
	}
	

	@Override
	public Page<DTO> pagedList(FiltroAbstrato<T> filtroAbstrato, Pageable pageable) {
		Page<DTO> result = null;
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<DTO> cq = cb.createQuery(dtoClass);
		Root<T> root = cq.from(entityClass);
		
		cq.select(cb.construct(dtoClass, filtroAbstrato.configureProjection(cb, root)));
		
		cq.where(filtroAbstrato.configureWhereClausule(cb, root));
		
		cq.groupBy(filtroAbstrato.groupBy(cb, root));
		
		cq.orderBy(filtroAbstrato.orderBy(cb, root));
		
		TypedQuery<DTO> query = em.createQuery(cq);
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
		result = new PageImpl<>(query.getResultList(), pageable, total(filtroAbstrato, em));
		
		return result;
	}
	
	private Long total(FiltroAbstrato<T> filter, EntityManager em) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<T> root = cq.from(entityClass);
		
		cq.where(filter.configureWhereClausule(cb, root));
		cq.select(cb.count(root));
		
		return em.createQuery(cq).getSingleResult();
	}

}
