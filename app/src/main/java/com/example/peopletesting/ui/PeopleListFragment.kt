package com.example.peopletesting.ui

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.peopletesting.databinding.FragmentPeopleListBinding
import com.example.peopletesting.network.People
import com.example.peopletesting.network.PeopleApi
import com.example.peopletesting.network.PeopleApiService
import java.lang.StringBuilder

class PeopleListFragment : Fragment() {


    private val viewModel: PeopleViewModel = PeopleViewModel(PeopleApi.retrofitService)
    //lateinit var tempArray: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPeopleListBinding.inflate(inflater)
        viewModel.getPeopleDetails()

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = PeopleListAdapter()

        return binding.root
    }
}