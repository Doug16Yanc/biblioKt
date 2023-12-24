package services

import entities.Socio

class Dados {
    companion object{
        fun visualizaDados(socio : Socio){
            println("********VISUALIZAÇÃO DE DADOS DO SÓCIO***********\n")
            println("       > Nome do estudante: ${socio.nomeSocio}\n" +
                    "       > Id do estudante: ${socio.id}\n" +
                    "       > Endereço : ${socio.enderecoSocio}\n" +
                    "       > Cep : ${socio.cepSocio}\n" +
                    "       > Email : ${socio.emailSocio}\n" +
                    "       > Tipo de sócio : ${socio.tipoSocio}\n")
        }
    }
}