# Sistema de Gerenciamento de uma Biblioteca Online - Projeto de Programação Orientada a Objetos

O sistema de gerenciamento de bibliotecas visa facilitar a vida de alunos que queiram alugar livros de forma prática e ágil, assim como auxiliar seus administradores em sua gestão. Nele, é possível cadastrar usuários (clientes ou administradores), cadastrar livros e gerenciar os aluguéis, devoluções e dívidas quanto aos mesmos.

> ## Code Smells

- Duplicated Code
  ```sh
  Biblioteca -> listarlivros, listarLivrosAtrasados, buscarCliente, BuscarAdmin.
	 ```

- Data Clumps
  ```sh
  Usuario -> (livrosAlugados, LivrosReservados, LivrosDevolvidos), (printUsuario, listarLivrosAlugados)
	```
- Long Method
	 ```sh
  Cliente -> removerReserva
  Biblioteca -> listarLivrosAtrasados
  ```

- Large Class
	 ```sh
  Livro, Biblioteca
  ```

- Shotgun Surgery
  ```sh
  Biblioteca
  ```

- Feature Envy
	 ```sh
  Cliente -> removerReserva, reservar.
  ```

- Primitive Obsession
	 ```sh
  Livro -> CapaURL, id.
  ```

- Middle Man
  ```sh
  Usuario ->  printUsuario, ListarLivros, listarLivrosAtrasados, temLivroAtrasado, baterPontoEntrada, baterPontoSaida, alugarLivro, pegarLivrosAlugados, pegarLivrosDevolvidos, devolverLivro e reservarlistar.
	 ```

- Data Class
	 ```sh
  Reviews
  ```
