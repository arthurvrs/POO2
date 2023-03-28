package biblioteca.biblio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {
    static Biblioteca biblioteca;
    static Scanner input;
    static Cliente cliente;
    static Admin admin;

    public static void main(String[] args) {
        biblioteca = new Biblioteca();
        input = new Scanner(System.in);
        
        biblioteca.inicializarDados();

        do {
            loginMenu();
            
        } while (false);

        System.out.println("\n                           ATÉ A PRÓXIMA!");
        input.close();
    }

    private static void loginMenu() {
        boolean conseguiuLogar = false;
        
        loginLoop:
        while (!conseguiuLogar) {
            System.out.println("--------------------------- SEJA BEM-VINDO ---------------------------\n");
            System.out.println("                                MENU\n");
            System.out.println("1 - Logar");
            System.out.println("2 - Cadastrar Usuario");
            System.out.println("3 - Sair");
            System.out.print("Digite o que deseja fazer: ");

            String opcao = input.nextLine();
            System.out.println();

            switch (opcao) {
                case "1":
                    conseguiuLogar = tentarLogar();
                    break;
                case "2":
                    cadastrarUsuario();
                    break;
                case "3":
                    break loginLoop;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
    }

    private static void adminMenu() {
        menu: while (true) {
            System.out.println("--------------------------- SEJA BEM-VINDO ---------------------------\n");
            System.out.println("                                MENU\n");
            System.out.println("1  - Criar Usuario Administrador");
            System.out.println("2  - Cadastrar Livros");
            System.out.println("3  - Listar Livros");
            System.out.println("4  - Listar Usuarios");
            System.out.println("5  - Listar Administradores");
            System.out.println("6  - Buscar");
            System.out.println("7  - Listar Livros Alugados");
            System.out.println("8  - Listar Livros Atrasados");
            System.out.println("9  - Remover Review");
            System.out.println("10 - Alterar Senha");
            System.out.println("11 - Alterar Contato");
            System.out.println("12 - Ver Horas Trabalhadas");
            System.out.println("13 - Sair");
            System.out.print("Digite o que deseja fazer: ");

            int[] count = {0};
            String opcao = input.nextLine();
            System.out.println();

            switch (opcao) {
                case "1":
                    cadastrarAdmin();
                    break;
                case "2":
                    cadastrarLivro();
                    break;
                case "3":
                    biblioteca.listarLivros();
                    break;
                case "4":
                    biblioteca.listarClientes();
                    break;
                case "5":
                    biblioteca.listarAdministradores();
                    break;
                case "6":
                    buscarLivro();
                    break;
                case "7":
                    biblioteca.listarLivros(count);
                    break;
                case "8":
                    biblioteca.listarLivrosAtrasados();
                    break;                
                case "9":
                    removerReview();
                    break;
                case "10":
                    alterarSenha(admin);
                    break;
                case "11":
                    alterarContato(admin);
                    break;
                case "12":
                    System.out.println(admin.horasTrabalhadas);
                    break;
                case "13":
                    admin.baterPontoSaida();
                    break menu;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
    }

    private static void usuarioMenu() {
        menu: while (true) {
            System.out.println("--------------------------- SEJA BEM-VINDO ---------------------------\n");
            System.out.println("                                MENU\n");
            System.out.println("1 - Buscar");
            System.out.println("2 - Listar Livros");
            System.out.println("3 - Alugar Livro");
            System.out.println("4 - Devolver Livro");
            System.out.println("5 - Listar Livros Alugados");
            System.out.println("6 - Reservar Livro");
            System.out.println("7 - Multas Pendentes");
            System.out.println("8 - Escrever Review");
            System.out.println("9 - Alterar Senha");
            System.out.println("10 - Alterar Contato");
            System.out.println("11 - Sair");
            System.out.print("Digite o que deseja fazer: ");

            String opcao = input.nextLine();
            System.out.println();

            switch (opcao) {
                case "1":
                    buscarLivro();
                    break;
                case "2":
                    biblioteca.listarLivros();
                    break;
                case "3":
                    alugarLivro();
                    break;
                case "4":
                    devolverLivro();
                    break;
                case "5":
                    System.out.println(cliente.listarLivrosAlugados());
                    break;
                case "6":
                    reservar();
                    break;
                case"7":
                    olharMulta();
                    break;    
                case "8":
                    escreverReview();
                    break;
                case "9":
                    alterarSenha(cliente);
                    break;
                case "10":
                    alterarContato(cliente);
                    break;
                case "11":
                    break menu;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
    }

    private static void alterarSenha(Usuario usuario) {
        System.out.print("Digite sua senha: ");

        String senhaAtual = input.nextLine();

        System.out.print("Digite a nova senha: ");
        String senhaNova = input.nextLine();


        boolean conseguiuAlterar = usuario.alterarSenha(senhaAtual, senhaNova);

        if (!conseguiuAlterar) {
            System.out.println("Senha incorreta!");
        }
    }

    private static void alterarContato(Usuario usuario) {
        System.out.print("Digite sua senha: ");

        String senha = input.nextLine();

        System.out.print("Digite o seu novo contato: ");
        String contato = input.nextLine();


        boolean conseguiuAlterar = usuario.alterarContato(senha, contato);

        if (!conseguiuAlterar) {
            System.out.println("Senha incorreta!");
        }
    }

    private static void cadastrarAdmin() {
        System.out.print("Digite o Username: ");
        String username = input.nextLine();

        if (biblioteca.buscarUsuario(username) != null) {
            System.out.println("Usuario já existente!");
            return;
        }
        System.out.print("Digite forma de contato(email/telefone): ");
        String contato = input.nextLine();

        System.out.print("Digite sua senha: ");
        String senha = input.nextLine();

        biblioteca.criarAdmin(username, senha, contato);
    }

    private static boolean tentarLogar() {
        System.out.print("Digite o seu usuario: ");
        String username = input.nextLine();

        System.out.print("Digite a sua senha: ");
        String senha = input.nextLine();
        
        Usuario userLogado = biblioteca.login(username, senha);
        
        if(userLogado instanceof Cliente)
        {
            System.out.println("Usuario " + userLogado.username + " logado com sucesso!");
            cliente = (Cliente) userLogado;
            usuarioMenu();
        }

        else if(userLogado instanceof Admin)
        {
            System.out.println("Usuario " + userLogado.username + " logado com sucesso!");
            admin = (Admin) userLogado;
            admin.baterPontoEntrada();
            adminMenu();
        }

        else
        {
            System.out.println("Usuario ou senha invalidos.");
        }

        return false;
    }

    private static void devolverLivro() {
        System.out.print("Digite o titulo do livro: ");
        String titulo = input.nextLine();

        Livro livro = biblioteca.buscarLivro(titulo);

        if (livro == null) {
            System.out.println("Livro nao encontrado!");
            return;
        }

        if (!cliente.estaComLivroAlugado(livro)) {
            System.out.println("Você não alugou este livro!");
            return;
        }

        if (livro.dataDevolucao != null) {
            if(livro.isAtrasado()) {
                LocalDate hoje = LocalDate.now();
                long atraso = ChronoUnit.DAYS.between(livro.dataDevolucao, hoje);
                System.out.println("Digite (ok) para comfirmar o pagamento da multa de R$:" + (5 + atraso*.75) + "\n(R$ 5.00 + R$ 0.75 por dia de atraso.)");
                String ok = input.nextLine();
            
                if(!ok.equals("ok")) return;
            }
        }

        cliente.devolverLivro(livro,biblioteca);
        System.out.println("Livro devolvido com sucesso!");

    }

    private static void olharMulta()
    {
        int atrasado = 0;
        
        if (cliente.livrosAlugados.size() > 0) {
            
            for (Livro livro: cliente.livrosAlugados) {
                if (livro.isAtrasado()) {
                    LocalDate hoje = LocalDate.now();
                    long atraso = ChronoUnit.DAYS.between(livro.dataDevolucao, hoje);
                    
                    System.out.print("Titulo:");
                    System.out.print(" "+ livro.getTitulo());
                    System.out.print(" multa de R$: " + (5 + atraso*.75) + " / ");
                    atrasado++;
                }
            }
        }
        if(atrasado == 0)
        {
            System.out.println("Sem multas pendentes!");
        }
        else
        {
            System.out.println();
        }
        
    }

    private static void alugarLivro() {

        for(Livro l: cliente.livrosAlugados) {
            if(l.isAtrasado()) {
                System.out.println("Devido a existencia de livros atrasados, esta função está indisponivel!");
                return;
            }
        }



        System.out.print("Digite o titulo do livro: ");
        String titulo = input.nextLine();

        Livro livro = biblioteca.buscarLivro(titulo);

        if (livro == null) {
            System.out.println("Livro nao encontrado!");
            return;
        }


        System.out.print("Digite a data para devolução do livro (YYYY-MM-DD):");
        String devolucao = input.nextLine();

    
        DateTimeFormatter formato = DateTimeFormatter.ISO_LOCAL_DATE;

        boolean formatoCorreto = false;
        while (!formatoCorreto) {
            try {
                livro.dataDevolucao = LocalDate.parse(devolucao, formato);
                formatoCorreto = true;
            } catch (Exception e) {
                System.out.println("A Input não apresenta o formato correto, digite novamente a data no formato (YYYY-MM-DD)");
                devolucao = input.nextLine();
            }
        }

        if (livro.getDisponibilidade()) {
            cliente.alugarLivro(livro);
            System.out.println("Livro alugado com sucesso!");
        } else {
            System.out.println("Livro indisponivel!");
        }
    }

    private static void cadastrarUsuario() {
        System.out.print("Digite o Username: ");
        String username = input.nextLine();

        if (biblioteca.buscarUsuario(username) != null) {
            System.out.println("Usuario já existente!");
            return;
        }

        System.out.print("Digite forma de contato(email/telefone): ");
        String contato = input.nextLine();

        System.out.print("Digite sua senha: ");
        String senha = input.nextLine();

        biblioteca.criarUsuario(username, senha, contato);
        System.out.println("Usuario cadastrado com sucesso!");
    }

    private static void cadastrarLivro() {
        System.out.println("Nome do Livro:");
        String titulo = input.nextLine();

        System.out.println("Autor do Livro:");
        String autor = input.nextLine();

        System.out.println("Editora do Livro:");
        String editora = input.nextLine();

        System.out.println("Ano de lançamento do Livro:");
        String anoLancamento = input.nextLine();

        Livro livro = new Livro(titulo, autor, editora, anoLancamento, anoLancamento, anoLancamento);
        
        biblioteca.cadastrarLivro(livro);
    }

    private static void buscarLivro()
    {
        System.out.print("Digite o titulo do livro: ");
        String titulo = input.nextLine();

        Livro livro = biblioteca.buscarLivro(titulo);

        if(livro == null)
            System.out.println("Livro nao encontrado!");
        
        else {
            livro.printLivro();
            if (livro.reviews.isEmpty()) {
                System.out.println("Nenhuma review.");
            } else {
                System.out.println("Reviews:");
                for(Review R: livro.reviews) {
                    R.printReview();
                }
            }
        }
    }

    private static void reservar()
    {
        for(Livro l: cliente.livrosAlugados) {
            if(l.isAtrasado()) {
                System.out.println("Devido a existencia de livros atrasados, esta função está indisponivel!");
                return;
            }
        }
        
        System.out.print("Digite o titulo do livro: ");
        String titulo = input.nextLine();
        
        Livro livro = biblioteca.buscarLivro(titulo);

        if (livro == null) {
            System.out.println("Livro nao encontrado!");
            return;
        }

        cliente.reservar(livro);
    }

    private static void escreverReview() {
        if (cliente.livrosDevolvidos.isEmpty()) {
            System.out.println("Nenhum livro valido (devolva um livro para poder escrever uma review).");
        } else {
            int count = 1;
            for (Livro l: cliente.livrosDevolvidos) {
                System.out.println(count++ + " - " + l.titulo);
            }
            System.out.println("Digite o número do livro que quer escrever a review: ");
            int numl = input.nextInt();

            Livro selecionado = null;
            boolean formatoCorreto = false;
            while (!formatoCorreto) {
                try {
                    selecionado = cliente.livrosDevolvidos.get(numl - 1);
                    formatoCorreto = true;
                } catch (Exception e) {
                    System.out.println("A Input não apresenta o formato correto");
                    numl = input.nextInt();
                }
            }

            System.out.println("Digite sua review:");
            String rev = input.nextLine();
            rev = input.nextLine();
            selecionado.reviews.add(new Review(rev, cliente.username));
        }       
    }

    private static void removerReview() {
        System.out.println("Digite o username do usuario:");
        String username = input.nextLine();
        if(biblioteca.buscarCliente(username) == null) {
            System.out.println("Usuario não encotrado");
            return;
        }
        System.out.print("Digite o titulo do livro: ");
        String titulo = input.nextLine();

        Livro livro = biblioteca.buscarLivro(titulo);
        if(livro == null) {
            System.out.println("Livro não encotrado");
            return;
        }
        
        int index = -1;
        for(Review r: livro.reviews) {
            if (r.reviewerUsername.equals(username)) {
                index = livro.reviews.indexOf(r);
            }
        }
        if (index == -1) {
            System.out.println("Usuario não possui review desse livro.");
        } else {
            livro.reviews.remove(index);
            System.out.println("Review removida com sucesso.");
        }
    }
}