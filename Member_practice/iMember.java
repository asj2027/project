package com.human.myapp;

import java.util.ArrayList;

public interface iMember {
	ArrayList<memberDTO> listMember();
	void insert(String id, String pw);
	memberDTO viewMember(String id);
	memberDTO signMember(String id,String pw);
}
