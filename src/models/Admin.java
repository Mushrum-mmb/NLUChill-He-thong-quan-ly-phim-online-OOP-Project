package models;

public class Admin extends User{
//	constructor admin
	public Admin(int id, String email, String password) {
		super(id, email, password);
		// TODO Auto-generated constructor stub
	}
//	phuong thuc khac
	public void addMovie(Movie movie) {};
	public void deleteMovie(Movie movie) {};
	public void lockUser(User user) {};
	public void unlockUser(User user) {};
	public void warnUser(Member user, String reason) {};
	public void manage() {};
}
