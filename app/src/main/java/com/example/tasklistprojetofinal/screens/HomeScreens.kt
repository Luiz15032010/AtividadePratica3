package com.example.tasklistprojetofinal.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreens(
    onCadastrar: () -> Unit,
    onVerDetalhes: (Int) -> Unit
) {
    val contatos = ContatoRepository.listar()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Lista de Contatos",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (contatos.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text("Nenhum contato cadastrado")
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(contatos) { contato ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onVerDetalhes(contato.id) }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(contato.nome, style = MaterialTheme.typography.titleMedium)
                            Text(contato.telefone, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onCadastrar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cadastrar Novo")
        }
    }
}