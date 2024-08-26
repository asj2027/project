
public class yeDTO {
	private int seq;
	private String name;
	private int price;
	private int num;
	private int type;
	private int tnum;
	private String checkin;
	private String checkout;
	private String typename;
	private String tname;
	private int order_no;
	
	public yeDTO() {
	}
	
	

	public yeDTO(int seq, String name, int price, int num, int type, int tnum, String checkin, String checkout,
			String typename) {
		this.seq = seq;
		this.name = name;
		this.price = price;
		this.num = num;
		this.type = type;
		this.tnum = tnum;
		this.checkin = checkin;
		this.checkout = checkout;
		this.typename = typename;
	}



	public int getSeq() {
		return seq;
	}



	public void setSeq(int seq) {
		this.seq = seq;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public int getNum() {
		return num;
	}



	public void setNum(int num) {
		this.num = num;
	}



	public int getType() {
		return type;
	}



	public void setType(int type) {
		this.type = type;
	}



	public int getTnum() {
		return tnum;
	}



	public void setTnum(int tnum) {
		this.tnum = tnum;
	}



	public String getCheckin() {
		return checkin;
	}



	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}



	public String getCheckout() {
		return checkout;
	}



	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}



	public String getTypename() {
		return typename;
	}



	public void setTypename(String typename) {
		this.typename = typename;
	}



	public String getTname() {
		return tname;
	}



	public void setTname(String tname) {
		this.tname = tname;
	}



	public int getOrder_no() {
		return order_no;
	}



	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	
	
}