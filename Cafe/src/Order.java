import java.util.ArrayList;

public class Order {
	ArrayList<String> arMenu;
	ArrayList<Integer> arQty;
	ArrayList<Integer> arTotal;
	
	Order() {
		this.arMenu = new ArrayList<String>();
		this.arQty = new ArrayList<Integer>();
		this.arTotal = new ArrayList<Integer>();
	}
	void clear() {
		this.arMenu.clear();
		this.arQty.clear();
		this.arTotal.clear();
	}
	void addOrder(String str,int x, int i) {
		this.arMenu.add(str);
		this.arQty.add(x);
		this.arTotal.add(i);
	}
	public String getArMenu(int str) {
		return this.arMenu.get(str);
	}
	public void setArMenu(String str) {
		this.arMenu.add(str);
	}
	public int getArQty(int str) {
		return this.arQty.get(str);
	}
	public void setArQty(int str) {
		this.arQty.add(str);
	}
	public int getArTotal(int str) {
		return this.arTotal.get(str);
	}
	public void setArTotal(int str) {
		this.arTotal.add(str);
	}
	int display() {
		int nTotal=0;
		for(int i=0; i<this.arMenu.size(); i++) {
			System.out.println(this.getArMenu(i)+", x"+this.getArQty(i)+", "+this.getArTotal(i)+"¿ø");
			nTotal=nTotal+this.getArTotal(i);
		}
		return nTotal;
	}
}
