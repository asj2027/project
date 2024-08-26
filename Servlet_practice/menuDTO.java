
public class menuDTO {
	private int seqno;
	private String name;
	private int price;

	public menuDTO() {
	}

	public menuDTO(int seqno,String name,int price) {
		this.seqno=seqno;
		this.name=name;
		this.price=price;
		
	}

	public int getSeqno() {
		return seqno;
	}

	public void setSeqno(int seqno) {
		this.seqno = seqno;
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
	};
	
}	
