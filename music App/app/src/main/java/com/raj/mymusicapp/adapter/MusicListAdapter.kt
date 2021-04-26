package com.raj.mymusicapp.adapter
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raj.mymusicapp.R
import com.raj.mymusicapp.model.DisplayMusicList
import com.raj.mymusicapp.ui.DetailsScreenActivity

class MusicListAdapter(private val dataSet:List<DisplayMusicList>) :
    RecyclerView.Adapter<MusicListAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mainContainer: ConstraintLayout = view.findViewById(R.id.mainContainer)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val name : TextView = view.findViewById(R.id.name)
        val listeners: TextView = view.findViewById(R.id.listeners)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_music_list, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.mainContainer.setOnClickListener()
        {
            val intent = Intent(it.context, DetailsScreenActivity::class.java)
            intent.putExtra("url",dataSet[position].url)
            it.context.startActivity(intent)
        }

        val media =dataSet[position].image?.get(0)?.text
        if(media != null)
        Glide.with(viewHolder.imageView.context).load(media).into(viewHolder.imageView)
        viewHolder.name.text = dataSet[position].name
        viewHolder.listeners.text = dataSet[position].listeners + " "+ viewHolder.imageView.context.getString(R.string.artists_listeners)
    }

    override fun getItemCount() = dataSet.size

}