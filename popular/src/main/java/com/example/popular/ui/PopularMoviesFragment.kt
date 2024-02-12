package com.example.popular.ui

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.core_ui.Routes
import com.example.popular.databinding.FragmentPopularMoviesBinding
import com.example.popular.di.DaggerPopularScreenComponent
import com.example.popular.di.PopularScreenDependenciesProvider
import com.example.popular.ui.recycler.MoviesListAdapter
import com.example.popular.util.isInternetAvailable
import com.google.gson.Gson

class PopularMoviesFragment : Fragment() {
    private var _binding: FragmentPopularMoviesBinding? = null
    private val binding: FragmentPopularMoviesBinding
        get() = _binding ?: throw RuntimeException("error")

    private lateinit var listAdapter: MoviesListAdapter

    private val component = DaggerPopularScreenComponent
        .builder()
        .dependencies(PopularScreenDependenciesProvider.dependencies)
        .build()

    private val viewModel by lazy {
        component.getPopularMoviesViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        overrideOnBackPressed()
        checkNetworkConnection()
        setupRecyclerView()
        setupClickListeners()
        searchMovieAction()
    }

    private fun overrideOnBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.searchBlock.visibility == View.VISIBLE) {
                    binding.searchBlock.visibility = View.GONE
                    binding.tvTitle.visibility = View.VISIBLE
                    binding.buttonSearch.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun checkNetworkConnection() {
        if (isInternetAvailable(requireContext())) {
            binding.recyclerView.visibility = View.VISIBLE
            binding.bottomButtonsBlock.visibility = View.VISIBLE
            binding.failBlock.visibility = View.GONE
            observeViewModel()
        } else {
            binding.failBlock.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        }
    }

    private fun observeViewModel() {
        viewModel.popularMovies.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
        }
    }

    private fun setupClickListeners() {
        listAdapter.onItemLongClickListener = {
            if (!it.isFavorite) {
                viewModel.addMovieToFavoriteList(it)
            } else {
                viewModel.deleteMovieFromFavoriteList(it)
            }
            viewModel.updateList(it)
        }

        listAdapter.onItemClickListener = {
            // сейчас в андроиде не поддержана передача кастомных классов в
            // качестве аргументов через диплинки, поэтому передаю костыльно через строку
            // https://stackoverflow.com/questions/65280719/android-navigation-component-passing-argument-with-safeargs-and-deep-link
            if (isInternetAvailable(requireContext())) {
                val gson = Gson()
                val movieAsString = gson.toJson(it)
                val uri = Uri.parse("${Routes.MOVIE_DETAIL_SCREEN}?movie=$movieAsString")
                val navOptions = NavOptions.Builder()
                    .setEnterAnim(com.example.core_ui.R.anim.slide_in_right)
                    .setExitAnim(com.example.core_ui.R.anim.slide_out_left)
                    .setPopEnterAnim(com.example.core_ui.R.anim.slide_in_left)
                    .setPopExitAnim(com.example.core_ui.R.anim.slide_out_right)
                    .build()
                findNavController().navigate(uri, navOptions, null)
            } else {
                Toast.makeText(
                    requireContext(),
                    requireContext().getString(com.example.core_ui.R.string.network_warning),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.buttonNavigateToFavoriteScreen.setOnClickListener {
            val uri = Uri.parse(Routes.FAVORITES_SCREEN)
            val navOptions = NavOptions.Builder()
                .setEnterAnim(com.example.core_ui.R.anim.slide_in_right)
                .setExitAnim(com.example.core_ui.R.anim.slide_out_left)
                .setPopEnterAnim(com.example.core_ui.R.anim.slide_in_left)
                .setPopExitAnim(com.example.core_ui.R.anim.slide_out_right)
                .build()
            findNavController().navigate(uri, navOptions, null)
        }

        binding.buttonTryLoad.setOnClickListener {
            checkNetworkConnection()
        }

        binding.buttonSearch.setOnClickListener {
            if (isInternetAvailable(requireContext())) {
                binding.searchBlock.visibility = View.VISIBLE
                it.visibility = View.GONE
                binding.tvTitle.visibility = View.GONE
            }
        }

    }

    private fun searchMovieAction() {
        binding.editTextSearchNote.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                searchInList(query)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun searchInList(query: String) {
        viewModel.searchMovies(query)
        viewModel.searchedMovies.observe(this) {
            listAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        listAdapter = MoviesListAdapter()
        binding.recyclerView.adapter = listAdapter
    }
}