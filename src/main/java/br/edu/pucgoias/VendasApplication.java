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

            List<Cliente> todosClientes = clientesDao.findAll();
            todosClientes.forEach(System.out::println);

            System.out.println("***********ATUALIZANDO CLIENTES");
            todosClientes.forEach(cliente -> {
                cliente.setNome(cliente.getNome() + " atualizado.");
                clientesDao.save(cliente);
            });

            todosClientes = clientesDao.findAll();
            todosClientes.forEach(System.out::println);

            System.out.println("***********CONSULTANDO CLIENTES POR NOME");
            clientesDao.findByNomeLike("REIS").forEach(System.out::println);

            System.out.println("***********DELETAR TODOS");
            clientesDao.findAll().forEach(c -> {
                clientesDao.delete(c);
            });
            todosClientes = clientesDao.findAll();
            if(todosClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado!!!");
            }
            else{
                todosClientes.forEach(System.out::println);
            }

            System.out.println("***********SALVANDO CLIENTES");
            clientesDao.save(new Cliente(null,"LUDMILLA REIS PINHEIRO"));

            clientesDao.save(new Cliente(null,"KETELLIN FREITAS NASCIMENTO"));
            System.out.println("***********EXISTE O CLIENTE:");
            System.out.println("KETELLIN FREITAS NASCIMENTO?");
            boolean existe = clientesDao.existsByNome("KETELLIN FREITAS NASCIMENTO");
            System.out.println(existe);


        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
