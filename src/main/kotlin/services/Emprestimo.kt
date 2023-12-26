package services

import application.geraInteracao
import entities.Livro
import entities.Socio
import enumerations.Situação
import java.util.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Emprestimo {
    companion object {
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val formattedDateTime = currentDateTime.format(formatter)
        val sc = Scanner(System.`in`)

        fun procurarLivro(livros: List<Livro>, socio: Socio) {

            println(
                "Procurar por: \n" +
                        "I - ISBN\n" +
                        "T - Título\n"
            )
            var opcao = sc.nextLine()

            when (opcao.lowercase(Locale.getDefault())) {
                "i" -> {
                    println("Digite o isbn do livro:")
                    var isbn = sc.nextInt()

                    val livroEncontrado = livros.find { it.isbn == isbn }

                    if (livroEncontrado != null) {
                        println("Livro encontrado com sucesso...\n")
                        println(
                            "Continuar com o processo de empréstimo\n" +
                                    "S/s - sim\n" +
                                    "N/n - não\n"
                        )
                        var escolha = sc.next()

                        when (opcao.lowercase(Locale.getDefault())) {
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

                    } else {
                        println("Livro não encontrado.\n")
                    }
                }

                "t" -> {
                    sc.nextLine()
                    println("Digite o título do livro:")
                    var title = sc.nextLine()

                    val bookEncontrado = livros.find {
                        it.titulo == title.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        }
                    }

                    if (bookEncontrado != null) {
                        println("Livro encontrado com sucesso...\n")
                        println(
                            "Continuar com o processo de empréstimo\n" +
                                    "S/s - sim\n" +
                                    "N/n - não\n"
                        )
                        var escolha = sc.nextLine()

                        when (escolha.lowercase(Locale.getDefault())) {
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

                    } else {
                        println("Livro não encontrado.\n")
                    }
                }

                else -> {
                    println("Opção não possível.\n")
                }
            }
            Dados.interageTodos(socio)
        }

        fun registrarEmprestimo(livro: Livro, socio: Socio) {
            println("************DADOS DO EMPRÉSTIMO DE LIVRO************\n\n")
            if (livro != null) {
                println(
                    "            DADOS DO SÓCIO                   \n\n" +
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
                            "           > Id da operação : ${UUID.randomUUID()}\n" +
                            "           > Data e hora da operação : $formattedDateTime\n\n"
                )
                livro.situacao = Situação.Emprestado
                socio.exemplaresEmprestados.add(livro)
            }
            Dados.interageTodos(socio)
        }

        fun devolverExemplares(socio: Socio) {
            println("********** DEVOLUÇÃO DE EXEMPLARES **************\n")

            if (socio.exemplaresEmprestados.isEmpty()) {
                println("Você não possui exemplares para devolver.\n")
                return
            }

            println("Exemplares em posse do sócio ${socio.nomeSocio}:\n")

            for ((index, exemplar) in socio.exemplaresEmprestados.withIndex()) {
                println("${exemplar.isbn} - ${exemplar.titulo}")
            }

            println("Digite o isbn do exemplar que deseja devolver:")
            var opcao = sc.nextInt()

            val isbnEncontrado = socio.exemplaresEmprestados.find { it.isbn == opcao }

            if (isbnEncontrado != null) {
                socio.exemplaresEmprestados.removeIf { it.isbn == opcao }
                println("Exemplar '${isbnEncontrado.titulo}' devolvido com sucesso!\n")
                isbnEncontrado.situacao = Situação.Disponível
                comprovaDevolucao(socio)
            } else {
                println("Opção inválida.\n")
            }
            Dados.interageTodos(socio)
        }

        fun renovarExemplares(socio: Socio) {
            println("********** RENOVAÇÃO DE EXEMPLARES **************\n")

            if (socio.exemplaresEmprestados.isEmpty()) {
                println("Você não possui exemplares para renovar.\n")
                return
            }

            println("Exemplares em posse do sócio ${socio.nomeSocio}:\n")

            for ((index, exemplar) in socio.exemplaresEmprestados.withIndex()) {
                println("${exemplar.isbn} - ${exemplar.titulo}")
            }

            println("Digite o isbn do exemplar que deseja renovar:")
            var opcao = sc.nextInt()

            val isbnEncontrado = socio.exemplaresEmprestados.find { it.isbn == opcao }

            if (isbnEncontrado != null) {
                println("Exemplar '${isbnEncontrado.titulo}' renovado com sucesso!\n")
                println("> Data e hora da renovação : $formattedDateTime\n\n")
            } else {
                println("Opção inválida.\n")
            }
            Dados.interageTodos(socio)
        }

        fun verificarSituacao(socio: Socio) {
            println("********** SITUAÇÃO DO SÓCIO **************\n")
            println("Verifique sua situação, ${socio.nomeSocio}, caríssimo.\n\n")

            if (socio.exemplaresEmprestados.isEmpty()) {
                println("Você não solicitou o empréstimo de nenhum livro.\n")
                return
            }

            println("Exemplares em posse do sócio ${socio.nomeSocio}:\n")
            println("ISBN\t\tTítulo\t\tAutor\t\tEditora\t\n")
            for ((index, exemplar) in socio.exemplaresEmprestados.withIndex()) {
                println("${exemplar.isbn} - ${exemplar.titulo} - ${exemplar.autor.nomeAutor} - ${exemplar.editora.nomeEditora}\n\n")
            }
            println("> Data e hora da verificação : $formattedDateTime\n\n")
            Dados.interageTodos(socio)
        }
        fun comprovaDevolucao(socio : Socio){
            println("Comprovo que, para os devidos fins, o sócio ${socio.nomeSocio},\n" +
                    "${socio.tipoSocio}, realizou na data e hora $formattedDateTime a\n" +
                    "devolução dos livros que estavam em seu empréstimo.\n\n")
            Dados.interageTodos(socio)
        }
    }

}




