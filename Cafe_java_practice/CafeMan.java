
import java.util.Scanner;

public class CafeMan {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Menu menu = new Menu();
		Order order = new Order();
		Sale sales=new Sale();
		
		try {
			menu.build();
			System.out.println("�۾��� �����ϼ���. [o:�ֹ��ޱ�, s:���⺸��, m:�޴�����, x:���α׷� ����]");
			String str=s.nextLine();
			while(!str.equals("x")) {
				if(str.equals("s")) {
					sales.display();
				} else if(str.equals("o")) {
					menu.display();
					System.out.println("�޴���ȣ�� �����ϼ���.");
					str=s.nextLine();
					while(!str.equals("")) {
						int ndx=Integer.parseInt(str)-1;
						System.out.println("������ �Է��ϼ���.");
						str=s.nextLine();
						int net=menu.getArPrice(ndx);
						order.addOrder(menu.getArName(ndx),
									   Integer.parseInt(str),
									   net*Integer.parseInt(str));
						menu.display();
						System.out.println("�޴���ȣ�� �����ϼ���.");
						str=s.nextLine();
					}
					int sum=order.display();
					System.out.println("����Ϲ�ȣ�� �Է��ϼ���.");
					String mobile=s.nextLine();
					sales.setArMobile(mobile);
					sales.setArSum(sum);
					order.clear();
				} else if(str.equals("m")) {
					menu.display();
					System.out.println("c:�űԸ޴� �߰�, u:�޴�����, d:�޴�����, x:�޴����� ����");
					str=s.nextLine();
					while(true) {
						if(str.equals("c")) {
							System.out.println("[�űԸ޴� �߰� ����]");
							menu.Cadd();
							menu.display();
							break;
						} else if(str.equals("u")) {
							System.out.println("[�޴� ���� ����]");
							menu.Cset();
							menu.display();
							break;
						} else if(str.equals("d")) {
							System.out.println("[�޴� ���� ����]");
							menu.Crmv();
							menu.display();
							break;
						} else if(str.equals("x")) {
							System.out.println("[�޴����� ����]");
							break;
						}
					}
				}
				System.out.println("�۾��� �����ϼ���. [o:�ֹ��ޱ�,s:���⺸��, m:�޴�����, x:���α׷� ����]");
				str=s.nextLine();

			}
		} catch(Exception e) {
			System.out.println("����ó�� �� ����");
			e.printStackTrace();
		}
	}
}
