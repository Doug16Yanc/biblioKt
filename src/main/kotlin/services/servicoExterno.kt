package services

import application.main
import entities.Socio
import enumerations.DescricaoSocio
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class servicoExterno {
    companion object{
        var usuario : MutableList<Socio> = ArrayList()

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

                val userEncontrado = usuario.find { it.nomeSocio == nome && it.senha == senha }

                if (userEncontrado != null){
                    controlaUser(userEncontrado)
                }
                else{
                    println("Nome de usuário ou senha não reconhecidos. Chances = ${tentativas - 1}\n")
                    tentativas--
                }

            }while(tentativas > 0)

        }
        fun controlaUser(usuario : Socio){
            println("**********PÁGINA OFICIAL DO ALUNO**************\n")
            println("Bem-vindo(a), ${usuario.nomeSocio}, caríssimo estudante:")
            println("               RECURSOS HUMANOS                \n\n")
            println("               1 - Visualizar seus dados       \n" +
                    "               2 - Alterar seus dados          \n\n" +
                    "               RECURSOS FÍSICOS                \n\n" +
                    "               3 - Realizar empréstimo de livro \n" +
                    "               4 - Renovar empréstimo de livro  \n")
            var opcao = servicoProfessor.sc.nextInt()

            when(opcao){
                1 -> {
                    Dados.visualizaDados(usuario)
                }
                2 -> {
                    alteraDados()
                }
                3 -> {
                    Emprestimo.procurarLivro(servicoLivro.livros, usuario)
                }
                4 -> {
                    Emprestimo.renovarExemplares()
                }
                5 -> {
                    Emprestimo.devolverExemplares()
                }
                6 -> {
                    Emprestimo.verificarSituacao()
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

            usuario.add(user)

            geraComprovante(user)

            sc.close()

        }
        fun gerarId(): Long {
            var num = 0

            var entrada = Random.nextLong(100000, 1000000)
            var aux = true

            while (entrada.toInt() != 1) {
                for (i in 0 until usuario.size) {
                    if (entrada == usuario[i].id) {
                        aux = false
                    }
                }

                if (aux) {
                    return entrada
                } else {
                    entrada = Random.nextLong(100000, 1000000)
                }
            }

            return entrada
        }
        fun geraComprovante(usuario : Socio){
            println("********COMPROVANTE DE CADASTRO NO SISTEMA***************\n")
            println("       > Nome do estudante: ${usuario.nomeSocio}\n" +
                    "       > Id do estudante : ${usuario.id}\n" +
                    "       > Endereço : ${usuario.enderecoSocio}\n" +
                    "       > Cep : ${usuario.cepSocio}\n" +
                    "       > Email : ${usuario.emailSocio}\n" +
                    "       > Tipo de sócio : ${usuario.tipoSocio}\n" +
                    "       > Id da operação : ${UUID.randomUUID()}\n")
            main()
        }
        fun visualizaDados(usuario : Socio){

        }
        fun alteraDados(){

        }
    }
}