package com.example.midtermproject.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.midtermproject.R
import com.example.midtermproject.adapter.CustomAdapter
import com.example.midtermproject.model.Game
import com.example.midtermproject.viewmodel.GameViewModel

// View Class for Home Page
class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel //created view model field
    private val recyclerViewAdapter = CustomAdapter(arrayListOf()) //List Adapter created


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)


    }

    /*For the fragment lifecycles this method called
    * All operations executed in here
    */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // viewModel initialized
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        viewModel.refreshhData() //


        //For the fragment replace button bind with view id
        val favButton = view.findViewById<LinearLayout>(R.id.favButton)

        //recyclerView initialized
        val myRecyclerView = view.findViewById<RecyclerView>(R.id.gamesList)
        myRecyclerView.layoutManager = LinearLayoutManager(context)

        myRecyclerView.adapter = recyclerViewAdapter //recyclerView adapted bind

        val  swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeR)

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshhData()
            swipeRefreshLayout.isRefreshing = false
        }
        observeLiveData(myRecyclerView) // list elements initialized


        // with navigation framework fragment replacements are done
        favButton.setOnClickListener() {
            val action = GameFragmentDirections.actionGameFragmentToFavoritesFragment() // created an action
            Navigation.findNavController(it).navigate(action)
        }


    }


    /* Designed to adapt to API
    * within .let structure  called adapter method
    * updated dataset
     */
    fun observeLiveData(myRecyclerView: RecyclerView) {
        viewModel.games.observe(viewLifecycleOwner, Observer { games ->
            games?.let {
                myRecyclerView.visibility = View.VISIBLE
                recyclerViewAdapter.updateDataList(games)

            }
        })

        /* viewModel.gameErrMsg.observe(this,Observer{ err->
            err?.let{
                if (it)
        }
        })*/
    }


}