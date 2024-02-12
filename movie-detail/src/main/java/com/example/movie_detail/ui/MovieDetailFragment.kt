package com.example.movie_detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import com.example.core_data.model.MovieModel
import com.example.movie_detail.databinding.FragmentMovieDetilBinding
import com.example.movie_detail.di.DaggerDetailScreenComponent
import com.example.movie_detail.di.DetailScreenDependenciesProvider
import com.google.gson.Gson

class MovieDetailFragment : Fragment() {
    private var _binding: FragmentMovieDetilBinding? = null
    private val binding: FragmentMovieDetilBinding
        get() = _binding ?: throw RuntimeException("error")

    private val component = DaggerDetailScreenComponent
        .builder()
        .dependencies(DetailScreenDependenciesProvider.dependencies)
        .build()

    private val viewModel by lazy {
        component.getMovieDetailViewModel()
    }

    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadMovieInfo()
        showMovieDetailInfo()
    }

    private fun loadMovieInfo() {
        startLoadingAnimation()
        val gson = Gson()
        val data = gson.fromJson(
            args.movie,
            MovieModel::class.java
        )
        viewModel.getMovie(data.id)
    }

    private fun startLoadingAnimation() {
        binding.loadingAnim.visibility = View.VISIBLE
        binding.loadingAnim.repeatCount = LottieDrawable.INFINITE
        binding.loadingAnim.playAnimation()
    }

    private fun stopPlayingAnimation() {
        binding.loadingAnim.pauseAnimation()
        binding.loadingAnim.visibility = View.GONE
    }

    private fun showMovieDetailInfo() {
        viewModel.movieInfo.observe(viewLifecycleOwner) {
            binding.imageRoundedCard.visibility = View.VISIBLE
            binding.textBlock.visibility = View.VISIBLE
            binding.tvMovieTitle.text = it.title
            binding.tvMovieDescription.text = it.description
            binding.tvCountry.text = it.country
            binding.tvGenre.text = it.genre
            stopPlayingAnimation()

            Glide.with(this)
                .load(it.imagePoster)
                .into(binding.imageMoviePoster)
        }
    }


}