package biblioteca.biblio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioImpl implements MainController<Usuario> {
    @Override
    public ResponseEntity<?> cadastro(Usuario usuario) {
        if (biblioteca.buscarUsuario(usuario.username) == null) {
            biblioteca.criarUsuario(usuario.username, usuario.senha, usuario.contato);
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.ok("usuario");
        }
    }

    @Override
    public ResponseEntity<?> listarObjetos() {
        return ResponseEntity.ok(biblioteca.usuarios);
    }

    @Override
    public ResponseEntity<?> pegarObjeto(String id) {
        Usuario usuario = biblioteca.buscarUsuario(id);
        return ResponseEntity.ok(usuario);
    }

    @Override
    public ResponseEntity<?> atrasados() {
        ArrayList<String> usuariosAtrasados = new ArrayList<>();
        for (Usuario usuario: biblioteca.usuarios) {
            if(usuario.temLivroAtrasado()){
                usuariosAtrasados.add("Usuario: " + usuario.getUsername() + ", forma de contato: " + usuario.getContato() + ".");
            }
        }
        return ResponseEntity.ok(usuariosAtrasados);
    }

    @GetMapping("livrosAlugados/{username}")
    public ResponseEntity<?> getLivrosAlugados(@PathVariable String username) {
        Usuario usuario = biblioteca.buscarUsuario(username);

        if (usuario == null || !usuario.isCliente()) {
            return ResponseEntity.ok("usuario invalido");
        }

        return ResponseEntity.ok(usuario.pegarLivrosAlugados());

    }

    @GetMapping("livrosDevolvidos/{username}")
    public ResponseEntity<?> getLivrosDevolvidos(@PathVariable String username) {
        Usuario usuario = biblioteca.buscarUsuario(username);

        if (usuario == null || !usuario.isCliente()) {
            return ResponseEntity.ok("usuario invalido");
        }

        return ResponseEntity.ok(usuario.pegarLivrosDevolvidos());

    }

    @PostMapping("/login")
    public ResponseEntity<?> tentarLogar(@RequestBody Usuario user) {

        Usuario usuario = biblioteca.login(user.getUsername(), user.getSenha());

        if (usuario == null) {
            System.out.println("Usuario ou senha incorretas");
            return ResponseEntity.ok("nenhum");

        }

        if (usuario.isCliente()) {
            return ResponseEntity.ok("user");
        } else {
            usuario.baterPontoEntrada();
            return ResponseEntity.ok("admin");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> sair(@RequestBody Usuario user) {
        Usuario usuario = biblioteca.buscarUsuario(user.username);

        if (usuario != null && !usuario.isCliente()) {
            usuario.baterPontoSaida();
        }
        return ResponseEntity.ok("ok");

    }

    @PostMapping("cadastro-admin")
    public ResponseEntity<?> cadastroAdmin(@RequestBody Usuario usuario) {
        System.out.println(usuario.username);
        if (biblioteca.buscarUsuario(usuario.username) == null) {
            biblioteca.criarAdmin(usuario.username, usuario.senha, usuario.contato);
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.ok("usuario");
        }
    }

    @GetMapping("/multa/{username}")
    public ResponseEntity<?> multa(@PathVariable String username) {
        Usuario usuario = biblioteca.buscarUsuario(username);
        ArrayList<String> multas = new ArrayList<>();
        if (usuario == null || !usuario.isCliente()) {
            return ResponseEntity.ok(multas);
        }

        ArrayList<Livro> livrosAlugados = usuario.pegarLivrosAlugados();
        for (Livro livro : livrosAlugados) {
            if (livro.isAtrasado()) {
                LocalDate hoje = LocalDate.now();
                long atraso = ChronoUnit.DAYS.between(livro.dataDevolucao, hoje);

                multas.add("Livro: " + livro.getTitulo()+ ",id: " + livro.getId() + ", data devolução: " + livro.dataDevolucao + ", multa de R$: " + (5 + atraso * .75) + " .");
            }
        }
        return ResponseEntity.ok(multas);
    }
}
