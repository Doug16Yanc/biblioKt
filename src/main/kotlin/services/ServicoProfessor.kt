package services

import application.geraInteracao
import application.main
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
            println("Já é professor cadastrado ou não?\nS/s - sim\nN/n - não\n\n")
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
                sc.nextLine()

                println("Nome:")
                var nome = sc.nextLine()

                println("Senha de acesso: ")
                var senha = sc.nextLine()

                val profEncontrado = professor.find { it.nomeSocio == nome && it.senha == senha }

                if (profEncontrado != null){
                    controlaProf(profEncontrado)
                }
                else{
                    println("Nome de usuário ou senha não reconhecidos. Chances = ${tentativas - 1}\n")
                    tentativas--
                }

            }while(tentativas > 0)

        }
        fun controlaProf(professor : Socio){
            println("**********PÁGINA OFICIAL DO PROFESSOR**************\n")
            println("Bem-vindo(a), ${professor.nomeSocio}, caríssimo mestre:")
            println("               RECURSOS HUMANOS                \n\n")
            println("               1 - Visualizar seus dados       \n" +
                    "               2 - Alterar seus dados          \n" +
                    "               3 - Remover seu cadastro        \n\n" +
                    "               RECURSOS FÍSICOS                \n\n" +
                    "               4 - Realizar empréstimo de livro \n" +
                    "               5 - Renovar empréstimo de livro  \n" +
                    "               6 - Devolver exemplar            \n" +
                    "               7 - Verificar situação           \n\n" +
                    "               RECURSOS DE SISTEMA              \n\n" +
                    "               8 - Retornar à página inicial    \n" +
                    "               9 - Encerrar operação           \n\n")
            var opcao = sc.nextInt()

            when(opcao){
                1 -> {
                  Dados.visualizaDados(professor)
                }
                2 -> {
                    alteraDados()
                }
                3 ->{
                    Dados.removerCadastro(professor)
                }
                4 -> {
                    Emprestimo.procurarLivro(servicoLivro.livros, professor)
                }
                5 -> {
                    Emprestimo.renovarExemplares(professor)
                }
                6 -> {
                    Emprestimo.devolverExemplares(professor)
                }
                7 -> {
                    Emprestimo.verificarSituacao(professor)
                }
                8 -> {
                    geraInteracao()
                }
                9 -> {
                    println("Até mais, ${professor.nomeSocio}!\n")
                    System.exit(0)
                }
                else -> {
                    println("Opção não possível.\n")
                }
            }
        }
        fun cadastraProfessor(){
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


            val prof = Socio(id, nome, endereco, cep, email, senha, DescricaoSocio.PROFESSOR )

            professor.add(prof)

            Dados.geraComprovante(prof)

            sc.close()

        }
        fun gerarId(): Int {
            var num = 0

            var entrada = Random.nextInt(100000, 1000000)
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
                    entrada = Random.nextInt(100000, 1000000)
                }
            }

            return entrada
        }

        fun alteraDados(){

        }
    }
}