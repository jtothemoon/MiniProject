package View;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.RankDAO;
import Controller.UserDAO;
import Model.RankDTO;
import Model.UserDTO;
import javazoom.jl.player.MP3Player;

public class LoginMenu {

	public String selectLoginMenu(MP3Player mp3) {
		Scanner sc = new Scanner(System.in);

		UserDTO dto = new UserDTO(null, null, null, null);
		UserDAO dao = new UserDAO();
		LoginAscii lAscii = new LoginAscii();

		String userId, pw, name, email;
		String saveUserId, saveEmail;
		boolean loginFlag = true;

		String txt = "끄기";

		lAscii.titleText();

		while (loginFlag) {
			System.out.print("[1] 회원가입 [2] 로그인 [3] 비밀번호 변경 [4] 회원탈퇴 [5] 랭킹 [6] BGM" + txt + " [7] 종료 : ");
			int sMenu;

			sMenu = checkInputValid(sc);

			switch (sMenu) {
			case 1: //회원가입
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
				if (row > 0)
					lAscii.joinText();
				break;
			case 2: //로그인
				System.out.print("ID : ");
				userId = sc.next();
				System.out.print("PW : ");
				pw = sc.next();

				dto = new UserDTO(userId, pw, "", "");
				userId = dao.userLogin(dto);

				if (userId.equals("")) {
					lAscii.idPwWrong();
					break;
				} else {
					return userId;
				}
			case 3: //비밀번호 변경
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
							lAscii.changePw();
						}
					} else {
						System.out.println("비밀번호를 확인해주세요.");
					}
				}

				break;
			case 4: //로그인
				System.out.print("ID : ");
				userId = sc.next();
				System.out.print("PW : ");
				pw = sc.next();

				dto = new UserDTO(userId, pw, "", "");
				userId = dao.userLogin(dto);

				if (userId.equals("")) {
					System.out.println("ID, PW가 잘 못 되었습니다.");
					break;
				} else {
					String yn = "";
					System.out.println("정말로 회원을 탈퇴 하시겠습니까? 게임 데이터도 같이 삭제됩니다(Y/N)");
					yn = sc.next();
					if (yn.toUpperCase().equals("Y")) {
						int deleteRow = dao.userDelete(dto);
						if (deleteRow > 0) {
							lAscii.deletePlayer();
							System.out.println("삭제되었습니다.");
						}
					} else {
						break;
					}
				}
				break;
			case 5: //랭킹
				RankDAO rDao = new RankDAO();
				ArrayList<RankDTO> list = rDao.selectRankAll();
				System.out.println("-----------------------------------------");
				System.out.println("|순위\t|ID\t|닉네임\t|클리어\t|돈\t|");
				System.out.println("-----------------------------------------");
				for (RankDTO rankDTO : list) {
					System.out.println("|" + rankDTO.getRank() + "\t|" + rankDTO.getId() + "\t|" + rankDTO.getNickName() + "\t|"
							+ rankDTO.getPlayDays() + "일\t|" + rankDTO.getMoney() + "\t|");
				}
				System.out.println("-----------------------------------------");
				break;
			case 6: //BGM 켜기/끄기
				if (mp3.isPlaying()) {
					txt = "켜기";
					mp3.stop();
				} else {
					txt = "끄기";
					mp3.play(".\\player\\bgm.mp3");
				}
				break;
			case 7: //종료
				loginFlag = false;
				return "";
			}
		}

		return "";
	}

	private int checkInputValid(Scanner sc) {
		int sMenu;

		while (!sc.hasNextInt()) {
			sc.next();
			System.err.print("에러! 숫자가 아닙니다. \n재 선택 : ");
		}

		sMenu = sc.nextInt();
		return sMenu;
	}

}