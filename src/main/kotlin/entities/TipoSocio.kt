package entities

import enumerations.DescricaoSocio

data class TipoSocio(
    var descricaoSocio : DescricaoSocio,
    var limiteExemplares : Int,
    var limiteRenovacoes : Int
)
