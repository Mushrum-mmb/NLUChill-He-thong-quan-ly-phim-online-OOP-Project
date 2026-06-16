import java.sql.Date;

public class Member extends User{
//	thuoc tinh member
	private String AccountStatus;
	private Date expiredVIP;
	//	constructor member
	public Member(int id, String email, String password, String accountStatus, Date expiredVIP) {
		super(id, email, password);
		AccountStatus = accountStatus;
		this.expiredVIP = expiredVIP;
	}
//	getters setters
	public String getAccountStatus() {
		return AccountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		AccountStatus = accountStatus;
	}
	public Date getExpiredVIP() {
		return expiredVIP;
	}
	public void setExpiredVIP(Date expiredVIP) {
		this.expiredVIP = expiredVIP;
	}
//	phuong thuc khac
	public void upgradeVIP() {};
}
