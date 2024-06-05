package View;

import Controller.PlayDAO;
import javazoom.jl.player.MP3Player;

public class Main {

	public static void main(String[] args) {
		int happyEndingFlag = 0;
		
		MP3Player mp3 = new MP3Player();
		
		while (true) {
			LoginMenu lMenu = new LoginMenu();
			mp3.play(".\\player\\bgm.mp3");
			String userId = lMenu.selectLoginMenu(mp3);
			
			if(userId.equals("")) {
				System.out.println();
				System.out.println();
				Outro r2 = new Outro();
				Thread t2 = new Thread(r2);
				mp3.stop();
				mp3.play(".\\player\\end.mp3");
				t2.run();
				mp3.stop();
				return;
			}
			
			PlayDAO dao = new PlayDAO();
			Intro gIntro = new Intro();
			PlayMenu pMenu = new PlayMenu();
			
			if (dao.checkPlayDays(userId) == 0) {
				gIntro.gameIntro(userId);
			}
			
			if (dao.getPlayInfo(userId) != null) {
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
