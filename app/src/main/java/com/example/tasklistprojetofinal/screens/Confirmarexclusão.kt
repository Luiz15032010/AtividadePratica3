package com.example.tasklistprojetofinal.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmarExclusaoScreens(
    contatoId: Int,
    onCancelar: () -> Unit,
    onConfirmar: () -> Unit
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
            text = "Confirmar Exclusão",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (contato != null) {
            Text(
                text = "Deseja realmente excluir o contato?",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = contato.nome,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = contato.telefone,
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            Text("Contato não encontrado")
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onConfirmar,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            )
        ) {
            Text("Sim, excluir")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = onCancelar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancelar")
        }
    }
}