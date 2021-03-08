package br.edu.pucgoias;

import br.edu.pucgoias.domain.entity.Cliente;
import br.edu.pucgoias.domain.entity.Pedido;
import br.edu.pucgoias.domain.repository.ClientesDao;
import br.edu.pucgoias.domain.repository.PedidoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired ClientesDao clientesDao,
            @Autowired PedidoDao pedidoDao){
        return args -> {


            System.out.println("***********SALVANDO CLIENTES");
            clientesDao.save(new Cliente(null,"LUDMILLA REIS PINHEIRO"));

            Cliente cli = new Cliente(null,"KETELLIN FREITAS NASCIMENTO");
            clientesDao.save(cli);

            Pedido p = new Pedido();
            p.setCliente(cli);
            p.setDataPedido(LocalDate.now()); //LocalDate api de datas do java 8
            p.setTotal(BigDecimal.valueOf(100));
            pedidoDao.save(p);
            Cliente outroCli = clientesDao.findClienteFetchPedidos(cli.getId());
            System.out.println(outroCli);
            System.out.println(p);
            pedidoDao.findByCliente(cli).forEach(System.out::println);

            System.out.println("***********EXISTE O CLIENTE:");
            System.out.println("KETELLIN FREITAS NASCIMENTO?");
            boolean existe = clientesDao.existsByNome("KETELLIN FREITAS NASCIMENTO");
            System.out.println(existe);

            List<Cliente> clientes = clientesDao.enontrarPorNome("%EI%");
            clientes.forEach(System.out::println);
            if(clientes.isEmpty())
                System.out.println("Lista vazia");
            else {
                for (Cliente cli1: clientes){
                    System.out.println(cli1);
                }
            }


        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
