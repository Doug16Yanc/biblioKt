package services

import application.geraInteracao
import entities.Livro
import entities.Socio
import enumerations.Situação
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
                        println("Livro encontrado com sucesso...\n")
                        println("Continuar com o processo de empréstimo\n" +
                                "S/s - sim\n" +
                                "N/n - não\n")
                        var escolha = sc.next()

                        when(opcao.lowercase(Locale.getDefault())){
                            "s" -> {
                                registrarEmprestimo(livroEncontrado, socio)
                            }
                            "n" -> {
                                servicoAluno.controlaAluno(socio)
                            }
                            else -> {
                                println("Opção não possível! Retornando...\n")
                                servicoAluno.controlaAluno(socio)
                            }
                        }

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
                        println("Livro encontrado com sucesso...\n")
                        println("Continuar com o processo de empréstimo\n" +
                                "S/s - sim\n" +
                                "N/n - não\n")
                        var escolha = sc.nextLine()

                        when(escolha.lowercase(Locale.getDefault())){
                            "s" -> {
                                registrarEmprestimo(bookEncontrado, socio)
                            }
                            "n" -> {
                                servicoAluno.controlaAluno(socio)
                            }
                            else -> {
                                println("Opção não possível! Retornando...\n")
                                servicoAluno.controlaAluno(socio)
                            }
                        }

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
            if (livro != null) {
                println("            DADOS DO SÓCIO                   \n\n" +
                        "           > Nome : ${socio.nomeSocio}\n" +
                        "           > Endereço : ${socio.enderecoSocio}\n" +
                        "           > Cep : ${socio.cepSocio}\n" +
                        "           > Tipo : ${socio.tipoSocio}\n\n" +
                        "            DADOS DO LIVRO             \n\n" +
                        "           > Título : ${livro.titulo}\n" +
                        "           > ISBN : ${livro.isbn}\n" +
                        "           > Autor : ${livro.autor.nomeAutor}\n" +
                        "           > Editora: ${livro.editora.nomeEditora}\n" +
                        "           > Situação : ${Situação.Emprestado}\n\n" +
                        "            DADOS DA OPERAÇÃO\n\n" +
                        "           > Id da operação : ${UUID.randomUUID()}\n")
                livro.situacao = Situação.Emprestado
            }
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





