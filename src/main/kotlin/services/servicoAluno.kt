package services

import entities.Socio
import enumerations.DescricaoSocio
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class servicoAluno {
    companion object{
        var aluno: MutableList<Socio> = ArrayList()

        val sc = Scanner(System.`in`)
        fun interagePrimeiro(){
            println("Já é aluno cadastrado ou não?\n S/s - sim\nN/n - não\n\n")
            var alternativa = sc.next()

            when(alternativa.lowercase(Locale.getDefault())){
                "s" -> {
                    loginAluno()
                }
                "n" -> {
                    cadastraAluno()
                }
                else -> {
                    println("Opção não possível.\n")
                }
            }

        }
        fun loginAluno(){
            var tentativas : Int = 3
            do {
                println("Nome:")
                var nome = sc.nextLine()

                var senha = System.console().readPassword("Senha de acesso: ").joinToString("")

                val alunoEncontrado = aluno.find { it.nomeSocio == nome && it.senha == senha }

                if (alunoEncontrado != null){
                    controlaAluno(alunoEncontrado)
                }
                else{
                    println("Nome de usuário ou senha não reconhecidos.\n")
                }

            }while(tentativas > 0)

        }
        fun controlaAluno(aluno : Socio){
            println("**********PÁGINA OFICIAL DO ALUNO**************\n")
            println("Bem-vindo(a), ${aluno.nomeSocio}, caríssimo estudante:")
            println("               RECURSOS HUMANOS                \n\n")
            println("               1 - ")
        }
        fun cadastraAluno(){
            var id = gerarId()
            println("Nome:")
            var nome = sc.next()

            sc.nextLine()

            println("Endereço: ")
            var endereco = sc.next()

            println("Cep: ")
            var cep = sc.nextLong()

            sc.nextLine()

            println("Email: ")
            var email = sc.nextLine()

            var senha = System.console().readPassword("Senha de acesso: ").joinToString("")


            val alu = Socio(id, nome, endereco, cep, email, senha, DescricaoSocio.ALUNO)

            aluno.add(alu)

            sc.close()

        }
        fun gerarId(): Long {
            var num = 0

            var entrada = Random.nextLong(100000, 1000000)
            var aux = true

            while (entrada.toInt() != 1) {
                for (i in 0 until aluno.size) {
                    if (entrada == aluno[i].id) {
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