import java.sql.Date;

public class Payment {
//	 thuoc tinh payment
	private int id;
	private Date paymentDate;
	private int price;
	private String service;
//	constructor payment
	public Payment(int id, Date paymentDate, int price, String service) {
		super();
		this.id = id;
		this.paymentDate = paymentDate;
		this.price = price;
		this.service = service;
	}
//	getters setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	
}
