package com.pushkaranand.restapiretrofitjsonadvance.models

/**
 * Created by Pushkar on 02-Jul-17.
 */
data class Comment (
        val postId: Int,
        val id: Int,
        val name: String,
        val email: String,
        val body: String
)