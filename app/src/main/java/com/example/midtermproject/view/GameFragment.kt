package com.example.midtermproject.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.compose.ui.text.toLowerCase
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
    private lateinit var search: SearchView

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

        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeR)

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshhData()
            swipeRefreshLayout.isRefreshing = false
        }
        observeLiveData(myRecyclerView) // list elements initialized
        search = view.findViewById<SearchView>(R.id.gameSearchBar)
        search.clearFocus()
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false

            }

            override fun onQueryTextChange(p0: String?): Boolean {
                val gameList = viewModel.games.value
                if (p0!!.length > 3) {
                    filter(p0!!, gameList!!)
                } else if (p0.length!! == 0) {
                    filter(p0!!, gameList!!)
                }

                return false
            }

        })


        // with navigation framework fragment replacements are done
        favButton.setOnClickListener() {
            val action =
                GameFragmentDirections.actionGameFragmentToFavoritesFragment() // created an action
            Navigation.findNavController(it).navigate(action)
        }


    }

    private fun filter(text: String, a: List<Game>) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<Game> = ArrayList()

        // running a for loop to compare elements.
        for (item in a) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.name!!.lowercase()!!.contains(text.lowercase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (!filteredlist.isEmpty()) {

            // at last we are passing that filtered
            // list to our adapter class.
            recyclerViewAdapter.filterList(filteredlist)
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


    }


}