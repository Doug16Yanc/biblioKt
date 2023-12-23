package entities

import enumerations.DescricaoSocio

data class Socio(
    var nomeSocio : String,
    var enderecoSocio : String,
    var bairroSocio : String,
    var cepSocio : Long,
    var telefoneSocio : String,
    var emailSocio : String,
    var senha : Int,
    var tipoSocio : DescricaoSocio
)

