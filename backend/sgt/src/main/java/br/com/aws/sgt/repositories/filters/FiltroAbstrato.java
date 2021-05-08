package br.com.aws.sgt.repositories.filters;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

public interface FiltroAbstrato<T>{
	
	@SuppressWarnings("rawtypes")
	Selection[] configureProjection(CriteriaBuilder cb, Root<T> root);
	
	Predicate[] configureWhereClausule(CriteriaBuilder cb, Root<T> root);
	
	List<Order> orderBy(CriteriaBuilder cb, Root<T> root);
	
	Expression<?>[] groupBy(CriteriaBuilder cb, Root<T> root);
	
	default String paramLikeFormated(String param) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("%");
		sb.append(param);
		sb.append("%");
		
		return sb.toString();
	}

}
