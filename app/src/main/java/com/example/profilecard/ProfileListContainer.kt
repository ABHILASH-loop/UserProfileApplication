package com.example.profilecard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController

@Composable
fun UserProfileList(navController: NavController?, userProfiles: List<UserInfo> = dummyUsers) {
    Scaffold(topBar = {
        AppBar(
            title = "Users Status",
            icon = Icons.Default.Person
        ) {}
    }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LazyColumn {
                items(userProfiles) { userprofile ->
                    ProfileCard(userInfo = userprofile){
                        navController?.navigate(route = "DisplayUserInfo/${userprofile.user_id}")
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileCard(userInfo: UserInfo, onCardClick: (() -> Unit)? = null) {
    Card(
        backgroundColor = Color.White,
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 24.dp, end = 24.dp, top = 16.dp)
            .clickable { onCardClick?.invoke() }
    ) {
        ConstraintLayout(
            profileCardConstraints(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            ProfilePicture(
                profilePicture = userInfo.profilePic,
                onlineStatus = userInfo.status,
                imageSize = 72.dp
            )
            ProfileText(
                userName = userInfo.userName,
                onlineStatus = userInfo.status,
                textSize = MaterialTheme.typography.h5
            )
        }

    }
}

@Composable
fun ProfilePicture(profilePicture: Int, onlineStatus: Boolean, imageSize: Dp) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            3.dp,
            color = if (onlineStatus) {
                Color(0xFF98fb98)
            } else {
                Color.Red
            }
        ),
        modifier = Modifier
            .wrapContentSize()
            .layoutId("ProfilePicture")
    ) {
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(profilePicture)
                .build(),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(imageSize)
        )
    }
}

@Composable
fun ProfileText(userName: String, onlineStatus: Boolean, textSize: TextStyle) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .layoutId("ProfileText")
    ) {
        CompositionLocalProvider(
            LocalContentAlpha provides (
                    if (!onlineStatus) {
                        ContentAlpha.medium
                    } else {
                        1f
                    }
                    )
        ) {
            Text(
                text = userName,
                style = textSize,
            )
        }

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = if (onlineStatus) {
                    "Active"
                } else {
                    "Offline"
                },
                style = MaterialTheme.typography.body2,
            )
        }
    }
}

@Composable
fun profileCardConstraints(): ConstraintSet {
    return ConstraintSet {
        val profilePicture = createRefFor("ProfilePicture")
        val profileText = createRefFor("ProfileText")

        constrain(profilePicture) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
        }

        constrain(profileText) {
            start.linkTo(profilePicture.end)
            top.linkTo(profilePicture.top)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardPreview() {
    UserProfileList(null)
}