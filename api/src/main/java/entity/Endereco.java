package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Y31V on 03/06/2014.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Endereco {

    private String cep;
    private String logradouro;
    private String numeroComplemento;
    private String bairro;

    @DBRef
    private Cidade cidade;

}
