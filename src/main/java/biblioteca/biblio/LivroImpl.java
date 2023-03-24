package biblioteca.biblio;

import org.springframework.http.ResponseEntity;
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
}
