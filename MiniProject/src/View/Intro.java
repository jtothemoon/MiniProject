package View;

import java.util.Scanner;

import Controller.PlayDAO;
import Model.PlayDTO;

public class Intro {
	public void gameIntro(String userId) {
		System.out.println("===== 인트로 =====");
		makeNickName(userId);
	}
	
	private void makeNickName(String userId) {
		Scanner sc = new Scanner(System.in);
		
		int row = 0;
		
		System.out.print("닉네임을 입력하세요 : ");
		String nickName = sc.next();
		
		PlayDTO dto = new PlayDTO(userId, nickName, 0, 0, 1000, 0, 0, 0, 0, 1);
		PlayDAO dao = new PlayDAO();
		
		row = dao.insertPlayInfo(dto);
		if(row > 0) System.out.println(nickName + "님 어서오세요.");
	}
}
