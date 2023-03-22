package biblioteca.biblio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.annotation.PostConstruct;

public interface MainController<T> {
    static Biblioteca biblioteca = new Biblioteca();

    @PostConstruct
    public default void iniciar() {
        biblioteca.criarAdmin("admin", "admin", "admin@biblioteca.com");
        biblioteca.criarUsuario("user", "user", "user@email.com");

        biblioteca.cadastrarLivro(new Livro("Álgebra Linear e Aplicações", "Carlos A. Callioli",
                "https://m.media-amazon.com/images/I/814pZppLkPL.jpg",
                "Atual Editora", "1997",
                "O livro, dividido em duas partes, apresenta a teoria, ou seja, as idéias centrais da álgebra linear e algumas aplicações destes conceitos."));
        biblioteca.cadastrarLivro(new Livro("Álgebra Linear e Aplicações", "Carlos A. Callioli",
                "https://m.media-amazon.com/images/I/814pZppLkPL.jpg",
                "Atual Editora", "1997",
                "O livro, dividido em duas partes, apresenta a teoria, ou seja, as idéias centrais da álgebra linear e algumas aplicações destes conceitos."));
        biblioteca.cadastrarLivro(new Livro("Álgebra Linear e Aplicações", "Carlos A. Callioli",
                "https://m.media-amazon.com/images/I/814pZppLkPL.jpg",
                "Atual Editora", "1997",
                "O livro, dividido em duas partes, apresenta a teoria, ou seja, as idéias centrais da álgebra linear e algumas aplicações destes conceitos."));
        }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastro(@RequestBody T tipo);

    @GetMapping("/getall")
    public ResponseEntity<?> getAll ();
}