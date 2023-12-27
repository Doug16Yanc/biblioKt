package services

import entities.*
import enumerations.Situação
import services.servicoLivro.Companion.livros
import java.util.*

data class Exemplar(
    val dataAquisicao : Date,
    val situacaoExemplar : Situação
) {
    companion object {
        val sc = Scanner(System.`in`)
        fun registrarExemplar(socio: Socio) {
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

            val livro = Livro(
                isbn,
                titulo,
                ano,
                edicao,
                paginas,
                Genero(genero),
                Autor(autor),
                Editora(nomeEditora, cidEditora),
                Situação.Disponível
            )

            livros.add(livro)
            servicoLivro.controlaLivro(socio)
        }
    }
}