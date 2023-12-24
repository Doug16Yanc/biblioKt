package services

import application.geraInteracao
import entities.Livro
import entities.Socio
import java.util.*

data class Emprestimo(
    val dataEmprestimo: Date,
    val dataDevolucao : Date,
    val dataDevolvido : Date
    ) {
    companion object {
        val sc = Scanner(System.`in`)

        fun procurarLivro(livros: List<Livro>, socio: Socio){

            println("Procurar por: \n" +
                    "I - ISBN\n" +
                    "T - Título\n")
            var opcao = sc.nextLine()

            when(opcao.lowercase(Locale.getDefault())) {
                "i" -> {
                    println("Digite o isbn do livro:")
                    var isbn = sc.nextInt()

                    val livroEncontrado = livros.find { it.isbn == isbn }

                    if (livroEncontrado != null) {
                        println("Realizando solicitação de empréstimo...\n")
                        registrarEmprestimo(livroEncontrado, socio)
                    }
                    else{
                        println("Livro não encontrado.\n")
                    }
                }

                "t" -> {
                    sc.nextLine()
                    println("Digite o título do livro:")
                    var title = sc.nextLine()

                    val bookEncontrado = livros.find { it.titulo == title.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }}

                    if (bookEncontrado != null) {
                        println("Realizando solicitação de empréstimo...\n")
                        registrarEmprestimo(bookEncontrado, socio)
                    }
                    else{
                        println("Livro não encontrado.\n")
                    }
                }
                else -> {
                    println("Opção não possível.\n")
                }
            }
            geraInteracao()
        }
        fun registrarEmprestimo(livro : Livro, socio: Socio) {
            println("************DADOS DO EMPRÉSTIMO DE LIVRO************\n\n")
            println("            DADOS DO SÓCIO                   \n\n" +
                    "           > Nome : ${socio.nomeSocio}\n" +
                    "           > Endereço : ${socio.enderecoSocio}\n" +
                    "           > Cep : ${socio.cepSocio}\n" +
                    "           > Tipo : ${socio.tipoSocio}\n\n" +
                    "            DADOS DO LIVRO             \n\n" +
                    "           > Título : ${livro.titulo}\n" +
                    "           > ISBN : ${livro.isbn}\n" +
                    "           > Autor : ${livro.autor.nomeAutor}\n" +
                    "           > Editora: ${livro.editora.nomeEditora}\n\n" +
                    "            DADOS DA OPERAÇÃO\n\n" +
                    "           > Id da operação : ${UUID.randomUUID()}\n")
            geraInteracao()
        }
        fun devolverExemplares(){

        }
        fun renovarExemplares(){

        }
        fun verificarSituacao(){

        }
    }
}





