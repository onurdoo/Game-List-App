package com.example.midtermproject.model


import com.squareup.moshi.Json

data class Game(
    @Json(name = "id") val id: Int,

    @Json(name = "name") val name: String?,

    @Json(name = "metacritic") val metacritic: Int?,

    @Json(name = "genres") val genres: List<Genres>,
    @Json(name = "description") val description: String,
    @Json(name = "background_image") val background_image: String
) {
}