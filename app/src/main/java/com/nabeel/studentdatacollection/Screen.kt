package com.nabeel.studentdatacollection

import androidx.navigation.NavArgs

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object FormScreen : Screen("detail_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { args ->
                append("/$args")
            }
        }
    }
}
