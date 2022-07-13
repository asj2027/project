
public class Shop {
	Menu menu;
	Order order;
	
	Shop() {
		this.menu=new Menu();
		this.order=new Order();
		
		this.menu.build();
	}
}
