package com.example.midtermproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var aa = Game("divinity","98","rpg","metacritic",null)
        var bb = Game("Life is Strange","100","lesbian love","metacritic",null)
        var cc = Game("gtav","96","action","metacritic",null)
        var dd = Game("gtav","96","action","metacritic",null)
        var ee = Game("gtav","96","action","metacritic",null)
        var ff = Game("gtav","96","action","metacritic",null)
        var gg = Game("gtav","96","action","metacritic",null)

        val games = arrayOf(
            aa,bb,cc,dd,ee,ff,gg
        )

        var customAdapter = CustomAdapter(games)

        var myRecyclerView = findViewById<RecyclerView>(R.id.gamesList)
        myRecyclerView.layoutManager = LinearLayoutManager(this)
        myRecyclerView.adapter = customAdapter
       // myRecyclerView.setOnClickListener { myRecyclerView.setBackgroundColor(getColor(R.color.aa))}


    }

}