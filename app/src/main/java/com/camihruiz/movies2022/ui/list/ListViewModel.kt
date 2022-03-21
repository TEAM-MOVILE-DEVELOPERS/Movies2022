package com.camihruiz.movies2022.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.camihruiz.movies2022.server.model.Movie
import com.camihruiz.movies2022.server.model.MoviesList
import com.camihruiz.movies2022.server.movies_repository.MoviesRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
	
	private val moviesRepository = MoviesRepository()
	// El repositorio deberá hacer la conversión de lo que
	private val loadMovies : MutableLiveData<ArrayList<Movie>> = MutableLiveData()
	val loadMoviesDone : LiveData<ArrayList<Movie>> = loadMovies
	
	fun getMovies() {
		GlobalScope.launch(Dispatchers.IO){
			val moviesList : MoviesList = moviesRepository.getMovies()
			loadMovies.postValue(moviesList.moviesList as ArrayList<Movie>)
		}
	}
}