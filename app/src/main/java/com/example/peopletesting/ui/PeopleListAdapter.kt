package com.example.peopletesting.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.peopletesting.databinding.ListViewItemBinding
import com.example.peopletesting.network.People

class PeopleListAdapter :
    ListAdapter<People, PeopleListAdapter.PeopleViewHolder>(DiffCallback) {

    class PeopleViewHolder(
        private var binding: ListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(people: People) {
            binding.people = people
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<People>() {

        override fun areItemsTheSame(oldItem: People, newItem: People): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: People, newItem: People): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.avatar == newItem.avatar
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PeopleViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val people = getItem(position)
        holder.bind(people)
    }
}
