package entity.repository;

import entity.Cidade;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Y31V on 04/06/2014.
 */
@RepositoryRestResource//(collectionResourceRel = "people", path = "people")
public interface CidadeRepository extends PagingAndSortingRepository<Cidade, BigInteger> {

    public List<Cidade> findByEstadoIgnoreCase(@Param("estado") String name);


    Long countByNomeAndEstadoAllIgnoreCase(@Param("nome")String nome, @Param("estado")String estado);

    Cidade save(Cidade cidade);
}
