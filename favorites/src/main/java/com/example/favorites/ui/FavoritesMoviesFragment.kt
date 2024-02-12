package com.example.favorites.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.favorites.databinding.FragmentFavoritesMoviesBinding
import com.example.favorites.di.DaggerFavoriteScreenComponent
import com.example.favorites.di.FavoriteScreenDependenciesProvider
import com.example.favorites.ui.recycler.FavoriteMoviesListAdapter

class FavoritesMoviesFragment : Fragment() {
    private var _binding: FragmentFavoritesMoviesBinding? = null
    private val binding: FragmentFavoritesMoviesBinding
        get() = _binding ?: throw RuntimeException("error")

    private lateinit var listAdapter: FavoriteMoviesListAdapter

    private val component = DaggerFavoriteScreenComponent
        .builder()
        .dependencies(FavoriteScreenDependenciesProvider.dependencies)
        .build()

    private val viewModel by lazy {
        component.getFavoriteMoviesViewModel()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupClickListeners()
        searchMovieAction()
        observeViewModel()
    }

    private fun setupClickListeners() {
        binding.buttonNavigateToPopularScreen.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonSearch.setOnClickListener {
            binding.searchBlock.visibility = View.VISIBLE
            it.visibility = View.GONE
            binding.title.visibility = View.GONE
        }
    }

    private fun observeViewModel() {
        viewModel.favoriteMovies.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.tvWarning.visibility = View.VISIBLE
            } else {
                binding.tvWarning.visibility = View.INVISIBLE
            }
            listAdapter.submitList(it)
        }
    }

    private fun searchMovieAction() {
        binding.editTextSearchNote.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                searchInDb(query)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun searchInDb(query: String) {
        viewModel.searchMovies(query)
        viewModel.searchedQueryList.observe(this) {
            listAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        listAdapter = FavoriteMoviesListAdapter()
        binding.recyclerView.adapter = listAdapter
    }
}