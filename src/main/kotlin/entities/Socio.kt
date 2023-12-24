package entities

import enumerations.DescricaoSocio

data class Socio(
    var id: Long,
    var nomeSocio: String,
    var enderecoSocio: String,
    var cepSocio: Int,
    var emailSocio: String,
    var senha: String,
    var tipoSocio: DescricaoSocio
)

