package View;

import Controller.PlayDAO;

public class Main {

	public static void main(String[] args) {
		boolean happyEndingFlag = false;
		
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
				GameOverAscii r1 = new GameOverAscii();
				Thread t1 = new Thread(r1);
				t1.run();
			}
			
			if (happyEndingFlag == true) {
				Outro r2 = new Outro();
				Thread t2 = new Thread(r2);
				t2.run();
			}
			
		}
		
	}

}
