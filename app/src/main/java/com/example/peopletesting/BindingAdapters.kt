package com.example.peopletesting

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.peopletesting.network.People
import com.example.peopletesting.ui.PeopleListAdapter

/**
 * Use Glide
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView)
            .load(imgUri)
            .centerCrop()
            .into(imgView)
    }
}


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<People>?){
    val adapter = recyclerView.adapter as PeopleListAdapter
    adapter.submitList(data)
}