import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nabeel.studentdatacollection.Screen
import com.nabeel.studentdatacollection.database.RoomDB
import com.nabeel.studentdatacollection.design.StudentForm
import com.nabeel.studentdatacollection.design.StudentList

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val studentDao = RoomDB.getDatabase(LocalContext.current).studentDao()
    NavHost(navController = navController , startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            StudentList(navController, studentDao)
        }
        composable(route = Screen.FormScreen.route) {
            StudentForm(studentDao,navController)
        }
    }
}