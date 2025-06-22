import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shayriapp.ShayriAppScreen.ShayriScreenItem


@Composable
fun ShayriRouting(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = ShayriScreenItem.HomeScreen.route) {
        composable(ShayriScreenItem.HomeScreen.route) {
            HomeScreenExample(navHostController)
        }
        composable (ShayriScreenItem.catagoryScreen.route){
            CatagoryScreen(navHostController)
        }
        composable (ShayriScreenItem.ShayriListScreen.route+"/{title}"){
            val title = it.arguments?.getString("title")
            ShayriListScreen(navHostController,title)
        }
        composable (ShayriScreenItem.FinalScreen.route+"/{item}"){
          FinalScreen(it.arguments?.getString("item").toString())
        }
    }

}

//@Preview
//@Composable
//fun Example () {
//    val navHostController = rememberNavController()
//    ShayriRouting(navHostController = navHostController)
//}