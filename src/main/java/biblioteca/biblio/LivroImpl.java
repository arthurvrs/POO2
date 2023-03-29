package biblioteca.biblio;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livro")
public class LivroImpl implements MainController<Livro> {
    @Override
    public ResponseEntity<?> cadastro(Livro livro) {
        biblioteca.cadastrarLivro(livro);
        return ResponseEntity.ok("ok");
    }

    @Override
    public ResponseEntity<?> listarObjetos() {
        return ResponseEntity.ok(biblioteca.livros);
    }

    @Override
    public ResponseEntity<?> pegarObjeto(String id) {

        int idint = Integer.parseInt(id);
        return ResponseEntity.ok(biblioteca.buscarLivroId(idint));
    }

    @Override
    public ResponseEntity<?> atrasados() {
        ArrayList<String> livrosAtrasados = new ArrayList<>();
        for (Livro livro : biblioteca.livros) {
            if (livro.isAtrasado()) {
                Usuario usuario = biblioteca.buscarUsuario(livro.getUsername());

                livrosAtrasados.add("Livro: " + livro.getTitulo() + ",id: " + livro.getId() + ", data devolução: "
                        + livro.getDataDevolucao() + ", usuario: " + usuario.getUsername() + ", forma de contato: "
                        + usuario.getContato() + ".");
            }
        }
        return ResponseEntity.ok(livrosAtrasados);
    }

    @PostMapping("/alugar/{id}/{tempo}")
    public ResponseEntity<?> alugarLivro(@PathVariable int id, @PathVariable int tempo, @RequestBody Usuario user) {

        Usuario usuario = biblioteca.buscarUsuario(user.getUsername());

        if (usuario == null || !usuario.isCliente()) {
            return ResponseEntity.ok("Usuario Invalido");
        }

        if (usuario.temLivroAtrasado()) {
            return ResponseEntity.ok("Devido a existencia de livros atrasados, esta função está indisponivel!");
        }
        Livro livro = biblioteca.buscarLivroId(id);

        if (livro == null) {
            return ResponseEntity.ok("Livro invalido");
        }

        if (livro.getDisponibilidade()) {
            usuario.alugarLivro(livro);
            livro.setUsername(usuario.getUsername());
            livro.setDataDevolucao(LocalDate.now().plusDays(tempo));
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.ok("Livro indisponivel!");
        }
    }

    @PostMapping("/devolver/{id}")
    public ResponseEntity<?> devolver(@PathVariable int id, @RequestBody Usuario user) {
        Usuario usuario = biblioteca.buscarUsuario(user.getUsername());
        if (usuario == null || !usuario.isCliente()) {
            return ResponseEntity.ok("Usuario Invalido");
        }
        Livro livro = biblioteca.buscarLivroId(id);
        if (livro == null) {
            return ResponseEntity.ok("Livro invalido");
        }

        if (livro.isAtrasado()) {
            return ResponseEntity.ok("Livro atrasado");
        }

        livro.setUsername(null);
        usuario.devolverLivro(livro, biblioteca);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/devolver-atrasado/{id}")
    public ResponseEntity<?> devolverAtrasado(@PathVariable int id, @RequestBody Usuario user) {
        Usuario usuario = biblioteca.buscarUsuario(user.getUsername());
        if (usuario == null || !usuario.isCliente()) {
            return ResponseEntity.ok("Usuario Invalido");
        }
        Livro livro = biblioteca.buscarLivroId(id);
        if (livro == null || !livro.isAtrasado()) {
            return ResponseEntity.ok("Livro invalido");
        }

        livro.setUsername(null);
        usuario.devolverLivro(livro, biblioteca);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/review/{id}")
    public ResponseEntity<?> fazerReview(@PathVariable int id, @RequestBody Review review) {
        Livro livro = biblioteca.buscarLivroId(id);
        livro.reviews.add(review);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/reservar/{id}")
    public ResponseEntity<?> fazerReserva(@PathVariable int id, @RequestBody Usuario user) {
        Usuario usuario = biblioteca.buscarUsuario(user.getUsername());

        if (usuario == null || !usuario.isCliente()) {
            return ResponseEntity.ok("Usuario Invalido");
        }

        if (usuario.temLivroAtrasado()) {
            return ResponseEntity.ok("Devido a existencia de livros atrasados, esta função está indisponivel!");
        }
        Livro livro = biblioteca.buscarLivroId(id);
        return ResponseEntity.ok(usuario.reservar(livro));
    }

    @GetMapping("busca/{search}")
    public ResponseEntity<?> busca(@PathVariable String search){
        ArrayList<Livro> livros = new ArrayList<>();
        for (Livro livro: biblioteca.livros) {
            if (livro.getTitulo().contains(search)) {
                livros.add(livro);
            }
        }
        return ResponseEntity.ok(livros);
    }
}
