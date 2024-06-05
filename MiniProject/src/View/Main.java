package View;

import Controller.PlayDAO;
import javazoom.jl.player.MP3Player;

public class Main {

	public static void main(String[] args) {
		int happyEndingFlag = 0;
		
		MP3Player mp3 = new MP3Player();
		mp3.play(".\\player\\bgm.mp3");
		System.out.println(mp3.isPlaying());
		
		while (true) {
			LoginMenu lMenu = new LoginMenu();
			String userId = lMenu.selectLoginMenu();
			
			if(userId.equals("")) return;
			
			PlayDAO dao = new PlayDAO();
			Intro gIntro = new Intro();
			PlayMenu pMenu = new PlayMenu();
			
			if (dao.checkPlayDays(userId) == 0) {
				gIntro.gameIntro(userId);
			}
			
			if (dao.getPlayInfo(userId) != null) {
				happyEndingFlag = pMenu.selectPlayMenu(userId);
			}
			
			if (happyEndingFlag == 1) {
				Outro r2 = new Outro();
				Thread t2 = new Thread(r2);
				mp3.stop();
				t2.run();
			} else if(happyEndingFlag == 2) {
				GameOverAscii r1 = new GameOverAscii();
				Thread t1 = new Thread(r1);
				mp3.stop();
				t1.run();
			} else {
				continue;
			}
			
		}
		
	}

}
