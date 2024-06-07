package View;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import Controller.EventDAO;
import Controller.PlayDAO;
import Controller.PlayGame;
import Controller.QuizDAO;
import Controller.Shop;
import Model.EventDTO;
import Model.PlayDTO;
import Model.QuizDTO;
import javazoom.jl.player.MP3Player;

public class PlayMenu {

	Scanner sc = new Scanner(System.in);
	PlayGame pg = new PlayGame();

	public int selectPlayMenu(String userId, MP3Player mp3) {
		PlayDAO pDao = new PlayDAO();
		PlayDTO dto = pDao.getPlayInfo(userId);

		MenuAscii mAscii = new MenuAscii();

		boolean menuFlag = true;
		boolean eventFlag[] = new boolean[3]; //이벤트(0: 랜덤이벤트 발생여부, 1: 하루경과 여부 - 상점, 종료 제외, 2: 스케쥴 실행 여부)
		boolean eventGameOverFlag = false;

		int bfPosition = 0;
		String position = "";

		int bfExperience, bfHealth, bfMoney; //변경전 능력치 변수

		int happyEndingFlag = 0; //해피엔딩여부(0:해피엔딩, 1:게임오버, 2:종료)

		System.out.println("게임을 시작합니다.");

		while (menuFlag) {
			Arrays.fill(eventFlag, false);

			position = checkPosition(dto);
			bfPosition = dto.getExperience() / 100;
			displayUserInfo(dto, position);

			bfExperience = dto.getExperience();
			bfHealth = dto.getHealth();
			bfMoney = dto.getMoney();

			System.out.println("[1] 업무 [2] 교육 [3] 출장 [4] 휴식 [5] 상점 [6] 종료");
			System.out.print(">>");

			int selectMenu;

			selectMenu = checkInputValid(sc);

			switch (selectMenu) {
			case 1: //업무
				eventFlag = callWork(dto, eventFlag);
				break;
			case 2: //교육
				eventFlag = callQuiz(dto, eventFlag);
				break;
			case 3: //출장
				eventFlag = callBusinessTrip(dto, eventFlag);
				break;
			case 4: //휴식
				if (dto.getHealth() == 0) {
					System.out.println("당신은 팔팔한 상태에요!!!!!");
					break;
				}
				eventFlag = callRest(dto, eventFlag);
				break;
			case 5: //상점
				eventFlag = callShop(dto, eventFlag);
				break;
			case 6: //종료(게임플레이 데이터 저장)
				pDao.savePlayInfo(dto);
				return 0;
			}

			if (eventFlag[2]) { //스케쥴 실행 여부
				mAscii.menu(selectMenu);
			}

			if (eventFlag[0]) { //랜덤이벤트 발생여부
				eventGameOverFlag = callEvent(dto, eventGameOverFlag);
			}

			GameOverAscii goAscii = new GameOverAscii();

			if (gameOver(dto, eventGameOverFlag)) { //게임오버여부(해피 엔딩 포함)
				eventGameOverFlag = true;
				happyEndingFlag = goAscii.gameOver(dto);
				return happyEndingFlag;
			}
			updatePosition(dto, bfPosition); //경험치로 직급 계산
			updatePlayerInfo(dto, bfExperience, bfHealth, bfMoney); //변경전 능력치와 스케쥴 실행 후 능력치 비교해서 출력

			if (eventFlag[1]) { //하루 경과 여부(상점 제외하고 업데이트)
				updatePlayDays(dto, selectMenu);
			}
		}
		return happyEndingFlag;

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

	private String checkPosition(PlayDTO dto) {
		String position = "";

		switch (Integer.toString(dto.getExperience() / 100).substring(0, 1)) {
		case "0":
			position = "사원";
			break;
		case "1":
			position = "대리";
			break;
		case "2":
			position = "과장";
			break;
		case "3":
			position = "부장";
			break;
		case "4":
			position = "사장";
			break;
		}
		return position;
	}

	private void displayUserInfo(PlayDTO dto, String position) {
		System.out.println("============================================================");
		System.out.println(dto.getPlayDays() + " 일차");
		System.out.println("닉네임 : " + dto.getNickName() + "\t직급 : " + position);
		System.out.println("경험치 : " + dto.getExperience() % 100 + "\t돈 : " + String.format("%,d", dto.getMoney()));
		for (int i = 0; i < 10; i++) {
			if ((dto.getExperience() % 100) / 10 > i) {
				System.out.print("■");
			} else {
				System.out.print("□");
			}
		}
		System.out.println();
		System.out.println("피로도 : " + dto.getHealth());
		for (int i = 0; i < 10; i++) {
			if (dto.getHealth() / 10 > i) {
				System.out.print("■");
			} else {
				System.out.print("□");
			}
		}
		System.out.println();
		System.out.println("============================================================");
	}

	private boolean[] callWork(PlayDTO dto, boolean[] eventFlag) {
		System.out.println("=====메뉴를 선택해주세요=====");
		System.out.println("[1] 업무 [2] 뒤로가기");
		System.out.print(">>");
		int b;
		b = checkInputValid(sc);

		if (b == 1) {
			eventFlag = pg.work(dto);
		} else if (b == 2) {
			return eventFlag;
		}
		return eventFlag;
	}

	private boolean[] callQuiz(PlayDTO dto, boolean[] eventFlag) {
		QuizDAO qDao = new QuizDAO();
		QuizDTO qDto = new QuizDTO(0, null, null, null, null);
		Random rd = new Random();

		int random = rd.nextInt(21) + 1;

		System.out.println("=====메뉴를 선택해주세요=====");
		System.out.println("[1] 교육 [2] 뒤로가기");
		System.out.print(">>");

		int b;
		b = checkInputValid(sc);

		if (b == 1) {
			eventFlag[0] = true;
			eventFlag[1] = true;
			eventFlag[2] = true;
			qDto = qDao.makeQuiz(dto, random);
			if (qDto != null) {
				if (random == qDto.getQuizSq()) {
					System.out.println("[" + qDto.getQuizSq() + "번] " + "분야는 " + qDto.getQuizPart() + "문제 : "
							+ qDto.getQuestion());

					System.out.print("답 입력>>");
					String num = sc.next();

					if (num.equals(qDto.getAnswer())) {
						dto.setAddExperience(dto.getAddExperience() + 5);
						System.out.println("정답입니다.");
					} else {
						System.out.println("틀렸습니다. 정답은 : " + qDto.getAnswer());
					}
				}
			}
		} else if (b == 2) {
			return eventFlag;
		}
		return eventFlag;
	}

	private boolean[] callBusinessTrip(PlayDTO dto, boolean[] eventFlag) {
		System.out.println("=====메뉴를 선택해주세요=====");
		System.out.println("[1] 출장 [2] 뒤로가기");
		System.out.print(">>");
		int b;

		b = checkInputValid(sc);

		if (b == 1) {
			eventFlag = pg.businessTrip(dto);
		} else if (b == 2) {
			return eventFlag;
		}
		return eventFlag;
	}

	private boolean[] callRest(PlayDTO dto, boolean[] eventFlag) {
		RestAscii rAscii = new RestAscii();
		System.out.println("집에 도착하셨습니다. 무엇을 하시겠습니까? ");
		System.out.println("");
		System.out.print("[1] 집에서 쉬기 [2] 여행가기 [3] 뒤로가기");
		System.out.print(">>");

		int a;
		a = checkInputValid(sc);

		if (a == 1) {
			eventFlag = pg.rest(dto, a);
			System.out.println("");
		} else if (a == 2) {
			if (dto.getMoney() < 100) {
				System.out.println("가지고 계신 돈이 없어서 여행을 갈 수 없어요 ㅠㅠ");
				System.out.println("");
				System.out.println("다시 선택해주세요.");
			} else {
				eventFlag = pg.rest(dto, a);
			}
		} else {
			return eventFlag;
		}

		rAscii.rest(a);

		return eventFlag;
	}

	private boolean[] callShop(PlayDTO dto, boolean[] eventFlag) {
		Shop sp = new Shop();

		System.out.println("=====메뉴=====");
		if (dto.getAddHealth() == 0)
			System.out.println("1. 자동차(2500원) : 일과 진행시 얻는 피로도를 3 감소시켜줍니다.");
		if (dto.getAddExperience() == 0)
			System.out.println("2. 개인노트북(2500원) : 일과 진행시 얻는 경험치를 2 올려줍니다.");
		if (dto.getAddMoney() == 0)
			System.out.println("3. 인센티브(800원) : 일과 진행시 얻는 돈을 3 올려줍니다.");
		System.out.println("4. 피로회복제(30원) : 즉시 피로도를 10 감소시켜줍니다.");
		System.out.println("5. 복권(300원) : 자신의 운을 시험하세요!");
		System.out.println("6. 뒤로가기");
		System.out.print(">>");

		int choice;
		choice = checkInputValid(sc);

		if (choice != 6) {
			eventFlag[2] = true;
		}
		int[] arr2 = new int[4];

		if (choice == 5) {
			System.out.println("복권을 구매하셨습니다. ");

			for (int i = 0; i < 4; i++) {
				System.out.print("10이하의 숫자를 4개 입력하세요 >> ");
				arr2[i] = sc.nextInt();
			}
		}

		int num = sp.shop(dto, choice, arr2);

		if (choice == 5) {
			if (num == 0) {
				System.out.println("꽝!");
			} else if (num == 1) {
				System.out.println("축하합니다!!! 1개 맞추셨습니다.");
				dto.setExperience(dto.getExperience() + 20);
				System.out.println("경험치 20을 획득하였습니다.");
			} else if (num == 2) {
				System.out.println("축하합니다!!! 2개 맞추셨습니다.");
				dto.setHealth(dto.getHealth() - 30);
				System.out.println("피로도가 30 감소하였습니다.");
			} else if (num == 3) {
				System.out.println("축하합니다!!! 3개 맞추셨습니다.");
				dto.setMoney(dto.getMoney() + 1000);
				System.out.println("돈을 1000원 획득하였습니다.");
			} else if (num == 4) {
				dto.setPosition(4);
				System.out.println("축하합니다!!! 4개 맞추셨습니다.");
				System.out.println("사장으로 진급하였습니다.");
			}
		}
		return eventFlag;

	}

	private void updatePosition(PlayDTO dto, int bfPosition) {
		PositionAscii pAscii = new PositionAscii();

		dto.setHealth(dto.getHealth() < 0 ? 0 : dto.getHealth());
		dto.setPosition(dto.getExperience() / 100);
		if (bfPosition != dto.getExperience() / 100)
			pAscii.position(dto.getPosition());
	}

	private void updatePlayerInfo(PlayDTO dto, int bfExperience, int bfHealth, int bfMoney) {
		int sumExp = dto.getExperience() - bfExperience;
		int sumHealth = dto.getHealth() - bfHealth;
		int sumMoney = dto.getMoney() - bfMoney;

		String plus = "+";

		if (dto.getExperience() == bfExperience) {
			plus = "";
		}

		if (dto.getHealth() == bfHealth) {
			plus = "";
		}

		if (dto.getMoney() == bfMoney) {
			plus = "";
		}

		System.out.println("------------------------------------------------------------");
		System.out.print("경험치 : " + (sumExp > 0 ? plus + sumExp : sumExp));
		System.out.print("\t피로도 : " + (sumHealth > 0 ? plus + sumHealth : sumHealth));
		System.out.print("\t돈 : " + (sumMoney > 0 ? plus + sumMoney : sumMoney));
		System.out.println();
		System.out.println("------------------------------------------------------------");
	}

	private boolean gameOver(PlayDTO dto, boolean eventGameOverFlag) {
		boolean gameOverFlag = false;

		if (dto.getHealth() >= 100 || dto.getMoney() <= 0 || (dto.getExperience() / 100) == 4) {
			gameOverFlag = true;
		}

		if (eventGameOverFlag == true) {
			gameOverFlag = true;
		}

		return gameOverFlag;
	}

	private void updatePlayDays(PlayDTO dto, int selectMenu) {
		if (selectMenu != 5)
			dto.setPlayDays(dto.getPlayDays() + 1);

	}

	private boolean callEvent(PlayDTO dto, boolean eventGameOverFlag) {
		System.out.println("===== 이벤트 발생! =====");

		EventDAO rDao = new EventDAO();
		EventDTO rDto = rDao.ran();

		if (rDto.getEventNumber() == 1) {
			System.out.println(rDto.getEventNumber() + ". " + rDto.getEventText());
			eventGameOverFlag = true;
		}

		else if (rDto.getEventNumber() >= 2 && rDto.getEventNumber() < 13) {
			while (true) {
				System.out.println(rDto.getEventText());
				System.out.print(">>");
				int num;
				num = checkInputValid(sc);
				if (num == 1) {
					System.out.println(rDto.getEventAns1());
					dto.setExperience(dto.getExperience() - 10);
					dto.setHealth(dto.getHealth() - 10);
					break;
				} else if (num == 2) {
					System.out.println(rDto.getEbentAns2());
					dto.setExperience(dto.getExperience() + 10);
					dto.setHealth(dto.getHealth() + 10);
					break;
				} else {
					System.out.println("다시 입력하시오.");
				}
			}
		}

		else if (rDto.getEventNumber() >= 13 && rDto.getEventNumber() < 23) {
			while (true) {
				System.out.println(rDto.getEventText());
				System.out.print(">>");
				int num;
				num = checkInputValid(sc);
				if (num == 1) {
					System.out.println(rDto.getEventAns1());
					dto.setExperience(dto.getExperience() + 10);
					dto.setHealth(dto.getHealth() + 10);
					break;
				} else if (num == 2) {
					System.out.println(rDto.getEbentAns2());
					dto.setHealth(dto.getHealth() - 10);
					break;
				} else {
					System.out.println("다시 입력하시오.");
				}
			}
		}

		else if (rDto.getEventNumber() >= 23 && rDto.getEventNumber() < 33) {
			while (true) {
				System.out.println(rDto.getEventText());
				System.out.print(">>");
				int num;
				num = checkInputValid(sc);
				if (num == 1) {
					System.out.println(rDto.getEventAns1());
					dto.setMoney(dto.getMoney() - 50);
					dto.setHealth(dto.getHealth() - 10);
					break;
				} else if (num == 2) {
					System.out.println(rDto.getEbentAns2());
					dto.setHealth(dto.getHealth() + 10);
					break;
				} else {
					System.out.println("다시 입력하시오.");
				}
			}
		}

		else if (rDto.getEventNumber() >= 33 && rDto.getEventNumber() < 43) {
			while (true) {
				System.out.println(rDto.getEventText());
				System.out.print(">>");
				int num;
				num = checkInputValid(sc);
				if (num == 1) {
					System.out.println(rDto.getEventAns1());
					dto.setMoney(dto.getMoney() - 100);
					dto.setHealth(dto.getHealth() - 15);
					break;
				} else if (num == 2) {
					System.out.println(rDto.getEbentAns2());
					dto.setMoney(dto.getMoney() + 100);
					break;
				} else {
					System.out.println("다시 입력하시오.");
				}
			}
		}

		else if (rDto.getEventNumber() >= 43 && rDto.getEventNumber() < 49) {
			while (true) {
				System.out.println(rDto.getEventText());
				System.out.print(">>");
				int num;
				num = checkInputValid(sc);
				if (num == 1) {
					System.out.println(rDto.getEventAns1());
					dto.setMoney(dto.getMoney() + 100);
					dto.setHealth(dto.getHealth() + 10);
					break;
				} else if (num == 2) {
					System.out.println(rDto.getEbentAns2());
					eventGameOverFlag = true;
					break;
				} else {
					System.out.println("다시 입력하시오.");
				}
			}
		} else if (rDto.getEventNumber() >= 49 && rDto.getEventNumber() < 54) {
			while (true) {
				System.out.println(rDto.getEventText());
				System.out.print(">>");
				int num;
				num = checkInputValid(sc);
				if (num == 1) {
					System.out.println(rDto.getEventAns1());
					eventGameOverFlag = true;
					break;
				} else if (num == 2) {
					System.out.println(rDto.getEbentAns2());
					dto.setExperience(dto.getExperience() + 5);
					dto.setHealth(dto.getHealth() + 15);
					break;
				} else {
					System.out.println("다시 입력하시오.");
				}
			}
		}

		else if (rDto.getEventNumber() >= 54 && rDto.getEventNumber() < 59) {
			while (true) {
				System.out.println(rDto.getEventText());
				System.out.print(">>");
				int num;
				num = checkInputValid(sc);
				if (num == 1) {
					System.out.println(rDto.getEventAns1());
					dto.setHealth(dto.getHealth() + 15);
					break;
				} else if (num == 2) {
					System.out.println(rDto.getEbentAns2());
					eventGameOverFlag = true;
					break;
				} else {
					System.out.println("다시 입력하시오.");
				}
			}
		}

		else if (rDto.getEventNumber() >= 59 && rDto.getEventNumber() < 64) {
			while (true) {
				System.out.println(rDto.getEventText());
				System.out.print(">>");
				int num;
				num = checkInputValid(sc);
				if (num == 1) {
					System.out.println(rDto.getEventAns1());
					eventGameOverFlag = true;
					break;
				} else if (num == 2) {
					System.out.println(rDto.getEbentAns2());
					dto.setExperience(dto.getExperience() + 10);
					dto.setHealth(dto.getHealth() + 15);
					break;
				} else {
					System.out.println("다시 입력하시오.");
				}
			}
		}

		else if (rDto.getEventNumber() >= 64 && rDto.getEventNumber() < 74) {
			while (true) {
				System.out.println(rDto.getEventText());
				System.out.print(">>");
				int num;
				num = checkInputValid(sc);
				if (num == 1) {
					System.out.println(rDto.getEventAns1());
					eventGameOverFlag = true;
					break;
				} else if (num == 2) {
					System.out.println(rDto.getEbentAns2());
					dto.setMoney(dto.getMoney() + 50);
					dto.setHealth(dto.getHealth() + 10);
					break;
				} else {
					System.out.println("다시 입력하시오.");
				}
			}
		}

		else if (rDto.getEventNumber() >= 74 && rDto.getEventNumber() < 79) {
			while (true) {
				System.out.println(rDto.getEventText());
				System.out.print(">>");
				int num;
				num = checkInputValid(sc);
				if (num == 1) {
					System.out.println(rDto.getEventAns1());
					dto.setHealth(dto.getHealth() + 10);
					break;
				} else if (num == 2) {
					System.out.println(rDto.getEbentAns2());
					eventGameOverFlag = true;
					break;
				} else {
					System.out.println("다시 입력하시오.");
				}
			}
		} else if (rDto.getEventNumber() >= 79 && rDto.getEventNumber() < 84) {
			while (true) {
				System.out.println(rDto.getEventText());
				System.out.print(">>");
				int num;
				num = checkInputValid(sc);
				if (num == 1) {
					System.out.println(rDto.getEventAns1());
					eventGameOverFlag = true;
					break;
				} else if (num == 2) {
					System.out.println(rDto.getEbentAns2());
					dto.setMoney(dto.getMoney() + 50);
					dto.setHealth(dto.getHealth() - 10);
					break;
				} else {
					System.out.println("다시 입력하시오.");
				}
			}
		}

		else if (rDto.getEventNumber() >= 84 && rDto.getEventNumber() < 89) {
			while (true) {
				System.out.println(rDto.getEventText());
				System.out.print(">>");
				int num;
				num = checkInputValid(sc);
				if (num == 1) {
					System.out.println(rDto.getEventAns1());
					dto.setMoney(dto.getMoney() - 50);
					dto.setHealth(dto.getHealth() + 10);
					break;
				} else if (num == 2) {
					System.out.println(rDto.getEbentAns2());
					eventGameOverFlag = true;
					break;
				} else {
					System.out.println("다시 입력하시오.");
				}
			}
		}

		else if (rDto.getEventNumber() >= 89 && rDto.getEventNumber() < 94) {
			while (true) {
				System.out.println(rDto.getEventText());
				System.out.print(">>");
				int num;
				num = checkInputValid(sc);
				if (num == 1) {
					System.out.println(rDto.getEventAns1());
					eventGameOverFlag = true;
					break;
				} else if (num == 2) {
					System.out.println(rDto.getEbentAns2());
					dto.setExperience(dto.getExperience() + 10);
					break;
				} else {
					System.out.println("다시 입력하시오.");
				}
			}
		}

		else if (rDto.getEventNumber() >= 94 && rDto.getEventNumber() < 99) {
			while (true) {
				System.out.println(rDto.getEventText());
				System.out.print(">>");
				int num;
				num = checkInputValid(sc);
				if (num == 1) {
					System.out.println(rDto.getEventAns1());
					eventGameOverFlag = true;
					break;
				} else if (num == 2) {
					System.out.println(rDto.getEbentAns2());
					dto.setExperience(dto.getExperience() + 15);
					dto.setHealth(dto.getHealth() + 10);
					break;
				} else {
					System.out.println("다시 입력하시오.");
				}
			}
		}

		else if (rDto.getEventNumber() >= 99 && rDto.getEventNumber() < 110) {
			while (true) {
				System.out.println(rDto.getEventText());
				System.out.print(">>");
				int num;
				num = checkInputValid(sc);
				if (num == 1) {
					System.out.println(rDto.getEventAns1());
					dto.setMoney(dto.getMoney() + 100);
					break;
				} else if (num == 2) {
					System.out.println(rDto.getEbentAns2());
					dto.setExperience(dto.getExperience() + 20);
					dto.setMoney(dto.getMoney() + 50);
					break;
				} else {
					System.out.println("다시 입력하시오.");
				}
			}
		}
		return eventGameOverFlag;
	}

}
