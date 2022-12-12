package com.example.midtermproject.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.midtermproject.R
import com.example.midtermproject.model.Game
import com.example.midtermproject.view.GameFragmentDirections
import com.squareup.picasso.Picasso
import java.lang.reflect.Array.get
import java.nio.file.Paths.get

class CustomAdapter(val dataSet: ArrayList<Game>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView
        val score: TextView
        val genre: TextView
        val desc: TextView
        val gameImage: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            name = view.findViewById(R.id.title)
            score = view.findViewById(R.id.metacriticPoint)
            genre = view.findViewById(R.id.Genre)
            desc = view.findViewById(R.id.descGhost)
            gameImage = view.findViewById(R.id.gameImage)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.games_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.name.text = dataSet[position].name
        viewHolder.score.text = dataSet[position].score
        viewHolder.genre.text = dataSet[position].genre
        viewHolder.desc.text = dataSet[position].description
        viewHolder.gameImage.setImageDrawable(viewHolder.itemView.context.getDrawable(dataSet[position].gameImage))



        /* with navigation framework fragment replacements are done
        * For the clicking on the elements in the list this process done in adapter class
        */
        viewHolder.itemView.setOnClickListener {
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#E0E0E0"))
            val action = GameFragmentDirections.actionGameFragmentToDescFragment(viewHolder.name.text.toString(),dataSet[position].gameImage)
            Navigation.findNavController(it).navigate(action)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    // Return the new dataset
    fun updateDataList(newList: List<Game>) {
        dataSet.clear()
        dataSet.addAll(newList)
        notifyDataSetChanged()
    }

}