import java.util.Scanner

// Variáveis globais
var saldo = 1000.0
val extrato = mutableListOf("Depósito inicial: R$1000.0")
const val senhaCorreta = "3589"

// Função para verificar a senha
fun verificaSenha(): Boolean {
    print("Informe sua senha: ")
    val senha = readLine()
    return senha == senhaCorreta
}

// Função para exibir o saldo
fun exibirSaldo() {
    if (verificaSenha()) {
        println("Seu saldo é: R$$saldo")
    } else {
        println("Senha incorreta. Tente novamente.")
        exibirSaldo() // Tenta novamente caso a senha esteja errada
    }
}

// Função para exibir o extrato
fun exibirExtrato() {
    if (verificaSenha()) {
        println("Extrato de movimentações:")
        extrato.forEach { println(it) }
    } else {
        println("Senha incorreta. Tente novamente.")
        exibirExtrato()
    }
}

// Função para realizar saque
fun realizarSaque() {
    if (verificaSenha()) {
        print("Informe o valor para saque: ")
        val valor = readLine()?.toDoubleOrNull()

        when {
            valor == null || valor <= 0 -> println("Operação não autorizada. Valor inválido.")
            valor > saldo -> println("Operação não autorizada. Saldo insuficiente.")
            else -> {
                saldo -= valor
                extrato.add("Saque: R$$valor")
                println("Saque realizado com sucesso. Novo saldo: R$$saldo")
            }
        }
    } else {
        println("Senha incorreta. Tente novamente.")
        realizarSaque()
    }
}

// Função para realizar depósito
fun realizarDeposito() {
    print("Informe o valor para depósito: ")
    val valor = readLine()?.toDoubleOrNull()

    if (valor == null || valor <= 0) {
        println("Operação não autorizada. Valor inválido.")
    } else {
        saldo += valor
        extrato.add("Depósito: R$$valor")
        println("Depósito realizado com sucesso. Novo saldo: R$$saldo")
    }
}

// Função para realizar transferência
fun realizarTransferencia() {
    if (verificaSenha()) {
        print("Informe o número da conta destino: ")
        val conta = readLine()
        if (conta?.all { it.isDigit() } == true) { // Verifica se o número da conta é válido
            print("Informe o valor para transferência: ")
            val valor = readLine()?.toDoubleOrNull()

            when {
                valor == null || valor <= 0 -> println("Operação não autorizada. Valor inválido.")
                valor > saldo -> println("Operação não autorizada. Saldo insuficiente.")
                else -> {
                    saldo -= valor
                    extrato.add("Transferência para conta $conta: R$$valor")
                    println("Transferência realizada com sucesso. Novo saldo: R$$saldo")
                }
            }
        } else {
            println("Número de conta inválido.")
        }
    } else {
        println("Senha incorreta. Tente novamente.")
        realizarTransferencia()
    }
}

// Função de menu principal
fun menu() {
    println("Escolha uma opção:")
    println("1 - Saldo")
    println("2 - Extrato")
    println("3 - Saque")
    println("4 - Depósito")
    println("5 - Transferência")
    println("6 - Sair")
}

// Função de erro para opção inválida
fun erro() {
    println("Opção inválida. Tente novamente.")
}

// Função main (ponto de entrada)
fun main() {
    print("Qual é o seu nome? ")
    val nome = readLine()
    println("Olá $nome, é um prazer ter você por aqui!")

    while (true) {
        menu()
        print("Escolha uma opção: ")
        when (readLine()?.toIntOrNull()) {
            1 -> exibirSaldo()
            2 -> exibirExtrato()
            3 -> realizarSaque()
            4 -> realizarDeposito()
            5 -> realizarTransferencia()
            6 -> {
                println("$nome, foi um prazer ter você por aqui!")
                break
            }
            else -> erro()
        }
    }
}
