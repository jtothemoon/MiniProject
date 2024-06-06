package View;

import Controller.PlayDAO;
import javazoom.jl.player.MP3Player;

public class Main {

	public static void main(String[] args) {
		int happyEndingFlag = 0; //해피엔딩여부(0:해피엔딩, 1:게임오버, 2:종료)
		
		MP3Player mp3 = new MP3Player();
		
		while (true) {
			LoginMenu lMenu = new LoginMenu();
			if (!mp3.isPlaying()) mp3.play(".\\player\\bgm.mp3");
			
			String userId = lMenu.selectLoginMenu(mp3); //메인 메뉴 호출
			
			if(userId.equals("")) { //메인 메뉴에서 종료버튼을 눌렀을 경우 메소드 리턴
				mp3.stop();
				return;
			}
			
			PlayDAO dao = new PlayDAO();
			Intro gIntro = new Intro();
			PlayMenu pMenu = new PlayMenu();
			
			if (dao.checkPlayDays(userId) == 0) { //플레이 기록이 있는지 검사 0이면 인트로 호출
				gIntro.gameIntro(userId);
			}
			
			if (dao.getPlayInfo(userId) != null) { //인트로 호출 후 플레이데이터 적재되면 플레이 메뉴 호출 
				happyEndingFlag = pMenu.selectPlayMenu(userId, mp3);
			}
			
			if (happyEndingFlag == 1) {
				System.out.println();
				System.out.println();
				Outro r2 = new Outro();
				Thread t2 = new Thread(r2);
				mp3.stop();
				mp3.play(".\\player\\end.mp3");
				t2.run();
				mp3.stop();
			} else if(happyEndingFlag == 2) {
				GameOverAscii r1 = new GameOverAscii();
				Thread t1 = new Thread(r1);
				mp3.stop();
				mp3.play(".\\player\\gameover.mp3");
				t1.run();
				mp3.stop();
			} else {
				continue;
			}
			
		}
		
	}

}
