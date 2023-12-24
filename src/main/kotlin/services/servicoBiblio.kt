package services

import application.main
import entities.Socio
import enumerations.DescricaoSocio
import services.servicoAluno.Companion.aluno
import services.servicoExterno.Companion.usuario
import services.servicoProfessor.Companion.professor
import java.util.Scanner

class servicoBiblio {


    companion object {
        val aluno = mutableListOf<Socio>()
        val professor = mutableListOf<Socio>()
        val usuario = mutableListOf<Socio>()

        val users = mutableListOf<Socio>().apply {
            addAll(aluno)
            addAll(professor)
            addAll(usuario)
        }

        val socios  = listOf(Socio(12756, "Dooglahs", "Boulevard of Broken Dreams",
            78788, "","4060", DescricaoSocio.BIBLIOTECÁRIO))

        val sc = Scanner(System.`in`)
        fun loginBiblio() {
            var tentativas : Int = 3
            do{
                println("Nome :")
                var nome = sc.nextLine()

                println("Senha de acesso:")
                var senha = sc.nextLine()


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
                    "               1 - Ir para funções de bibliotecário\n" +
                    "               2 - Consultar usuários\n\n" +
                    "               RECURSOS FÍSICOS\n\n" +
                    "               3 - Livros\n\n" +
                    "               RECURSOS SENSÍVEIS\n\n" +
                    "               4 - Visualizar seus dados\n" +
                    "               5 - Alterar seus dados\n\n" +
                    "               RECURSOS DE SISTEMA\n\n" +
                    "               6 - Retornar à página inicial\n" +
                    "               7 - Encerrar operação.\n\n" )

            var opcao = sc.nextInt()

            when(opcao){
                1 -> controlaBiblio()
                2 -> consultaUsuarios(socios)
                3 -> servicoLivro.controlaLivro()
                4 -> visualizaDados(socios)
                5 -> alteraDados()
                6 -> main()
                7 -> System.exit(0)
            }
        }
        fun controlaBiblio(){
            println("CARO BIBLIOTECÁRIO, ESCOLHA UMA OPÇÃO")
            println("       1 - Regitrar um novo bibliotecário  \n" +
                    "       2 - Consultar bibliotecário         \n" +
                    "       3 - Listar bibliotecário            \n" +
                    "       4 - Remover bibliotecário           \n" +
                    "       5 - ")
        }
        fun visualizaDados(socio : Socio){
            println("***************DADOS DO SÓCIO BIBLIOTECÁRIO************\n")
            println("               > Nome : ${socio.nomeSocio}\n" +
                    "               > Endereço : ${socio.enderecoSocio}\n" +
                    "               > CEP : ${socio.cepSocio}\n" +
                    "               > Email : ${socio.emailSocio}\n" +
                    "               > Tipo de sócio : ${socio.tipoSocio}\n")
            interageBiblio(socio)
        }
        fun alteraDados(){

        }
        fun consultaUsuarios(socio : Socio){
            if (!users.isEmpty()){
                for (socio in users){
                    println("***************DADOS DOS SÓCIOS************\n")
                    println("               > Nome : ${socio.nomeSocio}\n" +
                            "               > Endereço : ${socio.enderecoSocio}\n" +
                            "               > CEP : ${socio.cepSocio}\n" +
                            "               > Email : ${socio.emailSocio}\n" +
                            "               > Tipo de sócio : ${socio.tipoSocio}\n")
                }
            }
            else{
                println("Nenhum sócio ainda além de você.")
            }
            interageBiblio(socio)
        }
    }

}