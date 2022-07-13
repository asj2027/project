package com.human.ajax;

import java.util.ArrayList;

public interface iMenu {
	ArrayList<menuDTO> listMenu();
	int addMenu(String name, int price);
	int delMenu(int seqno);
	menuDTO viewMenu(int seqno);
	int upMenu(String name, int price, int seqno);
}
