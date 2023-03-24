package biblioteca.biblio;

import org.springframework.http.ResponseEntity;
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
        }else {
            return ResponseEntity.ok("usuario");
        }
    }

    @Override
    public ResponseEntity<?> listarObjetos() {
        return ResponseEntity.ok(biblioteca.clientes);
    }

    @Override
    public ResponseEntity<?> pegarObjeto(String id) {
        Usuario usuario = biblioteca.buscarUsuario(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> tentarLogar(@RequestBody Usuario loginData) {

        Admin admin = biblioteca.buscarAdmin(loginData.getUsername());

        if (admin != null && admin.isSenhaCorreta(loginData.getSenha())) {
            System.out.println("admin");
            return ResponseEntity.ok("admin");
        }

        Cliente cliente = biblioteca.buscarCliente(loginData.getUsername());

        if (cliente != null && cliente.isSenhaCorreta(loginData.getSenha())) {
            System.out.println("user");
            return ResponseEntity.ok("user");
        }

        System.out.println("Usuario ou senha incorretas");
        return ResponseEntity.ok("nenhum");
    }
}
