package com.example.mycapstonemade.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.Resource
import com.example.core.ui.SportAdapter
import com.example.mycapstonemade.R
import com.example.mycapstonemade.databinding.HomeFragmentBinding
import com.example.mycapstonemade.detail.DetailSportActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val sportAdapter = SportAdapter()
            sportAdapter.onClick = {
                val intent = Intent(activity, DetailSportActivity::class.java)
                intent.putExtra(DetailSportActivity.EXTRA_DATA, it)
                startActivity(intent)
            }

            homeViewModel.sport.observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> binding.pbHome.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.pbHome.visibility = View.GONE
                            sportAdapter.setData(it.data)
                        }
                        is Resource.Error -> {
                            binding.apply {
                                pbHome.visibility = View.GONE
                                itemError.root.visibility = View.VISIBLE
                                itemError.tvError.text = it.message ?: getString(R.string.oops_something_went_wrong)
                            }
                        }
                    }
                }
            })

            with(binding.rvSport) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = sportAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}