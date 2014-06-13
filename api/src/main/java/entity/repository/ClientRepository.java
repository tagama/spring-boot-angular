package entity.repository;

import entity.Cliente;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Y31V on 02/06/2014.
 */
//@org.springframework.stereotype.Repository
@RepositoryRestResource
public interface ClientRepository extends PagingAndSortingRepository<Cliente, BigInteger> {

    public List<Cliente> findByEnderecoCidadeEstadoAndEnderecoCidadeNomeAllIgnoreCase(@Param("estado") String estado, @Param("nome") String nome);
    public List<Cliente> findByEnderecoCidadeEstadoAllIgnoreCase(@Param("estado") String name);
    public List<Cliente> findByEnderecoCidadeEstado(@Param("estado") String name);

}
