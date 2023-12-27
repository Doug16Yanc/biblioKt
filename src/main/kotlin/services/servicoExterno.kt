package services

import application.geraInteracao
import application.main
import entities.Socio
import enumerations.DescricaoSocio
import services.Dados.Companion.socios
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class servicoExterno {
    companion object{
        val sc = Scanner(System.`in`)
        fun interagePrimeiro(){
            println("Já é usuário cadastrado ou não?\nS/s - sim\nN/n - não\n\n")
            var alternativa = sc.next()

            when(alternativa.lowercase(Locale.getDefault())){
                "s" -> {
                    loginUser()
                }
                "n" -> {
                    cadastraUser()
                }
                else -> {
                    println("Opção não possível.\n")
                }
            }

        }
        fun loginUser(){
            var tentativas : Int = 3
            do {
                sc.nextLine()

                println("Nome:")
                var nome = sc.nextLine()

                println("Senha de acesso: ")
                var senha = sc.nextLine()

                val userEncontrado = socios.find { it.nomeSocio == nome && it.senha == senha }

                if (userEncontrado != null){
                    controlaUser(userEncontrado)
                }
                else{
                    println("Nome de usuário ou senha não reconhecidos. Chances = ${tentativas - 1}\n")
                    tentativas--
                }

            }while(tentativas > 0)
            geraInteracao()
        }
        fun controlaUser(usuario : Socio){
            println("**********PÁGINA OFICIAL DO USUÁRIO**************\n")
            println("Bem-vindo(a), ${usuario.nomeSocio}, caríssimo usuário:")
            println("               RECURSOS HUMANOS                \n\n")
            println("               1 - Visualizar seus dados       \n" +
                    "               2 - Alterar seus dados          \n" +
                    "               3 - Remover seu cadastro        \n\n" +
                    "               RECURSOS FÍSICOS                \n\n" +
                    "               4 - Realizar empréstimo de livro \n" +
                    "               5 - Renovar empréstimo de livro  \n" +
                    "               6 - Devolver exemplar            \n" +
                    "               7 - Verificar situação           \n" +
                    "               8 - Consultar renovações        \n\n" +
                    "               RECURSOS DE SISTEMA              \n\n" +
                    "               9 - Retornar à página inicial    \n" +
                    "               10 - Encerrar operação           \n\n")
            var opcao = servicoProfessor.sc.nextInt()

            when(opcao){
                1 -> {
                    Dados.visualizaDados(usuario)
                }
                2 -> {
                    Dados.alteraDados(usuario)
                }
                3 -> {
                    Dados.removerCadastro(usuario)
                }
                4 -> {
                    Emprestimo.procurarLivro(servicoLivro.livros, usuario)
                }
                5 -> {
                    Renovacao.renovarExemplares(usuario)
                }
                6 -> {
                    Emprestimo.devolverExemplares(usuario)
                }
                7 -> {
                    Emprestimo.verificarSituacao(usuario)
                }
                8 -> {
                    Renovacao.consultarRenovacoes(usuario)
                }
                9 -> {
                    geraInteracao()
                }
                10 -> {
                    println("Até mais, ${usuario.nomeSocio}!\n")
                    System.exit(0)
                }
                else -> {
                    println("Opção não possível.\n")
                }
            }
        }
        fun cadastraUser(){
            var id = gerarId()

            sc.nextLine()

            println("Nome:")
            var nome = sc.nextLine()

            println("Endereço: ")
            var endereco = sc.nextLine()

            println("Cep: ")
            var cep = sc.nextInt()

            sc.nextLine()

            println("Email: ")
            var email = sc.nextLine()

            println("Senha de acesso: ")
            var senha = sc.nextLine()

            val user = Socio(id, nome, endereco, cep, email, senha, DescricaoSocio.EXTERNO )

            socios.add(user)

            Dados.geraComprovante(user)

            sc.close()

        }
        fun gerarId(): Int {
            var num = 0

            var entrada = Random.nextInt(100000, 1000000)
            var aux = true

            while (entrada.toInt() != 1) {
                for (i in 0 until socios.size) {
                    if (entrada == socios[i].id) {
                        aux = false
                    }
                }

                if (aux) {
                    return entrada
                } else {
                    entrada = Random.nextInt(100000, 1000000)
                }
            }

            return entrada
        }
    }
}