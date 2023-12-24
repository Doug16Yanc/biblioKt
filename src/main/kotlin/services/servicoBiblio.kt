package services

import application.main
import entities.Socio
import enumerations.DescricaoSocio
import java.util.*
import kotlin.collections.ArrayList

class servicoBiblio {


    companion object {
        val socios: MutableList<Socio> = ArrayList()
        val users =  mutableListOf<Socio>()
        init {

                users.addAll(servicoAluno.aluno)
                users.addAll(servicoProfessor.professor)
                users.addAll(servicoExterno.usuario)
                users.addAll(socios)
        }
        init {
            socios.add(
                Socio(
                    12756,
                    "Dooglahs",
                    "Boulevard of Broken Dreams",
                    78788,
                    "",
                    "4060",
                    DescricaoSocio.BIBLIOTECÁRIO
                )
            )
        }
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
                4 -> Dados.visualizaDados(socios)
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
            var opcao = sc.nextInt()

            when(opcao){
                1 -> {
                    cadastraBiblio()
                }
                2 -> {
                    consultaBiblio()
                }
                3 -> {
                    listaBiblio()
                }
                4 -> {
                    removeBiblio()
                }
                else -> {
                    println("Opção não possível.\n")
                }
            }
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
        fun cadastraBiblio(){
            var id = servicoProfessor.gerarId()

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

            val socio = Socio(id, nome, endereco, cep, email, senha, DescricaoSocio.BIBLIOTECÁRIO )

            socios.add(socio)

            geraComprovante(socio)

        }
        fun geraComprovante(biblio : Socio){
            println("********COMPROVANTE DE CADASTRO NO SISTEMA***************\n")
            println("       > Nome do estudante: ${biblio.nomeSocio}\n" +
                    "       > Endereço : ${biblio.enderecoSocio}\n" +
                    "       > Cep : ${biblio.cepSocio}\n" +
                    "       > Email : ${biblio.emailSocio}\n" +
                    "       > Tipo de sócio : ${biblio.tipoSocio}\n" +
                    "       > Id da operação : ${UUID.randomUUID()}\n")
        }
        fun consultaBiblio(){

        }
        fun listaBiblio(){

        }
        fun removeBiblio(){

        }
    }

}