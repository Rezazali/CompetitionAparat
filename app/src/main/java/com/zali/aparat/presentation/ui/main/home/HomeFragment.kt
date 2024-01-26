package com.zali.aparat.presentation.ui.main.home

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zali.aparat.R
import com.zali.aparat.databinding.FragmentHomeBinding
import com.zali.aparat.domain.models.ContentAttributes
import com.zali.aparat.presentation.ui.main.movielist.Interaction
import com.zali.aparat.presentation.ui.main.movielist.MovieListAdapter
import androidx.navigation.fragment.findNavController


class HomeFragment : Fragment(), Interaction {

    private val TAG = "HomeFragment"

    private lateinit var owner: LifecycleOwner
    private lateinit var homeViewModel: HomeViewModel

    private lateinit var binding : FragmentHomeBinding
    private lateinit var adapter : MovieListAdapter


    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = this
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        adapter = MovieListAdapter(this)
        val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = layoutManager


        homeViewModel.getAllVideo().observe(owner){t ->
            adapter.setStudentList(t.relatedContent)
        }

        return binding.root
    }


    private fun sendData(string: String) {
        val bundle = Bundle().apply {
            putString("data", string)
        }
        findNavController().navigate(R.id.action_homeFragment_to_movieDetailFragment, bundle)
    }


    override fun onItemSelected(item: String) {
        if(item != null){
            sendData(item)
        }
    }

}