package biblioteca.biblio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        int idint = 1;
        try {
            idint = Integer.parseInt(id);
            Livro livro = biblioteca.buscarLivroId(idint);
            if (livro == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(livro);
        } catch (NumberFormatException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> atrasados() {
        ArrayList<String> livrosAtrasados = new ArrayList<>();
        for (Livro livro : biblioteca.livros) {
            if (livro.getUsername() != null && livro.isAtrasado()) {
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
        Livro livro = biblioteca.buscarLivroId(id);
        try {
            if (!usuario.isCliente()) {
                return ResponseEntity.notFound().build();
            }
            if (usuario.temLivroAtrasado()) {
                return ResponseEntity.ok("Devido a existencia de livros atrasados, esta função está indisponivel!");
            }
            if (livro.getDisponibilidade()) {
                usuario.alugarLivro(livro);
                livro.setUsername(usuario.getUsername());
                livro.setDataDevolucao(LocalDate.now().plusDays(tempo));
                return ResponseEntity.ok("ok");
            } else {
                return ResponseEntity.ok("Livro indisponivel!");
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/devolver/{id}")
    public ResponseEntity<?> devolver(@PathVariable int id, @RequestBody Usuario user) {
        Usuario usuario = biblioteca.buscarUsuario(user.getUsername());
        Livro livro = biblioteca.buscarLivroId(id);

        try {
            if (livro.isAtrasado()) {
                return ResponseEntity.ok("Livro atrasado, por favor vá para a pagina de multas.");
            }

            livro.setUsername(null);
            usuario.devolverLivro(livro, biblioteca);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/devolver-atrasado/")
    public ResponseEntity<?> devolverAtrasado(@RequestBody Usuario user) {
        Usuario usuario = biblioteca.buscarUsuario(user.getUsername());
        try {
            List<Livro> livrosAlugados = new ArrayList<>(usuario.pegarLivrosAlugados());
            for (Livro l : livrosAlugados) {
                if (l.isAtrasado()) {
                    l.setUsername(null);
                    usuario.devolverLivro(l, biblioteca);
                }
            }
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/review/{id}")
    public ResponseEntity<?> fazerReview(@PathVariable int id, @RequestBody Review review) {
        Usuario usuario = biblioteca.buscarUsuario(review.getReviewerUsername());

        try {
            for (Livro l : usuario.pegarLivrosDevolvidos()) {
                if (l.getId() == id) {
                    Livro livro = biblioteca.buscarLivroId(id);
                    livro.reviews.add(review);
                    return ResponseEntity.ok("ok");
                }
            }

            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/reservar/{id}")
    public ResponseEntity<?> fazerReserva(@PathVariable int id, @RequestBody Usuario user) {
        Usuario usuario = biblioteca.buscarUsuario(user.getUsername());

        try {
            if (usuario.temLivroAtrasado()) {
                return ResponseEntity.ok("Devido a existencia de livros atrasados, esta função está indisponivel!");
            }
            Livro livro = biblioteca.buscarLivroId(id);
            if (livro.getDisponibilidade()) {
                return ResponseEntity.ok("O livro está disponivel!");
            }
            return ResponseEntity.ok(usuario.reservar(livro));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("busca/{search}")
    public ResponseEntity<?> busca(@PathVariable String search) {
        ArrayList<Livro> livros = new ArrayList<>();
        for (Livro livro : biblioteca.livros) {
            if (livro.getTitulo().contains(search)) {
                livros.add(livro);
            }
        }
        return ResponseEntity.ok(livros);
    }

    @PutMapping("/remover-review/{id}")
    public ResponseEntity<String> removerReview(@PathVariable int id, @RequestBody Usuario user) {
        Livro livro = biblioteca.buscarLivroId(id);
        try {
            int index = -1;
            for (Review r : livro.reviews) {
                if (r.getReviewerUsername().equals(user.getUsername())) {
                    index = livro.reviews.indexOf(r);
                }
            }
            if (index == -1) {
                return ResponseEntity.notFound().build();
            }

            livro.reviews.remove(index);
            System.out.println("Review removida com sucesso.");
            return ResponseEntity.ok("Review removida com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
}
