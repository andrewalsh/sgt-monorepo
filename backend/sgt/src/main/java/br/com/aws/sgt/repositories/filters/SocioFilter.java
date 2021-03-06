package br.com.aws.sgt.repositories.filters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import br.com.aws.sgt.domain.Pessoa;
import br.com.aws.sgt.domain.Pessoa_;
import br.com.aws.sgt.domain.Socio;
import br.com.aws.sgt.domain.Socio_;

public class SocioFilter implements FiltroAbstrato<Socio>{
	
	private String cpfSelecionado;
	private int page = 0;
	private int size = 10;

	@Override
	public Selection[] configureProjection(CriteriaBuilder cb, Root<Socio> root) {
		Join<Socio, Pessoa> joinPessoa = root.join(Socio_.PESSOA, JoinType.INNER);
		List<Selection> selections = new ArrayList<>();
		
		selections.add(joinPessoa.get(Pessoa_.NOME));
		selections.add(joinPessoa.get(Pessoa_.CPF));
		
		return selections.toArray(new Selection[selections.size()]);
	}

	@Override
	public Predicate[] configureWhereClausule(CriteriaBuilder cb, Root<Socio> root) {
		List<Predicate> predicates = new ArrayList<>();
		Join<Socio, Pessoa> joinPessoa = root.join(Socio_.PESSOA, JoinType.INNER);
		
		if(Objects.nonNull(getCpfSelecionado())){
			predicates.add(cb.equal(joinPessoa.get(Pessoa_.CPF), getCpfSelecionado()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	@Override
	public List<Order> orderBy(CriteriaBuilder cb, Root<Socio> root) {
		List<Order> orderList = new ArrayList<>();
		return new ArrayList<>();
	}
	
	@Override
	public Expression<?>[] groupBy(CriteriaBuilder cb, Root<Socio> root) {
		List<Expression<?>> expressions = new ArrayList<>();
		// TODO Auto-generated method stub
		return expressions.toArray(new Expression<?>[expressions.size()]);
	}

	public String getCpfSelecionado() {
		return cpfSelecionado;
	}
	public void setCpfSelecionado(String cpfSelecionado) {
		this.cpfSelecionado = cpfSelecionado;
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
