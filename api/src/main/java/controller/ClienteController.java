package controller;

import entity.Cliente;
import entity.PessoaFisica;
import entity.PessoaJuridica;
import entity.repository.ClientRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Resource
    private ClientRepository clientRepository;

    @RequestMapping("/list")
    public Iterable<Cliente> list() {
       return clientRepository.findAll();
    }

    /*
    Se não incluir org.springframework.validation.Errors erros como parametro ele solta
    MethodArgumentNotValidException se falhar o @Valid. Essa exception pode ser
    capturada em alguma classe com @ControllerAdvice no metodo com
    @ExceptionHandler(MethodArgumentNotValidException.class).

    Se incluir o Errors erros não solta a exception e vc trata como quiser
     */
    @RequestMapping(value = "/pessoaJuridica/save", method = RequestMethod.POST)
    public PessoaJuridica save(@Valid @RequestBody PessoaJuridica cliente) {
        return null;//clientRepository.save(cliente);
    }
    @RequestMapping(value = "/pessoaFisica/save", method = RequestMethod.POST)
    public PessoaFisica save(@RequestBody @Valid PessoaFisica cliente) {
        return null;//clientRepository.save(cliente);
    }


}