package com.skintone.me.database

data class User(
    val name: String,
    val token: String,
    val isLogin: Boolean,
    val gender: String,
    val email: String,
)