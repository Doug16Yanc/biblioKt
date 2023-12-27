package services

import entities.Socio
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Renovacao
{
    companion object {
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val formattedDateTime = currentDateTime.format(formatter)
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
            var opcao = Emprestimo.sc.nextInt()

            val isbnEncontrado = socio.exemplaresEmprestados.find { it.isbn == opcao }

            if (isbnEncontrado != null) {

                println("Exemplar '${isbnEncontrado.titulo}' renovado com sucesso!\n")
                println("> Data e hora da renovação : ${formattedDateTime}\n\n")
                socio.exemplaresRenovados.add(isbnEncontrado)
            } else {
                println("Opção inválida.\n")
            }
            Dados.interageTodos(socio)
        }

        fun consultarRenovacoes(socio: Socio) {
            println("********EXEMPLARES RENOVADOS**********\n")
            println("Verifique suas renovações, ${socio.nomeSocio}, caríssimo.\n\n")

            if (socio.exemplaresRenovados.isEmpty()) {
                println("Você não realizou a renovação de nenhum livro.\n")
                Dados.interageTodos(socio)
            }

            println("Exemplares renovados por ${socio.nomeSocio}:\n")
            println("ISBN\t\tTítulo\t\tAutor\t\tEditora\t\n")
            for ((index, exemplar) in socio.exemplaresRenovados.withIndex()) {
                println("${exemplar.isbn} - ${exemplar.titulo} - ${exemplar.autor.nomeAutor} - ${exemplar.editora.nomeEditora}\n\n")
            }
            println("> Data e hora da operação : ${formattedDateTime}\n\n")
            Dados.interageTodos(socio)
        }
    }
}