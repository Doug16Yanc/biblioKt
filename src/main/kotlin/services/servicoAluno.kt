package services

import application.main
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
            println("Já é aluno cadastrado ou não?\nS/s - sim\nN/n - não\n\n")
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
                sc.nextLine()

                println("Nome:")
                var nome = sc.nextLine()

                println("Senha de acesso: ")
                var senha = sc.nextLine()

                val alunoEncontrado = aluno.find { it.nomeSocio == nome && it.senha == senha }

                if (alunoEncontrado != null){
                    controlaAluno(alunoEncontrado)
                }
                else{
                    println("Nome de usuário ou senha não reconhecidos. Chances = ${tentativas - 1}\n")
                    tentativas--
                }

            }while(tentativas > 0)

        }
        fun controlaAluno(aluno : Socio){
            println("**********PÁGINA OFICIAL DO ALUNO**************\n")
            println("Bem-vindo(a), ${aluno.nomeSocio}, caríssimo estudante:")
            println("               RECURSOS HUMANOS                \n\n")
            println("               1 - Visualizar seus dados       \n" +
                    "               2 - Alterar seus dados          \n" +
                    "               3 - Remover cadastro            \n\n" +
                    "               RECURSOS FÍSICOS                \n\n" +
                    "               4 - Realizar empréstimo de livro \n" +
                    "               5 - Renovar empréstimo de livro  \n" +
                    "               6 - Devolver exemplar            \n" +
                    "               7 - Verificar situação           \n")
            var opcao = sc.nextInt()

            when(opcao){
                1 -> {
                    Dados.visualizaDados(aluno)
                }
                2 -> {
                    alteraDados()
                }
                3 -> {
                    Dados.removerCadastro(aluno)
                }
                4 -> {
                    Emprestimo.procurarLivro(servicoLivro.livros, aluno)
                }
                5 -> {
                    Emprestimo.renovarExemplares(aluno)
                }
                6 -> {
                    Emprestimo.devolverExemplares(aluno)
                }
                7 -> {
                    Emprestimo.verificarSituacao(aluno)
                }
                else -> {
                    println("Opção não possível.\n")
                }
            }
        }
        fun cadastraAluno(){
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

            val alu = Socio(id, nome, endereco, cep, email, senha, DescricaoSocio.ALUNO)

            aluno.add(alu)

            Dados.geraComprovante(alu)

            sc.close()

        }
        fun gerarId(): Int {
            var num = 0

            var entrada = Random.nextInt(100000, 1000000)
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
                    entrada = Random.nextInt(100000, 1000000)
                }
            }

            return entrada
        }

        fun alteraDados(){

        }
    }
}