package com.example.mycapstonemade.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.core.domain.model.Sport
import com.example.mycapstonemade.R
import com.example.mycapstonemade.databinding.ActivityDetailSportBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailSportActivity : AppCompatActivity() {

    private val detailSportViewModel: DetailSportViewModel by viewModel()
    private lateinit var activityDetailSportBinding: ActivityDetailSportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailSportBinding = ActivityDetailSportBinding.inflate(layoutInflater)
        setContentView(activityDetailSportBinding.root)

        val detailSport = intent.getParcelableExtra<Sport>(EXTRA_DATA)
        showDetailSport(detailSport)
    }


    private fun showDetailSport(detailSport: Sport?) {
        detailSport?.let {
            supportActionBar?.title = detailSport.strSport
            activityDetailSportBinding.tvSportDescDetail.text = detailSport.strSportDescription
            activityDetailSportBinding.tvSportDetail.text = detailSport.strSport
            Glide.with( this@DetailSportActivity)
                .load(detailSport.strSportThumb)
                .into(activityDetailSportBinding.imgSportDetail)

            var statusFavorite = detailSport.isFavorite
            setIconFavorite(statusFavorite)
            activityDetailSportBinding.fab.setOnClickListener {
                if (!statusFavorite) {
                    statusFavorite = !statusFavorite
                    Toast.makeText(this, resources.getString(R.string.add_fav), Toast.LENGTH_SHORT).show()
                    detailSportViewModel.setFavoriteSport(detailSport, statusFavorite)
                    setIconFavorite(statusFavorite)
                } else {
                    statusFavorite = !statusFavorite
                    Toast.makeText(this, resources.getString(R.string.remove_fav), Toast.LENGTH_SHORT).show()
                    detailSportViewModel.setFavoriteSport(detailSport, statusFavorite)
                    setIconFavorite(statusFavorite)
                }
            }
        }
    }

    private fun setIconFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            activityDetailSportBinding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24))
        } else {
            activityDetailSportBinding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24))
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}