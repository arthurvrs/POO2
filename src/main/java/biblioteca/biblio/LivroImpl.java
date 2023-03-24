package biblioteca.biblio;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livro")
public class LivroImpl implements MainController<Livro>{    
    @Override
    public ResponseEntity<?> cadastro(Livro livro) {
        biblioteca.cadastrarLivro(livro);
        return ResponseEntity.ok("ok");
    }

    @Override
    public ResponseEntity<?> listarObjetos() {
        System.out.println("teste");
        return ResponseEntity.ok(biblioteca.livros);
    }
    
    @Override
    public ResponseEntity<?> pegarObjeto(String id) {

        int idint = Integer.parseInt(id);
        return ResponseEntity.ok(biblioteca.buscarLivroId(idint));
    }

    @PostMapping("/alugar/:{id}/:{username}")
    public ResponseEntity<?> alugarLivro(@RequestBody LocalDate data, @PathVariable("id") int id, @PathVariable("username") String username) {
        Cliente cliente = biblioteca.buscarCliente(username);

        for(Livro l: cliente.livrosAlugados) {
            if(l.isAtrasado()) {
                ResponseEntity.ok("Devido a existencia de livros atrasados, esta função está indisponivel!");
            }
        }

        Livro livro = biblioteca.buscarLivroId(id);

        if (livro.getDisponibilidade()) {
            cliente.alugarLivro(livro);
            livro.dataDevolucao = data;
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.ok("Livro indisponivel!");
        }
    }
}
