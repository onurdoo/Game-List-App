package com.example.midtermproject.view


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.midtermproject.R
import com.example.midtermproject.util.createPlaceHolder
import com.example.midtermproject.util.downloadImage
import com.example.midtermproject.viewmodel.DetailedViewModel
import com.example.midtermproject.viewmodel.FavoriteViewModel


/* View Class for Detailed Page
* For the present this class dont use the viewModel due to midterm project requirments
* With API requests this whole class will be updated
* */
class DetailedFragment : Fragment() {
    private lateinit var viewModel: DetailedViewModel //created view model field
    private var gameId = 0 // Image Id field
    private var gameIm = "" // Image Id field
    private var gameFlag = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detailed, container, false)
    }


    /*For the fragment lifecycles this method called
    * All operations executed in here
    */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var gameImage1 = view?.findViewById<ImageView>(R.id.detailedIm)
        //For the fragment replace button bind with view id
        val backButton = view.findViewById<LinearLayout>(R.id.backButton)
        val favButton = view.findViewById<LinearLayout>(R.id.addFav)
        val redditButton = view.findViewById<LinearLayout>(R.id.reddit)
        val websiteButton = view.findViewById<LinearLayout>(R.id.website)
        var favText = view.findViewById<TextView>(R.id.favoriteText)
        val singleton = FavoriteViewModel.FavList

        arguments?.let {
            gameId = DetailedFragmentArgs.fromBundle(it).gameID
            gameIm = DetailedFragmentArgs.fromBundle(it).imageURL
            gameFlag = DetailedFragmentArgs.fromBundle(it).gameFlag


        }


        // viewModel initialized
        viewModel = ViewModelProvider(this)[DetailedViewModel::class.java]
        viewModel.getData(gameId.toString())
        gameImage1?.downloadImage(gameIm, createPlaceHolder(view?.context!!))
        val game = viewModel.detGameLiveData.value
        if (singleton.favorites.contains(game)) {
            view.findViewById<TextView>(R.id.favoriteText).setText("Favourited")
        }


        observeLiveData()




        favButton.setOnClickListener {
            val game = viewModel.detGameLiveData.value
            if (!singleton.favorites.contains(game)) {
                val game = viewModel.detGameLiveData.value
                singleton.favorites.add(game!!)
                favText.text = "Favourited"
            } else {
                singleton.favorites.remove(game)
                favText.text = "Favourite"

            }

        }

        // with navigation framework fragment replacements are done
        backButton.setOnClickListener {
            if (gameFlag) {
                val action =
                    DetailedFragmentDirections.actionDescFragmentToGameFragment() //action created
                Navigation.findNavController(it).navigate(action)
            } else {
                val action =
                    DetailedFragmentDirections.actionDescFragmentToFavoritesFragment() //action created
                Navigation.findNavController(it).navigate(action)
            }

        }

        redditButton.setOnClickListener{
            openBrowser()
        }
        websiteButton.setOnClickListener{
            openBrowser()
        }


    }

    fun openBrowser(){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
        startActivity(browserIntent)
    }
    //
    fun observeLiveData() {

        viewModel.detGameLiveData.observe(viewLifecycleOwner, Observer { game ->
            game?.let {
                var gameName = view?.findViewById<TextView>(R.id.gameName)
                var gameDesc = view?.findViewById<TextView>(R.id.gameDesc)
                var gameImage = view?.findViewById<ImageView>(R.id.detailedIm)
                val decoded: String = Html
                    .fromHtml(it.description, Html.FROM_HTML_MODE_COMPACT)
                    .toString()
                gameName?.text = it.name
                gameDesc?.text = decoded
                gameImage?.downloadImage(it.background_image, createPlaceHolder(view?.context!!))

            }

        })
    }

}