package View;

import Controller.PlayDAO;

public class Main {

	public static void main(String[] args) {
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
			pMenu.selectPlayMenu(userId);
		}
		
	}

}
