package com.example.tasklistprojetofinal.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetalhesScreens(
    contatoId: Int,
    onVoltar: () -> Unit,
    onExcluir: () -> Unit
) {
    val contato = ContatoRepository.buscarPorId(contatoId)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Detalhes do Contato",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (contato != null) {
            Text("Nome: ${contato.nome}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Telefone: ${contato.telefone}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("ID: ${contato.id}", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onExcluir,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Excluir Contato")
            }
        } else {
            Text("Contato não encontrado")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onVoltar) {
            Text("Voltar")
        }
    }
}