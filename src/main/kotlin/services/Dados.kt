package services

import application.geraInteracao
import application.main
import entities.Socio
import enumerations.DescricaoSocio
import services.servicoBiblio.Companion.controlaBiblio
import services.servicoAluno.Companion.controlaAluno
import services.servicoBiblio.Companion.interageBiblio
import services.servicoProfessor.Companion.controlaProf
import services.servicoExterno.Companion.controlaUser
import java.util.*

class Dados {
    companion object{
        val socios: MutableList<Socio> = ArrayList()
        val sc = Scanner(System.`in`)
        fun interageTodos(socio: Socio){
            println("******INTERAÇÃO COM SÓCIO*********\n")
            when(socio.tipoSocio){
                DescricaoSocio.BIBLIOTECÁRIO -> {
                    interageBiblio(socio)
                }
                DescricaoSocio.ALUNO -> {
                    controlaAluno(socio)
                }
                DescricaoSocio.PROFESSOR -> {
                    controlaProf(socio)
                }
                DescricaoSocio.EXTERNO -> {
                   controlaUser(socio)
                }
            }
        }
        fun visualizaDados(socio : Socio){
            println("********VISUALIZAÇÃO DE DADOS DO SÓCIO***********\n")
            println("       > Nome do sócio: ${socio.nomeSocio}\n" +
                    "       > Id do sócio: ${socio.id}\n" +
                    "       > Endereço : ${socio.enderecoSocio}\n" +
                    "       > Cep : ${socio.cepSocio}\n" +
                    "       > Email : ${socio.emailSocio}\n" +
                    "       > Tipo de sócio : ${socio.tipoSocio}\n\n")
            interageTodos(socio)
        }
        fun geraComprovante(socio : Socio){
            println("********COMPROVANTE DE CADASTRO NO SISTEMA***************\n")
            println("       > Nome do sócio: ${socio.nomeSocio}\n" +
                    "       > Id do sócio: ${socio.id}\n" +
                    "       > Endereço : ${socio.enderecoSocio}\n" +
                    "       > Cep : ${socio.cepSocio}\n" +
                    "       > Email : ${socio.emailSocio}\n" +
                    "       > Tipo de sócio : ${socio.tipoSocio}\n" +
                    "       > Id da operação : ${UUID.randomUUID()}\n\n")
            interageTodos(socio)
        }
        fun alteraDados(socio: Socio){
            println("**********ALTERAÇÃO DE DADOS DO SÓCIO**********\n")
            println("Caríssimo, ${socio.nomeSocio}, você pode modificar\n" +
                    "vários dados sensíveis seus, exceto id, nome e seu tipo.\n")
            while (true) {
                println("Escolha o que deseja alterar:\n" +
                "           1 - Endereço         \n" +
                "           2 - CEP              \n" +
                "           3 - E-mail           \n" +
                "           4. Senha            \n" +
                "           5 - Retornar á sua página\n")
                var opcao = sc.nextInt()
                sc.nextLine()
                when (opcao) {
                    1 -> {
                        println("Digite o novo endereço:")
                        val novoEndereco = sc.nextLine()
                        socio.enderecoSocio = novoEndereco
                    }
                    2 -> {
                        println("Digite o novo CEP:")
                        val novoCep = sc.nextInt()
                        socio.cepSocio = novoCep
                    }
                    3 -> {
                        println("Digite o novo e-mail:")
                        val novoEmail = sc.nextLine()
                        socio.emailSocio = novoEmail
                    }
                    4 -> {
                        println("Digite a nova senha:")
                        val novaSenha = sc.nextLine()
                        socio.senha = novaSenha
                    }
                    5 -> break
                    else -> println("Opção inválida. Tente novamente.")
                }
            }

            println("Dados alterados com sucesso!\n")
            visualizaDados(socio)

        }
        fun removerCadastro(socio : Socio){
            println("************REMOÇÃO DE CADASTRO DO SÓCIO*********\n")

            if (socio.exemplaresEmprestados.isEmpty()){
                println("Caríssimo(a), ${socio.nomeSocio}, lamentamos sua decisão de " +
                        "se despedir de nosso sistema de controle bibliotecário, uma vez\n" +
                        "removido seu cadastro, precisará registrar-se novamente, caso" +
                        "desejes ter acesso a nossos serviços.\n\n")
                println("Confirme sua senha:")
                var senha = sc.nextLine()

                if (socio.senha == senha ){
                    println("Realizando processo de remoção...")
                    socios.remove(socio)
                    comprovaRemocao(socio)
                }
                else {
                    println(
                        "Senha não reconhecida, caríssimo(a) ${socio.nomeSocio},\n" +
                                "por favor, solicite novo processo de remoção.\n"
                    )
                    interageTodos(socio)
                }
            }
            else{
                println("Solicitação de remoção não atendida, há pendências antes a resolver...\n")
                println("É necessário realizar a devolução do(s) seguinte(s) livro(s):\n\n")
                println("ISBN\t\tTítulo\t\tAutor\t\tEditora\t\n")
                for ((index, exemplar) in socio.exemplaresEmprestados.withIndex()) {
                    println("${exemplar.isbn} - ${exemplar.titulo} - ${exemplar.autor.nomeAutor} - ${exemplar.editora.nomeEditora}\n\n")
                }
                interageTodos(socio)
            }
        }
        fun comprovaRemocao(socio: Socio){
            println("********COMPROVANTE DE REMOÇÃO DO SISTEMA***************\n")
            println("       > Nome do sócior: ${socio.nomeSocio}\n" +
                    "       > Id do sócio: ${socio.id}\n" +
                    "       > Endereço : ${socio.enderecoSocio}\n" +
                    "       > Cep : ${socio.cepSocio}\n" +
                    "       > Email : ${socio.emailSocio}\n" +
                    "       > Tipo de sócio : ${socio.tipoSocio}\n" +
                    "       > Id da operação : ${UUID.randomUUID()}\n\n")
            main()
        }
    }
}