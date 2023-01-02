package com.example.midtermproject.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midtermproject.R

import com.example.midtermproject.adapter.CustomAdapter2
import com.example.midtermproject.model.Game

import com.example.midtermproject.viewmodel.FavoriteViewModel
import com.example.midtermproject.viewmodel.GameViewModel


// View Class for 2nd Page
class FavoritesFragment : Fragment() {
     var game : Game? = null
   // private lateinit var viewModel: FavoriteViewModel
    private val recyclerViewAdapter = CustomAdapter2(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }


    /*For the fragment lifecycles this method called
    * All operations executed in here
    */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // viewModel initialized
        //viewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]

        val singleton = FavoriteViewModel.FavList

        //For the fragment replace button bind with view id
        val gameButton = view.findViewById<LinearLayout>(R.id.gameButtonFav)

        //recyclerView initialized
        val myRecyclerView = view.findViewById<RecyclerView>(R.id.gamesListFav)
        myRecyclerView.layoutManager = LinearLayoutManager(context)//recyclerView adapted bind
        myRecyclerView.adapter = recyclerViewAdapter // list elements initialized
        recyclerViewAdapter.updateDataList(singleton.favorites)
        myRecyclerView.visibility = View.VISIBLE
        val count = recyclerViewAdapter.itemCount
        if (count!= 0){
            view.findViewById<TextView>(R.id.gameTabFav).setText("Favourites($count)")
        }







        // with navigation framework fragment replacements are done
        gameButton.setOnClickListener {
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToGameFragment()// created an action
            Navigation.findNavController(it).navigate(action)
        }
    }

    /* Designed to adapt to API
    * within .let structure  called adapter method
    * updated dataset
     */
    /*fun observeLiveData(myRecyclerView: RecyclerView) {
        viewModel.favorites.observe(viewLifecycleOwner, Observer { games ->
            games?.let {
                myRecyclerView.visibility = View.VISIBLE
                recyclerViewAdapter.updateDataList(games)

            }
        })


    }*/


}