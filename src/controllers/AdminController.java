package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Member;
import models.Movie;

/**
 * Controller xử lý các nghiệp vụ của Admin: quản lý phim và quản lý
 * tài khoản người dùng (Member). Đóng vai trò trung gian giữa AdminView
 * và dữ liệu (movieStore / memberStore), giống cách MovieController
 * đang làm với danh sách phim.
 */
public class AdminController {

    private List<Movie>  movieStore;
    private List<Member> memberStore;

    public AdminController(List<Movie> movieStore, List<Member> memberStore) {
        this.movieStore  = movieStore;
        this.memberStore = memberStore;
    }

    // ════════════════════════════════════════════
    //  QUẢN LÝ PHIM
    // ════════════════════════════════════════════

    /** Trả về toàn bộ danh sách phim hiện có. */
    public List<Movie> getAllMovie() {
        return new ArrayList<>(movieStore);
    }

    /** Thêm phim mới vào hệ thống. */
    public void addMovie(Movie movie) {
        if (movie == null) return;
        boolean duplicateId = movieStore.stream()
                .anyMatch(m -> m.getId() == movie.getId());
        if (duplicateId) {
            // tránh trùng ID, tự cấp ID mới nếu cần
            movie.setId(nextMovieId());
        }
        movieStore.add(movie);
        System.out.println("[AdminController] Đã thêm phim: " + movie.getNameMovie());
    }

    /**
     * Xóa phim theo vị trí (row) trong danh sách — tương ứng với dòng
     * đang được chọn trên JTable ở AdminView, vì bảng phim được nạp
     * theo đúng thứ tự của movieStore.
     */
    public void deleteMovie(int row) {
        if (row < 0 || row >= movieStore.size()) {
            System.out.println("[AdminController] Vị trí phim không hợp lệ: " + row);
            return;
        }
        Movie removed = movieStore.remove(row);
        System.out.println("[AdminController] Đã xóa phim: " + removed.getNameMovie());
    }

    /** Cập nhật thông tin phim (không có trong sơ đồ gốc nhưng cần cho onUpdateMovie). */
    public void updateMovie(Movie movie) {
        if (movie == null) return;
        for (int i = 0; i < movieStore.size(); i++) {
            if (movieStore.get(i).getId() == movie.getId()) {
                movieStore.set(i, movie);
                System.out.println("[AdminController] Đã cập nhật phim: " + movie.getNameMovie());
                return;
            }
        }
    }

    private int nextMovieId() {
        return movieStore.stream().mapToInt(Movie::getId).max().orElse(0) + 1;
    }

    // ════════════════════════════════════════════
    //  QUẢN LÝ NGƯỜI DÙNG
    // ════════════════════════════════════════════

    /** Trả về toàn bộ danh sách thành viên. */
    public List<Member> getAllMember() {
        return new ArrayList<>(memberStore);
    }

    /** Khóa tài khoản người dùng. */
    public void lockUser(Member user) {
        if (user == null) return;
        user.setAccountStatus("LOCKED");
        System.out.println("[AdminController] Đã khóa tài khoản: " + user.getEmail());
    }

    /**
     * Mở khóa tài khoản người dùng. Nếu VIP của họ vẫn còn hạn thì
     * trả về trạng thái VIP, ngược lại trả về tài khoản thường.
     */
    public void unlockUser(Member user) {
        if (user == null) return;
        Date expiredVIP = user.getExpiredVIP();
        boolean stillVip = expiredVIP != null && expiredVIP.after(new Date());
        user.setAccountStatus(stillVip ? "VIP" : "Regular");
        System.out.println("[AdminController] Đã mở khóa tài khoản: " + user.getEmail());
    }

    /** Gửi cảnh báo tới người dùng kèm theo lý do. */
    public void warnUser(Member user, String reason) {
        if (user == null) return;
        String msg = "[CẢNH BÁO] " + user.getEmail() + ": " + reason;
        System.out.println("[AdminController] " + msg);
        if (user instanceof models.Observer) {
            ((models.Observer) user).update(msg);
        }
    }
}