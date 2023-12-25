package entities

import enumerations.Situação

data class Livro(var isbn: Int,
                var titulo : String,
                var ano : Int,
                var edicao : Int,
                var paginas : Int,
                var genero : Genero,
                var autor : Autor,
                var editora : Editora,
                var situacao : Situação
)