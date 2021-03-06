package br.com.aws.sgt.repositories.natives;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aws.sgt.domain.Socio;
import br.com.aws.sgt.dto.SocioDTO;
import br.com.aws.sgt.repositories.customized.repo.GenericCustomRepository;

@Primary
@Repository
public interface SocioRepository extends JpaRepository<Socio, Long>, GenericCustomRepository<Socio, SocioDTO>{
	
}
