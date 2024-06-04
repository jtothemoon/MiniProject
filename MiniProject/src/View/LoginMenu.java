package View;

import java.util.Scanner;

import Controller.UserDAO;
import Model.UserDTO;

public class LoginMenu {
	
	public String selectLoginMenu() {
		Scanner sc = new Scanner(System.in);
		UserDTO dto = new UserDTO(null, null, null, null);
		UserDAO dao = new UserDAO();
		
		String userId, pw, name, email;
		boolean loginFlag = true;
		
		while (loginFlag) {
			System.out.print("[1] 회원가입 [2] 로그인 [3] 종료 : ");
			int sMenu = sc.nextInt();
			
			switch (sMenu) {
			case 1:
				System.out.print("ID : ");
				userId = sc.next();
				System.out.print("PW : ");
				pw = sc.next();
				System.out.print("NAME : ");
				name = sc.next();
				System.out.print("EMAIL : ");
				email = sc.next();
				
				dto = new UserDTO(userId, pw, name, email);
				int row = dao.userJoin(dto);
				if (row > 0) System.out.println("회원가입이 완료되었습니다.");
				break;
			case 2:
				System.out.print("ID : ");
				userId = sc.next();
				System.out.print("PW : ");
				pw = sc.next();
				
				dto = new UserDTO(userId, pw, "", "");
				userId = dao.userLogin(dto);
				
				if(userId.equals("")) {
					System.out.println("ID, PW가 잘 못 되었습니다.");
					break;
				} else {
					return userId;
				}
			case 3:
				loginFlag = false;
				return "";
			}
		}
		
		return "";
	}
}
