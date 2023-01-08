package com.example.profilecard

data class UserInfo(
    val user_id: Int,
    val profilePic: Int,
    val userName: String,
    val status: Boolean
)

val dummyUsers = listOf(
    UserInfo(user_id = 0 ,profilePic = R.drawable.dhoni, userName = "M.S.Dhoni", status = true),
    UserInfo(user_id = 1, profilePic = R.drawable.virat, userName = "Virat Kholi", status = false),
)
