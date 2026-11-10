package com.example.tasklistprojetofinal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tasklistprojetofinal.screens.CadastroScreens
import com.example.tasklistprojetofinal.screens.ConfirmarExclusaoScreens
import com.example.tasklistprojetofinal.screens.DetalhesScreens
import com.example.tasklistprojetofinal.screens.HomeScreens
import com.example.tasklistprojetofinal.screens.ContatoRepository

object Routes {
    const val HOME = "home"
    const val CADASTRO = "cadastro"
    const val DETALHES = "detalhes"
    const val CONFIRMAR_EXCLUSAO = "confirmar_exclusao"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreens(
                onCadastrar = {
                    navController.navigate(Routes.CADASTRO)
                },
                onVerDetalhes = { contatoId: Int ->
                    navController.navigate("${Routes.DETALHES}/$contatoId")
                }
            )
        }

        composable(Routes.CADASTRO) {
            CadastroScreens(
                onVoltar = { navController.popBackStack() },
                onSalvar = { navController.popBackStack() }
            )
        }

        composable(
            route = "${Routes.DETALHES}/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val contatoId = backStackEntry.arguments?.getInt("id") ?: 0

            DetalhesScreens(
                contatoId = contatoId,
                onVoltar = { navController.popBackStack() },
                onExcluir = {
                    navController.navigate("${Routes.CONFIRMAR_EXCLUSAO}/$contatoId")
                }
            )
        }

        // Nova tela de confirmação
        composable(
            route = "${Routes.CONFIRMAR_EXCLUSAO}/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val contatoId = backStackEntry.arguments?.getInt("id") ?: 0

            ConfirmarExclusaoScreens(
                contatoId = contatoId,
                onCancelar = { navController.popBackStack() },
                onConfirmar = {
                    ContatoRepository.excluir(contatoId)
                    // Volta duas telas (confirmação + detalhes) até a Home
                    navController.popBackStack(Routes.HOME, inclusive = false)
                }
            )
        }
    }
}