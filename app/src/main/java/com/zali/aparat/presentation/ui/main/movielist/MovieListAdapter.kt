package com.zali.aparat.presentation.ui.main.movielist

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.zali.aparat.R
import com.zali.aparat.domain.models.RelatedContent
import com.bumptech.glide.request.target.Target
import com.zali.aparat.domain.models.ContentAttributes
import de.hdodenhof.circleimageview.CircleImageView

class MovieListAdapter(private val interaction: Interaction? = null)
    : RecyclerView.Adapter<MovieViewHolder>() {

    private var relatedContent: List<RelatedContent> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_main, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (relatedContent.isEmpty()){
            0
        }else{
            relatedContent.size
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = relatedContent[position]

        showImage(holder.itemView.context,data.contentAttributes.bigPoster,holder.imagePoster)
        Glide.with(holder.itemView.context)
            .load(data.contentAttributes.profilePhoto)
            .into(holder.circleImage)
        holder.txtTile.text = data.contentAttributes.title
        holder.txtDuration.text = data.contentAttributes.duration
        holder.txtSenderName.text = data.contentAttributes.senderName
        holder.txtVisitCount.text = data.contentAttributes.visitCnt
        holder.txtSTDate.text = data.contentAttributes.sdate

        holder.imagePoster.setOnClickListener {
            interaction?.onItemSelected(data.contentAttributes.uid)
        }
    }

    fun setStudentList(relatedContents: List<RelatedContent>) {
        relatedContent = relatedContents
        notifyDataSetChanged()
    }

    private fun showImage(context: Context, url : String, imageView: AppCompatImageView){

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)

        Glide.with(context)
            .setDefaultRequestOptions(requestOptions)
            .load(url)
            .error(R.drawable.ic_launcher_background) // Replace with your error placeholder
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d(TAG, "onLoadFailed: ")
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d(TAG, "onResourceReady: ")
                    return false
                }

            })
            .into(imageView)

    }
}

class MovieViewHolder
constructor(
    itemView: View,
) : RecyclerView.ViewHolder(itemView){

     val imagePoster: AppCompatImageView = itemView.findViewById(R.id.img_poster)
     val circleImage: CircleImageView = itemView.findViewById(R.id.img_circle)
     val txtTile: AppCompatTextView = itemView.findViewById(R.id.txt_title)
     val txtDuration: AppCompatTextView = itemView.findViewById(R.id.txt_duration)
     val txtSenderName: AppCompatTextView = itemView.findViewById(R.id.txt_sender_name)
     val txtVisitCount: AppCompatTextView = itemView.findViewById(R.id.txt_visit_count)
     val txtSTDate: AppCompatTextView = itemView.findViewById(R.id.txt_sdate)


}

interface Interaction {

    fun onItemSelected(item: String)

}