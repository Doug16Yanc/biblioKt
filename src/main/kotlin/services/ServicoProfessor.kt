package services

import entities.Socio
import enumerations.DescricaoSocio
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class servicoProfessor {
    companion object{
        var professor: MutableList<Socio> = ArrayList()

        val sc = Scanner(System.`in`)
        fun interagePrimeiro(){
            println("Já é professor cadastrado ou não?\n S/s - sim\nN/n - não\n\n")
            var alternativa = sc.next()

            when(alternativa.lowercase(Locale.getDefault())){
                "s" -> {
                    loginProf()
                }
                "n" -> {
                    cadastraProfessor()
                }
                else -> {
                    println("Opção não possível.\n")
                }
            }

        }
        fun loginProf(){
            var tentativas : Int = 3
            do {
                println("Nome:")
                var nome = servicoAluno.sc.nextLine()

                var senha = System.console().readPassword("Senha de acesso: ").joinToString("")

                val profEncontrado = servicoAluno.aluno.find { it.nomeSocio == nome && it.senha == senha }

                if (profEncontrado != null){
                    controlaProf(profEncontrado)
                }
                else{
                    println("Nome de usuário ou senha não reconhecidos.\n")
                }

            }while(tentativas > 0)

        }
        fun controlaProf(professor : Socio){
            println("**********PÁGINA OFICIAL DO PROFESSOR**************\n")
            println("Bem-vindo(a), ${professor.nomeSocio}, caríssimo mestre:")
            println("               RECURSOS HUMANOS                \n\n")
            println("               1 - ")
        }
        fun cadastraProfessor(){
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


            val prof = Socio(id, nome, endereco, cep, email, senha, DescricaoSocio.PROFESSOR )

            professor.add(prof)

            sc.close()

        }
        fun gerarId(): Long {
            var num = 0

            var entrada = Random.nextLong(100000, 1000000)
            var aux = true

            while (entrada.toInt() != 1) {
                for (i in 0 until professor.size) {
                    if (entrada == professor[i].id) {
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