package services

import entities.Autor
import entities.Editora
import entities.Genero
import entities.Livro
import java.util.*
import kotlin.collections.ArrayList

class servicoLivro {
    companion object{
        var livros : List<Livro> = ArrayList()
        val sc = Scanner(System.`in`)
        fun controlaLivro(){
            println("************PÁGINA DO LIVRO**************\n")
            println("           1 - Ver livros disponíveis    \n")
            println("           2 - Registrar novo livro      \n")

            var opcao = sc.nextInt()

            when(opcao){
                1 -> {guardaLivros()}
                2 -> {registrarLivro()}
                else -> {
                    println("Opção não possível.\n")
                }
            }
        }
        fun guardaLivros(){
            livros = listOf(Livro(1245284, "Crime e Castigo", 1866, 3, 500, Genero("Romance filosófico"), Autor("Fiodor " +
                    "Dostoievski"), Editora("Companhia das Letras", "São Paulo")
            ))
            println("*********LIVROS DISPONÍVEIS***********\n\n")
            for (livro : Livro in livros){
                println("> ISBN : ${livro.isbn}\n" +
                        "> Título : ${livro.titulo}\n" +
                        "> Edição : ${livro.edicao}\n" +
                        "> Número de páginas: ${livro.paginas}\n" +
                        "> Gênero : ${livro.genero.descricao}\n" +
                        "> Autor : ${livro.autor.nomeAutor}\n" +
                        "> Nome da editora: ${livro.editora.nomeEditora}\n" +
                        "> Cidade da editora: ${livro.editora.cidadeEditora}\n")

            }

        }
        fun registrarLivro(){

        }
    }
}