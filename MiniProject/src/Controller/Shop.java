package Controller;

import java.util.Random;
import java.util.Scanner;

import Model.PlayDTO;

public class Shop {
	public int shop(PlayDTO dto, int choice, int[] arr2) {
		Scanner sc = new Scanner(System.in);
		Random rd = new Random();

		int num1 = 0;

		if (choice == 1) {
			dto.setMoney(dto.getMoney() - 250);
			dto.setAddHealth(dto.getAddHealth() + 3);
		} else if (choice == 2) {
			dto.setMoney(dto.getMoney() - 250);
			dto.setAddExperience(dto.getAddExperience() + 2);
		} else if (choice == 3) {
			dto.setMoney(dto.getMoney() - 80);
			dto.setAddMoney(dto.getAddMoney() + 3);
		} else if (choice == 4) {
			dto.setMoney(dto.getMoney() - 30);
			dto.setHealth(dto.getHealth() - 10);
		} else if (choice == 5) {
			dto.setMoney(dto.getMoney() - 30);
			int[] arr = new int[4];

			for (int i = 0; i < 4; i++) {
				arr[i] = rd.nextInt(5) + 1;
				for (int j = 0; j < i; j++) {
					if (arr[i] == arr[j]) {
						i--;
					}
				}
			}

			for (int i = 0; i < 4; i++) {
				System.out.print(arr2[i] + " ");
			}
			System.out.println();
			for (int i = 0; i < 4; i++) {
				System.out.print(arr[i] + " ");
			}
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < arr.length; j++) {
					if (arr[i] == arr2[j]) {
						num1++;
					}
				}
			}
			System.out.println();
			
		} else {
			return num1;
		}
		return num1;

	}
}
