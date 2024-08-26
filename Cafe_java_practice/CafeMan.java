
import java.util.Scanner;

public class CafeMan {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Menu menu = new Menu();
		Order order = new Order();
		Sale sales=new Sale();
		
		try {
			menu.build();
			System.out.println("작업을 선택하세요. [o:주문받기, s:매출보기, m:메뉴관리, x:프로그램 종료]");
			String str=s.nextLine();
			while(!str.equals("x")) {
				if(str.equals("s")) {
					sales.display();
				} else if(str.equals("o")) {
					menu.display();
					System.out.println("메뉴번호를 선택하세요.");
					str=s.nextLine();
					while(!str.equals("")) {
						int ndx=Integer.parseInt(str)-1;
						System.out.println("수량을 입력하세요.");
						str=s.nextLine();
						int net=menu.getArPrice(ndx);
						order.addOrder(menu.getArName(ndx),
									   Integer.parseInt(str),
									   net*Integer.parseInt(str));
						menu.display();
						System.out.println("메뉴번호를 선택하세요.");
						str=s.nextLine();
					}
					int sum=order.display();
					System.out.println("모바일번호를 입력하세요.");
					String mobile=s.nextLine();
					sales.setArMobile(mobile);
					sales.setArSum(sum);
					order.clear();
				} else if(str.equals("m")) {
					menu.display();
					System.out.println("c:신규메뉴 추가, u:메뉴수정, d:메뉴삭제, x:메뉴관리 종료");
					str=s.nextLine();
					while(true) {
						if(str.equals("c")) {
							System.out.println("[신규메뉴 추가 선택]");
							menu.Cadd();
							menu.display();
							break;
						} else if(str.equals("u")) {
							System.out.println("[메뉴 수정 선택]");
							menu.Cset();
							menu.display();
							break;
						} else if(str.equals("d")) {
							System.out.println("[메뉴 삭제 선택]");
							menu.Crmv();
							menu.display();
							break;
						} else if(str.equals("x")) {
							System.out.println("[메뉴관리 종료]");
							break;
						}
					}
				}
				System.out.println("작업을 선택하세요. [o:주문받기,s:매출보기, m:메뉴관리, x:프로그램 종료]");
				str=s.nextLine();

			}
		} catch(Exception e) {
			System.out.println("예외처리 후 종료");
			e.printStackTrace();
		}
	}
}
