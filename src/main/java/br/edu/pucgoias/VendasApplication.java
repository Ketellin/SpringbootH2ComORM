package br.edu.pucgoias;

import br.edu.pucgoias.domain.entity.Cliente;
import br.edu.pucgoias.domain.repository.ClientesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired ClientesDao clientesDao){
        return args -> {
            Cliente cliente = new Cliente(null,"KETELLIN FREITAS");
            clientesDao.save(cliente);
        };
    }

    public static void main(String[] args) {

        SpringApplication.run(VendasApplication.class, args);
    }
}
