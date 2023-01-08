package com.example.profilecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.profilecard.ui.theme.ProfileCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileCardTheme {
                ProfileApplication()
            }
        }
    }
}

@Composable
fun ProfileApplication(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "UserProfileList"){
        composable(route = "UserProfileList"){
            UserProfileList(navController)
        }
        composable(
            route = "DisplayUserInfo/{user_id}",
            arguments = listOf(navArgument("user_id"){type = NavType.IntType})
        ) {NavStacEntry ->
            DisplayUserInfo(NavStacEntry.arguments!!.getInt("user_id"), navController)
        }
    }
}

@Composable
fun AppBar(title: String,
           icon: ImageVector,
           iconClickEvent: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h5,
                fontFamily = FontFamily.SansSerif
            )
        },
        navigationIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "Home Icon",
                modifier = Modifier.padding(16.dp)
                    .clickable { iconClickEvent.invoke() }
            )
        },
        backgroundColor = Color.Yellow
    )

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProfileCardTheme {
        ProfileApplication()
    }
}