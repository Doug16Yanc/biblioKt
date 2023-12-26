package services

import application.geraInteracao
import application.main
import entities.Socio
import enumerations.DescricaoSocio
import services.servicoBiblio.Companion.controlaBiblio
import services.servicoAluno.Companion.controlaAluno
import services.servicoProfessor.Companion.controlaProf
import services.servicoExterno.Companion.controlaUser
import services.servicoBiblio.Companion.socios
import java.util.*

class Dados {
    companion object{
        val sc = Scanner(System.`in`)
        fun interageTodos(socio: Socio){
            println("******INTERAÇÃO COM SÓCIO*********\n")
            when(socio.tipoSocio){
                DescricaoSocio.BIBLIOTECÁRIO -> {
                    controlaBiblio(socio)
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
        fun removerCadastro(socio : Socio){
            println("************REMOÇÃO DE CADASTRO DO SÓCIO*********\n")

            if (socio.exemplaresEmprestados.isEmpty()){
                println("Caríssimo(a), ${socio.nomeSocio}, lamentamos sua decisão de " +
                        "se despedir de nosso sistema de controle bibliotecário, uma vez\n" +
                        "removido seu cadastro, precisará registrar-se novamente, caso" +
                        "desejes ter acesso a nossos serviços.\n\n")
                println("Confirme sua senha:")
                var senha = sc.next()

                val senhaCorreta = socios.find{ it.senha == senha}

                if (senhaCorreta != null){
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