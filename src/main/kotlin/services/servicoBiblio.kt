package services

import application.main
import entities.Socio
import enumerations.DescricaoSocio
import java.util.Scanner

class servicoBiblio {


    companion object {
        val socios  = listOf(Socio("Dooglahs", "Boulevard of Broken Dreams", "13 Destruction",
            787880, "", "", 4060, DescricaoSocio.BIBLIOTECÁRIO))

        val sc = Scanner(System.`in`)
        fun loginBiblio() {
            var tentativas : Int = 3
            do{
                println("Nome :")
                var nome = sc.nextLine()
                println("Senha : ")
                var senha = sc.nextInt()

                val socioEncontrado = socios.find {it.nomeSocio == nome && it.senha == senha}

                if (socioEncontrado != null){
                    println("Login bem-sucedido!")
                    interageBiblio(socioEncontrado)
                }
                else{
                    println("Usuário ou senha não reconhecidos. Chances = ${tentativas - 1}.")
                    tentativas--
                }
                sc.nextLine()
            }while(tentativas > 0)


        }
        fun interageBiblio(socios: Socio){
            println("Bem-vindo(a), ${socios.nomeSocio}, caro(a) bibliotecário(a).\n")
            println("  ******************************************************\n")
            println("          REGISTROS, CONSULTAS, ALTERAÇÕES          \n\n" +
                    "               RECURSOS HUMANOS\n\n" +
                    "               1 - Bibliotecário\n" +
                    "               2 - Professor\n" +
                    "               3 - Aluno \n" +
                    "               4 - Comunidade externa\n\n" +
                    "               RECURSOS FÍSICOS\n\n" +
                    "               5 - Livros\n\n" +
                    "               RECURSOS SENSÍVEIS\n\n" +
                    "               6 - Visualizar seus dados\n" +
                    "               7 - Alterar seus dados\n\n" +
                    "               RECURSOS DE SISTEMA\n\n" +
                    "               8 - Retornar à página inicial\n" +
                    "               9 - Encerrar operação.\n\n" )

            var opcao = sc.nextInt()

            when(opcao){
                1 -> controlaBiblio()
                2 -> servicoProfessor.controlaProf()
                3 -> servicoAluno.controlaAluno()
                4 -> servicoExterno.controlaExterno()
                5 -> servicoLivro.controlaLivro()
                6 -> visualizaDados(socios)
                7 -> alteraDados()
                8 -> main()
                9 -> System.exit(0)
            }
        }
        fun controlaBiblio(){

        }
        fun visualizaDados(socio : Socio){
            println("***************DADOS DO SÓCIO BIBLIOTECÁRIO************\n")
            println("               > Nome : ${socio.nomeSocio}\n" +
                    "               > Endereço : ${socio.enderecoSocio}\n" +
                    "               > Bairro : ${socio.bairroSocio}\n" +
                    "               > CEP : ${socio.cepSocio}\n" +
                    "               > Telefone : ${socio.telefoneSocio}\n" +
                    "               > Email : ${socio.emailSocio}\n" +
                    "               > Senha : ${socio.senha}\n" +
                    "               > Tipo de sócio : ${socio.tipoSocio}\n")
        }
        fun alteraDados(){

        }
    }

}