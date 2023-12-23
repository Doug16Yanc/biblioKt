package entities

data class Livro(var isbn: Long,
                var titulo : String,
                var ano : Int,
                var edicao : Int,
                var paginas : Int,
                val genero : Genero,
                val autor : Autor,
                val editora : Editora)