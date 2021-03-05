package br.edu.pucgoias;

import br.edu.pucgoias.domain.entity.Cliente;
import br.edu.pucgoias.domain.repositorio.ClientesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClientesDao clientesDao){
        return args -> {


            System.out.println("***********SALVANDO CLIENTES");
            clientesDao.save(new Cliente(null,"LUDMILLA REIS PINHEIRO"));

            clientesDao.save(new Cliente(null,"KETELLIN FREITAS NASCIMENTO"));

            System.out.println("***********EXISTE O CLIENTE:");
            System.out.println("KETELLIN FREITAS NASCIMENTO?");
            boolean existe = clientesDao.existsByNome("KETELLIN FREITAS NASCIMENTO");
            System.out.println(existe);

            List<Cliente> clientes = clientesDao.enontrarPorNome("%EI%");
            clientes.forEach(System.out::println);
            if(clientes.isEmpty())
                System.out.println("Lista vazia");
            else {
                for (Cliente cli: clientes){
                    System.out.println(cli);
                }
            }


        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
