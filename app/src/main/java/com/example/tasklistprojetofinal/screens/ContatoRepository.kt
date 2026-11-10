package com.example.tasklistprojetofinal.screens

object ContatoRepository {
    private val contatos = mutableListOf<Contato>()
    private var nextId = 1

    fun adicionar(nome: String, telefone: String): Contato {
        val contato = Contato(nextId++, nome, telefone)
        contatos.add(contato)
        return contato
    }

    fun listar(): List<Contato> = contatos.toList()

    fun buscarPorId(id: Int): Contato? = contatos.find { it.id == id }

    fun excluir(id: Int): Boolean {
        val contato = contatos.find { it.id == id }
        return if (contato != null) {
            contatos.remove(contato)
            true
        } else {
            false
        }
    }
}