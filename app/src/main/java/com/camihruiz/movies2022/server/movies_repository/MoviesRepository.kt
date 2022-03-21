package com.camihruiz.movies2022.server.movies_repository

import com.camihruiz.movies2022.server.MovieDB

class MoviesRepository {
	private val apiKey = "90fb7b708ba0c5da69c23d5afe4da734"
	
	suspend fun getMovies() = MovieDB.retrofit.getTopRated(apiKey)
}