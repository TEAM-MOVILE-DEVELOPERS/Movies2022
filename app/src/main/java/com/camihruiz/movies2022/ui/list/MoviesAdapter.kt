package com.camihruiz.movies2022.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.camihruiz.movies2022.R
import com.camihruiz.movies2022.databinding.CardViewMovieBinding
import com.camihruiz.movies2022.server.model.Movie
import com.squareup.picasso.Picasso

class MoviesAdapter(
	private val moviesList: ArrayList<Movie>,
	// Unit significa que Movie puede ser de cualquier tipo
	private val onItemClicked:  (Movie) -> Unit

) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
	//  Los métodos onCreateViewHolder, onBindViewHolder, getItemCount siempre se usan
	override fun onCreateViewHolder(
		parent: ViewGroup ,
		viewType: Int
	): MovieViewHolder {
		val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view_movie,parent, false)
		return MovieViewHolder(itemView)
	}
	
	override fun onBindViewHolder(holder: MovieViewHolder , position: Int) {
		val movie = moviesList[position]
		holder.bindMovie(movie)
		holder.itemView.setOnClickListener{ onItemClicked(moviesList[position]) }
	}
	
	override fun getItemCount(): Int = moviesList.size

	fun appendItems(newList: ArrayList<Movie>){
		// Para pedirle al backend que envíe listas nuevas
		moviesList.clear()
		moviesList.addAll(newList)
		notifyDataSetChanged()
	}
	
	class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
		private val binding = CardViewMovieBinding.bind(itemView)
		fun bindMovie(movie: Movie){
			with(binding){
				tvMovieTitle.text = movie.title
				tvDateData.text = movie.releaseDate
				tvVotesNum.text  = movie.voteAverage.toString()
				Picasso.get().load("https://image.tmdb.org/t/p/w500"+movie.posterPath).into(posterImageView)
				
			}
		}
	}
	
	
}