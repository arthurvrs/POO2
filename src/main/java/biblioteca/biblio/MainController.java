package biblioteca.biblio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.annotation.PostConstruct;

public interface MainController<T> {
        static Biblioteca biblioteca = new Biblioteca();

        @PostConstruct
        public default void iniciar() {
                if (biblioteca.livros.size() > 0) {
                        return;
                }
                biblioteca.criarAdmin("admin", "admin", "admin@biblioteca.com");
                biblioteca.criarUsuario("user", "user", "user@email.com");

                biblioteca.cadastrarLivro(new Livro("Álgebra Linear e Aplicações", "Carlos A. Callioli",
                                "https://m.media-amazon.com/images/I/814pZppLkPL.jpg",
                                "Atual Editora", "1997",
                                "O livro, dividido em duas partes, apresenta a teoria, ou seja, as idéias centrais da álgebra linear e algumas aplicações destes conceitos."));
                biblioteca.cadastrarLivro(new Livro("Introdução à Programação", "Anita Lopes, Guto Garcia",
                                "https://m.media-amazon.com/images/I/51ljjwmnAhL._SX352_BO1,204,203,200_.jpg",
                                "GEN LTC", "2002",
                                "Esta obra é indicada para um público iniciante em programação imperativa de computadores e para todos os estudantes que estão tendo dificuldades de aprender algoritmos pelo método tradicional. O objetivo deste livro é apresentar 500 exercícios resolvidos para que o aluno possa então se familiarizar com uma nova linguagem, entendê-la e a partir daí, começar a achar suas próprias soluções. O livro possui conceitos teóricos sobre algoritmos de uma forma bem rápida e resumida.Introdução à programação está dividido por assunto e organizado em 6 grandes blocos: * o primeiro, abrange algoritmos do cotidiano; * no segundo, somente algoritmos que usam funções, comando de atribuição, de entrada e saída; * no terceiro bloco, o comando de seleção; * no quarto bloco, os comandos de repetição; * no quinto os algoritmos que manipulam vetores e matrizes e, no sexto, algoritmos utilizando função.No apêndice foram incluídos problemas de raciocínio lógico e uma ferramenta para testar os algoritmos no computador."));
                biblioteca.cadastrarLivro(new Livro("Entendendo Algoritmos", "Aditya Bhargava",
                                "https://m.media-amazon.com/images/I/517I6z9QK4L._SX357_BO1,204,203,200_.jpg",
                                "Novatec Editora", "2017",
                                "Um guia ilustrado para programadores e outros curiosos. Um algoritmo nada mais é do que um procedimento passo a passo para a resolução de um problema. Os algoritmos que você mais utilizará como um programador já foram descobertos, testados e provados. Se você quer entendê-los, mas se recusa a estudar páginas e mais páginas de provas, este é o livro certo. Este guia cativante e completamente ilustrado torna simples aprender como utilizar os principais algoritmos nos seus programas. O livro Entendendo Algoritmos apresenta uma abordagem agradável para esse tópico essencial da ciência da computação. Nele, você aprenderá como aplicar algoritmos comuns nos problemas de programação enfrentados diariamente. Você começará com tarefas básicas como a ordenação e a pesquisa. Com a prática, você enfrentará problemas mais complexos, como a compressão de dados e a inteligência artificial. Cada exemplo é apresentado em detalhes e inclui diagramas e códigos completos em Python. Ao final deste livro, você terá dominado algoritmos amplamente aplicáveis e saberá quando e onde utilizá-los. O que este livro inclui: a abordagem de algoritmos de pesquisa, ordenação e algoritmos gráficos; mais de 400 imagens com descrições detalhadas; comparações de desempenho entre algoritmos; exemplos de código em Python."));
        }

        @PostMapping("/cadastro")
        public ResponseEntity<?> cadastro(@RequestBody T tipo);

        @GetMapping("/getall")
        public ResponseEntity<?> listarObjetos();

        @GetMapping("/{id}")
        public ResponseEntity<?> pegarObjeto(@PathVariable String id);

        @GetMapping("/get-atrasados")
        public ResponseEntity<?> atrasados();

}