package application

import services.servicoAluno
import services.servicoBiblio
import services.servicoExterno
import services.servicoProfessor
import java.util.*

fun main() {
    println("*******PÁGINA DE CONTROLE DE SISTEMA BIBLIOTECÁRIO*******\n")
    geraInteracao()
}

fun geraInteracao(){

    val sc = Scanner(System.`in`)
    println("Que tipo de sócio você é? ")
    println("   B/b - bibliotecário  \n")
    println("   P/p - professor      \n")
    println("   A/a - estudante      \n")
    println("   E/e - externo        \n")

    var opcao = sc.next()

    when(opcao.lowercase(Locale.getDefault())){
        "b" -> {servicoBiblio.loginBiblio()}
        "p" -> {servicoProfessor.loginProf()}
        "a" -> {servicoAluno.loginAluno()}
        "e" -> {servicoExterno.loginExterno()}
        else -> {
            println("Opção não possível.\n")
            System.exit(0)
        }
    }
}