package Controller;

import java.util.Scanner;

import Model.PlayDTO;

public class PlayGame {
	Scanner sc = new Scanner(System.in);

	public boolean[] work(PlayDTO dto) {
		boolean[] eventFlag = new boolean[3];
		
		dto.setExperience(dto.getExperience() + dto.getAddExperience() + 10);
		dto.setMoney(dto.getMoney() + dto.getAddMoney() + 10);
		dto.setHealth(dto.getHealth() + (10 - dto.getAddHealth()));
		eventFlag[0] = true;
		eventFlag[1] = true;
		eventFlag[2] = true;
		
		return eventFlag;
	}

	public boolean[] businessTrip(PlayDTO dto) {
		boolean[] eventFlag = new boolean[3];
		
		dto.setExperience(dto.getExperience() + dto.getAddExperience() + 15);
		dto.setMoney(dto.getMoney() + dto.getAddMoney() + 15);
		dto.setHealth(dto.getHealth() + (20 - dto.getAddHealth()));
		eventFlag[0] = true;
		eventFlag[1] = true;
		eventFlag[2] = true;
		return eventFlag;
	}

	public boolean[] rest(PlayDTO dto, int a) {
		boolean[] eventFlag = new boolean[3];
		
		if (a == 1) {
			dto.setHealth(dto.getHealth() - 10);
		} else {
			dto.setHealth(dto.getHealth() - 20);
			dto.setMoney(dto.getMoney() - 100);
		}
		
		eventFlag[0] = false;
		eventFlag[1] = true;
		eventFlag[2] = true;

		return eventFlag;
	}
}
