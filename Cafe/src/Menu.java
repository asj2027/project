import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	private ArrayList<String>arName;
	private ArrayList<Integer>arPrice;
	Menu() {
		this.arName=new ArrayList<String>();
		this.arPrice=new ArrayList<Integer>();
	}
	void display() {
		for(int i=0;i<this.arName.size();i++) {
			System.out.println((i+1)+". "+this.getArName(i)+": "+this.getArPrice(i)+"원");
		}
	}
	public String getArName(int n) {
		return this.arName.get(n);
	}
	public void setArName(String str) {
		this.arName.add(str);
	}
	public int getArPrice(int n) {
		return this.arPrice.get(n);
	}
	public void setArPrice(int price) {
		this.arPrice.add(price);
	}
	void build() {
		try {
		File f=new File("d:\\tmp\\menuu.txt");
		FileReader fr=new FileReader(f);
		BufferedReader br=new BufferedReader(fr);
		String line=br.readLine();
		while(line!=null) {
			String[] ar=line.split(",");
			this.setArName(ar[0]);
			this.setArPrice(Integer.parseInt(ar[1]));
			line=br.readLine();
		}
		br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {	
		}
	}
	
	public void Cadd() {
		Scanner s = new Scanner(System.in);
		System.out.println("추가할 메뉴명을 입력하세요.");
		String str=s.nextLine();
		this.setArName(str);
		System.out.println("추가할 가격을 입력하세요.");
		int price=Integer.parseInt(s.nextLine());
		this.setArPrice(price);
	}
	
	public void Cset() {
		Scanner s = new Scanner(System.in);
		System.out.println("변경할 메뉴번호를 입력하세요.");
		String str=s.nextLine();
		int ndx=Integer.parseInt(str)-1;
		System.out.println("변경할 메뉴명을 입력하세요.");
		String na=s.nextLine();
		this.arName.set(ndx,na);
		System.out.println("변경할 가격을 입력하세요.");
		int pr=Integer.parseInt(s.nextLine());
		this.arPrice.set(ndx,pr);
	}
	
	public void Crmv() {
		Scanner s = new Scanner(System.in);
		System.out.println("삭제할 메뉴번호를 입력하세요.");
		String str=s.nextLine();
		int ndx=Integer.parseInt(str)-1;
		this.arName.remove(ndx);
		this.arPrice.remove(ndx);
	}
	
}
