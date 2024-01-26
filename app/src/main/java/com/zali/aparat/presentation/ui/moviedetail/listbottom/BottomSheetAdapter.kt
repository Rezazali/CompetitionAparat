package com.zali.aparat.presentation.ui.moviedetail.listbottom

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.zali.aparat.R
import com.zali.aparat.domain.models.FileLinkAll


class BottomSheetAdapter(private val interaction: Interaction? = null)
    : RecyclerView.Adapter<BottomSheetViewHolder>() {

    private  val TAG = "BottomSheetAdapter"

    private var listName: List<FileLinkAll> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bottom_sheet, parent, false)
        return BottomSheetViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (listName.isEmpty()){
            0
        }else{
            listName.size
        }
    }

    override fun onBindViewHolder(holder: BottomSheetViewHolder, position: Int) {
        val data = listName[position]
        holder.txtName.text = data.text

        holder.txtName.setOnClickListener {
            interaction?.onItemSelected(data.urls[0])
            Log.d(TAG, "Link Downloadddddddddddd: ".plus(data.urls[0]))
        }
    }

    fun setDataBottomSheet(listNames: List<FileLinkAll>){
        listName = listNames
        notifyDataSetChanged()
    }

}

class BottomSheetViewHolder(itemView: View)
    :RecyclerView.ViewHolder(itemView){
    val txtName: AppCompatTextView = itemView.findViewById(R.id.txt_download_bottom_sheet)
}

interface Interaction {

    fun onItemSelected(link: String)

}