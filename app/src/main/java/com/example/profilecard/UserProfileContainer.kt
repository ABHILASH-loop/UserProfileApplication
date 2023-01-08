package com.example.profilecard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DisplayUserInfo(user_id: Int, navController: NavController?) {
    val userInfo: UserInfo = dummyUsers.first { userInfo ->
        user_id == userInfo.user_id
    }
    Scaffold(topBar = {
        AppBar(
            title = "User Profile Details",
            icon = Icons.Default.ArrowBack
        ) {
            navController?.navigateUp()
        }
    }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                ProfilePicture(
                    profilePicture = userInfo.profilePic,
                    onlineStatus = userInfo.status,
                    imageSize = 240.dp
                )
                ProfileText(
                    userName = userInfo.userName,
                    onlineStatus = userInfo.status,
                    textSize = MaterialTheme.typography.h3
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayUserPreview() {
    DisplayUserInfo(user_id = dummyUsers[0].user_id, navController = null)
}
