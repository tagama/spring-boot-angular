package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Y31V on 03/06/2014.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaFisica  {
    private String nome;
    private String rg;
    private String cpf;
    private Date nascimento;
}
