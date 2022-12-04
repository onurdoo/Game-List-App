package com.example.midtermproject


import android.widget.ImageView

class Game {
    var name: String?
    var score: String?
    var genre: String?
    var description: String?
    var gameImage: ImageView?

    constructor(
        name: String?,
        score: String?,
        genre: String?,
        description: String?,
        gameImage: ImageView?
    ) {
        this.name = name
        this.score = score
        this.genre = genre
        this.description = description
        this.gameImage = gameImage
    }


}