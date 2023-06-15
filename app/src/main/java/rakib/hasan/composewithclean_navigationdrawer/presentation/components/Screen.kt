package rakib.hasan.composewithclean_navigationdrawer.presentation.components

sealed class Screen(val route: String){
    object HomeScreen: Screen("home_screen")
    object AboutScreen: Screen("about_screen")
}
