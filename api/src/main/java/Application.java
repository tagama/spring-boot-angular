import entity.Cidade;
import entity.Cliente;
import entity.Endereco;
import entity.PessoaJuridica;
import entity.repository.CidadeRepository;
import entity.repository.ClientRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Calendar;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableMongoRepositories
public class Application extends SpringBootServletInitializer {
    @Resource
    private ClientRepository clientRepository;

    @Resource
    private CidadeRepository cidadeRepository;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @PostConstruct
    public void createEmpresa() {
        Cliente cliente = new Cliente();
        PessoaJuridica pessoaJuridica = new PessoaJuridica("razão", "fantasia", "1231.2131.3123/1231", "123141231234", "12389712391", Calendar.getInstance().getTime(), "www.thiagogama.com");
        cliente.setPessoaJuridica(pessoaJuridica);
        Cidade cidade = new Cidade("RiodeJaneiro", "RJ");
        cidadeRepository.save(cidade);
        cliente.setEndereco(new Endereco("22290-140", "Av Venceslau Bras","18/402", "botafogo", cidade));
        clientRepository.save(cliente);
    }




    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
