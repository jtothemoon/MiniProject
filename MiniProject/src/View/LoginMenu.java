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
		String saveUserId, saveEmail;
		boolean loginFlag = true;
		
		System.out.println("□□□□■□□□□■□□□□□□□□□□□■■■■■■■■□■□□□□□□□□□□□□□■■■■■■□□■□□\r\n"
				+ "□□□□■□□□□■□□□□□□□□□□□□□□■■□□□□■□□□□□□□□□□□□■■■■■■■■□■□□\r\n"
				+ "□□□□■□□□□■□□□□□□□□□□□□□□■■□□□□■□□□□□□□□□□□□□□□□□□■■□■□□\r\n"
				+ "□□□□■□□□□■□□□□□□□□□□□□□□■■■□□□■■■□□□□□□□□□□□□□□□□■□□■□□\r\n"
				+ "□□□■■■□□□■■■■□□□□□□□□□□■■■■■□□■□□□□□□□□□□□□□□□■□■■□□■■■\r\n"
				+ "□□□■■■□□□■■■□□□□□□□□□■■■■□■■■□■□□□□□□□□□□□□□□□■□■■□□■■■\r\n"
				+ "□□■■■■■□□■□□□□□□□□□□□■■□□□□■■□■□□□□□□□□□□□□□□□■□■■□□■□□\r\n"
				+ "□■■■□■■■□■□□□□□□□□□□□□□□■■■■■■■□□□□□□□□□□□□□□□■□□□■■■□□\r\n"
				+ "■■■□□□■■□■□□□□□□□□□□□□□■■■■□■■■□□□□□□□□□□□□■■■■■■■■■■□□\r\n"
				+ "□□□□□□□□□■□□□□□□□□□□□□□■■□□□□□■■□□□□□□□□□□□□■□□□□□□□■□□\r\n"
				+ "□□□□□□□□□■□□□□□□□□□□□□□■■■□□□■■□□□□□□□□□□□□□□□□□□□□□■□□\r\n"
				+ "□□□□□□□□□■□□□□□□□□□□□□□□■■■■■■■□□□□□□□□□□□□□□□□□□□□□■□□");
		System.out.println("□■■■■■■■□■□□□□□□□□□□□□□□□□□□■■■■■■■■□□■□□□□□■□□□□□□■□□□□□□■\r\n"
				+ "□■■■■■■□□■□□□□□□□□□□□□□□□□□□□□□■□□□■□□■□□□□□■□□□□□□■□□□□□□■\r\n"
				+ "□□□■■□■■■■□□□□□□□□□□□□□□□□□□□□□■□□□■□□■□□□□□■□□□□□□■□□□□□□■\r\n"
				+ "□□□■■□■■■■□□□□□□□□□□□□□□□□□□□□■■■□□■■■■□□□□□■□□□□□□■□□□□□□■\r\n"
				+ "□□■■■■□□□■□□□□□□□□□□□□□□□□□□□■■■■■□■□□■□□□□□■□□□□□□■□□□□□□■\r\n"
				+ "□■■■■■■■□■□□□□□□□□□□□□□□□□□□■■■□■■■■□□■□□□□□■□□□□□□■□□□□□□■\r\n"
				+ "■■■□□■■■□■□□□□□□□□□□□□□□□□□□■■□□□■□■□□■□□□□□■□□□□□□■□□□□□□■\r\n"
				+ "□□■■□□□□□■□□□□□□□□□□□□□□□□□□□□□□■■■■■■□□□□□□■□□□□□□■□□□□□□■\r\n"
				+ "□□■■□□□□□□□□□□□□□□□□□□□□□□□□□□□■■■■■■■■□□□□□□□□□□□□□□□□□□□□\r\n"
				+ "□□■■□□□□□□□□□□□□□□□□□□□□□□□□□□□■■□□□□□■□□□□□□□□□□□□□□□□□□□□\r\n"
				+ "□□■■□□□□□□□□□□□□□□□□□□□□□□□□□□□■■■□□■■■□□□□□■□□□□□□■□□□□□□■\r\n"
				+ "□□■■■■■■■■■□□□□□□□□□□□□□□□□□□□□□■■■■■■■□□□□□□□□□□□□□□□□□□□□");
		
		while (loginFlag) {
			System.out.print("[1] 회원가입 [2] 로그인 [3] 비밀번호 변경 [4] 회원탈퇴 [5] 종료 : ");
			int sMenu = sc.nextInt();
			
			switch (sMenu) {
			case 1:
				while (true) {
					System.out.print("ID : ");
					userId = sc.next();
					
					saveUserId = userId;
					
					userId = dao.checkId(userId);
					if (userId != "") {
						System.out.println("ID가 중복되었습니다.");
					} else {
						userId = saveUserId;
						break;
					}
				}
				
				System.out.print("PW : ");
				pw = sc.next();
				System.out.print("NAME : ");
				name = sc.next();
				while (true) {
					System.out.print("EMAIL : ");
					email = sc.next();
					
					saveEmail = email;
					
					email = dao.checkEmail(email);
					if (email != "") {
						System.out.println("EMAIL이 중복되었습니다.");
					} else {
						email = saveEmail;
						break;
					}
				}
				
				dto = new UserDTO(userId, pw, name, email);
				int row = dao.userJoin(dto);
				if (row > 0) joinText();
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
				System.out.print("NAME : ");
				name = sc.next();
				System.out.print("EMAIL : ");
				email = sc.next();
				
				dto = new UserDTO("", "", name, email);
				pw = dao.userAuth(dto);
				if (pw.equals("")) {
					System.out.println("이름이나 EMAIL이 잘 못 되었습니다.");
				} else {
					System.out.print("CHANGE PW : ");
					pw = sc.next();
					System.out.print("CONFIRM PW : ");
					String confirmPw = sc.next();
					
					if (pw.equals(confirmPw)) {
						int pwRow = dao.updatePw(email, pw);
						if (pwRow > 0) {
							System.out.println("비밀번호가 변경 되었습니다.");
						}
					} else {
						System.out.println("비밀번호를 확인해주세요.");
					}
				}
				
				break;
			case 4:
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
					String yn = "";
					System.out.println("정말로 회원을 탈퇴 하시겠습니까? 게임 데이터도 같이 삭제됩니다(Y/N)");
					yn = sc.next();
					if (yn.toUpperCase().equals("Y")) {
						int deleteRow = dao.userDelete(dto);
						if (deleteRow > 0) {
							System.out.println("삭제되었습니다.");
						} 
					} else {
						break;
					}
				}
				break;
			case 5:
				loginFlag = false;
				return "";
			}
		}
		
		return "";
	}

	private void joinText() {
		System.out.println(" __| |___________________________________________________| |__\r\n"
				+ "(__   ___________________________________________________   __)\r\n"
				+ "   | |                                                  | |\r\n"
				+ "   | |               회원가입이 완료되었습니다.                 | |\r\n"
				+ "   | |                 _                                    \r\n"
				+ "    __      __   ___  | |   ___    ___    _ __ ___     ___ \r\n"
				+ "    \\ \\ /\\ / /  / _ \\ | |  / __|  / _ \\  | '_ ` _ \\   / _ \\\r\n"
				+ "     \\ V  V /  |  __/ | | | (__  | (_) | | | | | | | |  __/\r\n"
				+ "      \\_/\\_/    \\___| |_|  \\___|  \\___/  |_| |_| |_|  \\___ \r\n"
				+ "   | |                                                  | |\r\n"
				+ " __| | _________________________________________________| |__\r\n"
				+ "(__   ___________________________________________________   __)\r\n"
				+ "   | |                                                  | |");
	}
}