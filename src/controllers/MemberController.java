package controllers;

import java.util.List;
import models.Member;

public class MemberController {
    private List<Member> memberStore;

    public MemberController(List<Member> memberStore) {
        this.memberStore = memberStore;
    }

    // Cập nhật hồ sơ người dùng
    public String updateProfile(Member currentMember, String newName, String newEmail) {
        if (currentMember == null) return "Người dùng không tồn tại.";
        
        String trimmedName = newName != null ? newName.trim() : "";
        String trimmedEmail = newEmail != null ? newEmail.trim() : "";

        if (trimmedName.isEmpty() && trimmedEmail.isEmpty()) {
            return "Vui lòng nhập ít nhất một thông tin cần thay đổi.";
        }
        if (!trimmedEmail.isEmpty() && !trimmedEmail.contains("@")) {
            return "Email không hợp lệ.";
        }
        
        if (!trimmedEmail.isEmpty()) {
            currentMember.setEmail(trimmedEmail);
        }

        return "Cập nhật thông tin thành công!";
    }

    // Đổi mật khẩu người dùng
    public String changePassword(Member currentMember, String oldPass, String newPass, String confirm) {
        if (currentMember == null) return "Người dùng không tồn tại.";
        
        if (oldPass.isEmpty() || newPass.isEmpty() || confirm.isEmpty()) {
            return "Vui lòng điền đầy đủ tất cả các ô.";
        }
        if (!oldPass.equals(currentMember.getPassword())) {
            return "Mật khẩu hiện tại không đúng.";
        }
        if (!newPass.equals(confirm)) {
            return "Mật khẩu không khớp.";
        }
        if (newPass.equals(oldPass)) {
            return "Mật khẩu mới không được trùng mật khẩu cũ.";
        }

        currentMember.setPassword(newPass);
        return "Đổi mật khẩu thành công!";
    }

    // Xóa tài khoản người dùng
    public boolean deleteAccount(Member currentMember) {
        if (currentMember == null) return false;
        if (memberStore != null) {
            return memberStore.remove(currentMember);
        }
        return true;
    }
}

