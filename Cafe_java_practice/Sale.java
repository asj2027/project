import java.util.ArrayList;

public class Sale {
	ArrayList<String> arMobile;
	ArrayList<Integer> arSum;
	
	Sale() {
		arMobile = new ArrayList<String>();
		arSum = new ArrayList<Integer>();
	}
	public String getArMobile(int str) {
		return this.arMobile.get(str);
	}
	public void setArMobile(String str) {
		this.arMobile.add(str);
	}
	public int getArSum(int sum) {
		return this.arSum.get(sum);
	}
	public void setArSum(int sum) {
		this.arSum.add(sum);
	}
	void display() {
		for(int i=0;i<arMobile.size();i++) {
			System.out.println("È¸¿ø "+this.getArMobile(i)+": "+getArSum(i)+"¿ø");
		}
	}
}
