package controllers;


import java.util.ArrayList;
import java.util.List;

import models.Member;
import models.Movie;

public class MovieController {
	private List<Movie> movieStore;

    public MovieController(List<Movie> movieStore) {
        this.movieStore = movieStore;
    }
    // tìm kiếm phim
    public List<Movie> searchMovie(String keyword) {
        List<Movie> results = new ArrayList<>();
        if (keyword == null || keyword.isEmpty()) 
        	return new ArrayList<>(movieStore);
        String kw = keyword.toLowerCase();
        for (Movie m : movieStore) {
            if (m.getNameMovie().toLowerCase().contains(kw)
             || m.getDirector().toLowerCase().contains(kw)
             || m.getActor().toLowerCase().contains(kw)) {
                results.add(m);
            }
        }
        return results;
    }
    // kiểm tra chay phim
    public boolean playMovie(int movieId, Member user) {
    	for (Movie movie : movieStore) {
            if (movie.getId() == movieId) {
                if (movie.isVip() && !("VIP".equalsIgnoreCase(user.getAccountStatus()))) {
                    System.out.println("Mua gói Vip để xem " + movie.getNameMovie());
                    return false;
                }
                movie.watchMovie();
                return true;
            }
        }
        System.out.println("Movie not found: " + movieId);
        return false;
    }
}
