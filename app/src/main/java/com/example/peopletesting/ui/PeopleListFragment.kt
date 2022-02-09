package com.example.peopletesting.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.peopletesting.databinding.FragmentPeopleListBinding
import com.example.peopletesting.network.PeopleApi

class PeopleListFragment : Fragment() {

    private val viewModel: PeopleViewModel = PeopleViewModel(PeopleApi.retrofitService)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPeopleListBinding.inflate(inflater)
        viewModel.getPeopleDetails()

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = PeopleListAdapter()

        return binding.root
    }
}