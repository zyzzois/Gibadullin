package com.example.popular.data.network.dto

data class MovieDto(
    val items: List<Item>,
    val total: Int,
    val totalPages: Int
)