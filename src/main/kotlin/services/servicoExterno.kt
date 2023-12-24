package services

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
            println("Já é usuário cadastrado ou não?\n S/s - sim\nN/n - não\n\n")
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
                println("Nome:")
                var nome = servicoAluno.sc.nextLine()

                var senha = System.console().readPassword("Senha de acesso: ").joinToString("")

                val userEncontrado = usuario.find { it.nomeSocio == nome && it.senha == senha }

                if (userEncontrado != null){
                    controlaUser(userEncontrado)
                }
                else{
                    println("Nome de usuário ou senha não reconhecidos.\n")
                }

            }while(tentativas > 0)

        }
        fun controlaUser(usuario : Socio){
            println("**********PÁGINA OFICIAL DO PROFESSOR**************\n")
            println("Bem-vindo(a), ${usuario.nomeSocio}, caríssimo e inestimado usuário:")
            println("               RECURSOS HUMANOS                \n\n")
            println("               1 - ")
        }
        fun cadastraUser(){
            var id = gerarId()
            println("Nome:")
            var nome = sc.nextLine()

            println("Endereço: ")
            var endereco = sc.nextLine()

            println("Cep: ")
            var cep = sc.nextLong()

            println("Email: ")
            var email = sc.nextLine()

            var senha = System.console().readPassword("Senha de acesso: ").joinToString("")


            val user = Socio(id, nome, endereco, cep, email, senha, DescricaoSocio.EXTERNO )

            usuario.add(user)

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
    }
}