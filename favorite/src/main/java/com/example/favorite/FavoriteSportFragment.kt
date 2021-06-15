package com.example.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.ui.SportAdapter
import com.example.favorite.databinding.FavoriteSportFragmentBinding
import com.example.favorite.di.favoriteModule
import com.example.mycapstonemade.detail.DetailSportActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class FavoriteSportFragment : Fragment() {

    private val favoriteSportViewModel: FavoriteSportViewModel by viewModel()
    private var _binding: FavoriteSportFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadKoinModules(favoriteModule)
        _binding = FavoriteSportFragmentBinding.inflate(inflater, container, false)
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
            favoriteSportViewModel.favoriteSport.observe(viewLifecycleOwner, {
                sportAdapter.setData(it)
                if (it.isNotEmpty())  {
                    binding.imgEmpty.visibility = View.GONE
                    binding.tvEmpty.visibility = View.GONE
                } else {
                    binding.imgEmpty.visibility = View.VISIBLE
                    binding.tvEmpty.visibility = View.VISIBLE                }
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
        unloadKoinModules(favoriteModule)
        _binding = null
    }
}