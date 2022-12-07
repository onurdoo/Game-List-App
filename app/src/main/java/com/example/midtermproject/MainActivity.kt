package com.example.midtermproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gameFragment = GameFragment()
        val favFragment = FavoritesFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainFrame, gameFragment)
            commit()
        }
        var button1 :Button = findViewById(R.id.gameButton)
        var button2 :Button = findViewById(R.id.favButton)

        button1.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.mainFrame, gameFragment)
                addToBackStack(null)
                commit()

            }
        }
        button2.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.mainFrame, favFragment)
                addToBackStack(null)
                commit()
            }
        }



        /*fun gamesTab(view: View){
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.replace(R.id.mainFrame,gameFragment).commit()
        }

        fun favoritesTab(view: View){
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.replace(R.id.mainFrame,favFragment).commit()
        }*/

    }

}