package services

import entities.*
import enumerations.Situação
import java.util.*
import kotlin.collections.ArrayList

class servicoLivro {
    companion object{
        var livros : MutableList<Livro> = ArrayList()
        init{
            guardaLivros()
        }
        val sc = Scanner(System.`in`)
        fun controlaLivro(socio: Socio){
            println("************PÁGINA DO LIVRO**************\n")
            println("           1 - Ver livros disponíveis          \n" +
                    "           2 - Registrar novo livro            \n" +
                    "           3 - Solicitar empréstimo de livro   \n" +
                    "           4 - Renovar exemplar(es)            \n" +
                    "           5 - Devolver exemplar(es)           \n" +
                    "           6 - Verificar situação              \n")

            var opcao = sc.nextInt()

            when(opcao){
                1 -> {
                    listaLivros(socio)
                }
                2 -> {
                    registrarLivro(socio)
                }
                3 -> {
                    Emprestimo.procurarLivro(livros, socio)
                }
                4 -> {
                    Emprestimo.renovarExemplares(socio)
                }
                5 -> {
                    Emprestimo.devolverExemplares(socio)
                }
                6 -> {
                    Emprestimo.verificarSituacao(socio)
                }
                else -> {
                    println("Opção não possível.\n")
                }
            }
        }
        fun registrarLivro(socio : Socio){
            println("ISBN do livro:")
            var isbn = sc.nextInt()

            sc.nextLine()

            println("Título:")
            var titulo = sc.nextLine()

            println("Ano de publicação:")
            var ano = sc.nextInt()

            sc.nextLine()

            println("Edição :")
            var edicao = sc.nextInt()

            sc.nextLine()

            println("Número de páginas:")
            var paginas = sc.nextInt()

            sc.nextLine()

            println("Gênero :")
            var genero = sc.nextLine()

            println("Autor:")
            var autor = sc.nextLine()

            println("Nome da editora:")
            var nomeEditora = sc.nextLine()

            println("Cidade da editora:")
            var cidEditora = sc.nextLine()

            val livro = Livro(isbn, titulo, ano, edicao, paginas, Genero(genero), Autor(autor), Editora(nomeEditora, cidEditora), Situação.Disponível)

            livros.add(livro)

            controlaLivro(socio)
        }
        fun guardaLivros(){
            livros.add(
                    Livro(
                        1245284, "Crime e Castigo", 1866, 3, 500, Genero("Romance filosófico"), Autor(
                            "Fiodor " +
                                    "Dostoievski"
                        ), Editora("Companhia das Letras", "São Paulo"), Situação.Disponível))
            livros.add(Livro
                (
                14562345, "O Estrangeiro", 1942, 2, 128, Genero("Romance filosófico"), Autor(
                "Albert Camus"), Editora("Intrínseca", "Rio de Janeiro"), Situação.Disponível))
            livros.add(Livro
                (
                1463744, "Metamorfose", 1915, 2, 96, Genero("Fantasia"),
                Autor("Franz Kafka"), Editora("Rocco", "Rio de Janeiro"), Situação.Disponível))
            livros.add(Livro
                (1635464, "Minha Querida Sputnik", 1999, 6, 232, Genero("Realismo mágico"),
                Autor("Haruki Murakami"), Editora("Panda Books", "São Paulo"), Situação.Disponível))
            livros.add(Livro(17458293, "eu tenho sérios poemas mentais", 2018, 1, 192, Genero("Poesia"),
                Autor("Pedro Salomão"), Editora("Sextante", "Rio de Janeiro"), Situação.Disponível))
        }
        fun listaLivros(socio: Socio){
            println("*****************DADOS DOS LIVROS**************\n\n")
            for (livro : Livro in livros){
                println("> ISBN : ${livro.isbn}\n" +
                        "> Título : ${livro.titulo}\n" +
                        "> Edição : ${livro.edicao}\n" +
                        "> Número de páginas: ${livro.paginas}\n" +
                        "> Gênero : ${livro.genero.descricao}\n" +
                        "> Autor : ${livro.autor.nomeAutor}\n" +
                        "> Nome da editora: ${livro.editora.nomeEditora}\n" +
                        "> Cidade da editora: ${livro.editora.cidadeEditora}\n" +
                        "> Situação na biblioteca : ${livro.situacao}\n\n")

            }
            println("******************************************************\n\n")
            controlaLivro(socio)
        }
    }
}