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

public class PlayMenu {

	Scanner sc = new Scanner(System.in);
	PlayGame pg = new PlayGame();

	public void selectPlayMenu(String userId) {
		PlayDAO pDao = new PlayDAO();
		PlayDTO dto = pDao.getPlayInfo(userId);

		boolean menuFlag = true;
		boolean eventFlag[] = new boolean[2];

		int bfPosition = 0;
		String position = "";

		System.out.println("게임을 시작합니다.");

		while (menuFlag) {
			Arrays.fill(eventFlag, false);

			position = checkPosition(dto);
			bfPosition = dto.getExperience() / 100;
			displayUserInfo(dto, position);

			System.out.println("[1] 업무 [2] 교육 [3] 출장 [4] 휴식 [5] 상점 [6] 종료");
			System.out.print(">>");
			int selectMenu = sc.nextInt();

			switch (selectMenu) {
			case 1:
				eventFlag = callWork(dto, eventFlag);
				break;
			case 2:
				eventFlag = callQuiz(dto, eventFlag);
				break;
			case 3:
				eventFlag = callBusinessTrip(dto, eventFlag);
				break;
			case 4:
				if (dto.getHealth() == 0) {
					System.out.println("당신은 팔팔한 상태에요!!!!!");
					break;
				}
				eventFlag = callRest(dto, eventFlag);
				break;
			case 5:
				callShop(dto);
				break;
			case 6:
				pDao.savePlayInfo(dto);
				menuFlag = false;
				break;
			}

			if (eventFlag[0]) {
				callEvent(dto);
			}
			
			updatePosition(dto, bfPosition);
			
			if (gameOver(dto)) {
				break;
			}

			if (eventFlag[1]) {
				updatePlayDays(dto, selectMenu);
			}
		}

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
		System.out.println("==============================");
		System.out.println(dto.getPlayDays() + " 일차");
		System.out.println("닉네임 : " + dto.getNickName() + "\t직급 : " + position);
		System.out.println("경험치 : " + dto.getExperience() % 100 + "\t피로도 : " + dto.getHealth() +  "\t돈 : " + dto.getMoney());
		for (int i = 0; i < 10; i++) {
			if ((dto.getExperience() % 100) / 10 > i) {
				System.out.print("■");
			} else {
				System.out.print("□");
			}
		}
		System.out.println();
		System.out.println("==============================");
	}

	private boolean[] callWork(PlayDTO dto, boolean[] eventFlag) {
		System.out.println("=====메뉴를 선택해주세요=====");
		System.out.println("[1] 업무 [2] 뒤로가기");
		System.out.print(">>");
		int b = sc.nextInt();

		if (b == 1) {
			eventFlag = pg.work(dto);
			System.out.println("=====업무중=====");
			System.out.println("오늘도 칼퇴를 위해서!!!");
			System.out.println(dto.getExperience() + "\t" + dto.getMoney() + "\t" + dto.getHealth());
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
		int b = sc.nextInt();

		if (b == 1) {
			eventFlag[0] = true;
			eventFlag[1] = true;
			qDto = qDao.makeQuiz(dto, random);
			if (qDto != null) {
				if (random == qDto.getQuizSq()) {
					System.out.println("[" +
							qDto.getQuizSq() + "번] " + "분야는 " + qDto.getQuizPart() + "문제 : " + qDto.getQuestion());

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
		int b = sc.nextInt();

		if (b == 1) {
			eventFlag = pg.businessTrip(dto);
			System.out.println("=====출장 업무중=====");
			System.out.println("오늘은 출장업무네... 얼른 끝내고 집에 가자!!");
			System.out.println("exp" + "\t" + dto.getExperience() + "\t" + "money" + "\t" + dto.getMoney() + "\t" + "hp"
					+ "\t" + dto.getHealth());
		} else if (b == 2) {
			return eventFlag;
		}
		return eventFlag;
	}

	private boolean[] callRest(PlayDTO dto, boolean[] eventFlag) {
		System.out.println("집에 도착하셨습니다. 무엇을 하시겠습니까? ");
		System.out.println("");
		System.out.print("[1] 집에서 쉬기 [2] 여행가기 [3] 뒤로가기");
		System.out.print(">>");

		int a = sc.nextInt();

		if (a == 1) {
			eventFlag = pg.rest(dto, a);
			System.out.println("");
			System.out.println("집에서 쉬기를 선택하셨습니다.  역시 집이 최고네요!!");
			System.out.println("");
			System.out.println("피로도  " + (10) + "  감소하였습니다!!");
			System.out.println("");
		} else if (a == 2) {
			if (dto.getMoney() < 100) {
				System.out.println("가지고 계신 돈이 없어서 여행을 갈 수 없어요 ㅠㅠ");
				System.out.println("");
				System.out.println("다시 선택해주세요.");
			} else {
				eventFlag = pg.rest(dto, a);
				System.out.println("여행을 선택하셨습니다. 힐링 귣!");
				System.out.println("");
				System.out.println("피로도 " + (10) + ".   골드가    " + 100 + "     감소하였습니다!!!");
				System.out.println("");
			}
		} else {
			return eventFlag;
		}
		return eventFlag;
	}

	private void callShop(PlayDTO dto) {
		Shop sp = new Shop();

		System.out.println("=====메뉴=====");
		if (dto.getAddHealth() == 0)
			System.out.println("1. 자동차(250원) : 일과 진행시 얻는 피로도를 3 감소시켜줍니다.");
		if (dto.getAddExperience() == 0)
			System.out.println("2. 개인노트북(250원) : 일과 진행시 얻는 경험치를 2 올려줍니다.");
		if (dto.getAddMoney() == 0)
			System.out.println("3. 인센티브(80) : 일과 진행시 얻는 돈을 3 올려줍니다.");
		System.out.println("4. 피로회복제(30원) : 즉시 피로도를 10 감소시켜줍니다.");
		System.out.println("5. 복권(30원) : 자신의 운을 시험하세요!");
		System.out.println("6. 뒤로가기");
		System.out.print(">>");

		int choice = sc.nextInt();
		int[] arr2 = new int[4];

		if (choice == 5) {
			System.out.println("복권을 구매하셨습니다. ");

			for (int i = 0; i < 4; i++) {
				System.out.print("5이하의 숫자를 입력하세요 >> ");
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
				dto.setHealth(dto.getHealth() - 20);
				System.out.println("피로도가 20 감소하였습니다.");
			} else if (num == 3) {
				System.out.println("축하합니다!!! 3개 맞추셨습니다.");
				dto.setMoney(dto.getMoney() + 100);
				System.out.println("돈을 100원 획득하였습니다.");
			} else if (num == 4) {
				dto.setPosition(4);
				System.out.println("축하합니다!!! 4개 맞추셨습니다.");
				System.out.println("사장으로 진급하였습니다.");
			}
		}

	}

	private void updatePosition(PlayDTO dto, int bfPosition) {
		String updatePosition = "";
		updatePosition = checkPosition(dto);
		dto.setPosition(dto.getExperience() / 100);
		if (bfPosition != dto.getExperience() / 100)
			System.out.println(updatePosition + "(으)로 진급하셨습니다.");
	}

	private boolean gameOver(PlayDTO dto) {
		boolean gameOverFlag = false;

		if (dto.getHealth() >= 100 || dto.getMoney() <= 0 || dto.getPosition() == 4) {
			gameOverFlag = true;
			System.out.println("Game Over!!");
		}

		return gameOverFlag;
	}

	private void updatePlayDays(PlayDTO dto, int selectMenu) {
		if (selectMenu != 5)
			dto.setPlayDays(dto.getPlayDays() + 1);

	}

	private void callEvent(PlayDTO dto) {
		System.out.println("===== 이벤트 발생! =====");

        EventDAO rDao = new EventDAO();
        EventDTO rDto = rDao.ran();

        if (rDto.getEventNumber() == 1) {
           System.out.println(rDto.getEventNumber() + ". " + rDto.getEventText());
           dto.setHealth(dto.getHealth() - 100);
        }

        else if (rDto.getEventNumber() >= 2 && rDto.getEventNumber() < 13) {
           while (true) {
              System.out.println(rDto.getEventText());
              System.out.print(">>");
              int num = sc.nextInt();
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
           System.out.println(rDto.getEventText());
           System.out.print(">>");
           int num = sc.nextInt();

           while (true) {
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
           System.out.println(rDto.getEventText());
           System.out.print(">>");
           int num = sc.nextInt();

           while (true) {
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
           System.out.println(rDto.getEventText());
           System.out.print(">>");
           int num = sc.nextInt();

           while (true) {
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
           System.out.println(rDto.getEventText());
           System.out.print(">>");
           int num = sc.nextInt();

           while (true) {
              if (num == 1) {
                 System.out.println(rDto.getEventAns1());
                 dto.setMoney(dto.getMoney() + 100);
                 dto.setHealth(dto.getHealth() + 10);
                 break;
              } else if (num == 2) {
                 System.out.println(rDto.getEbentAns2());
                 dto.setHealth(dto.getHealth() - 100);
                 break;
              } else {
                 System.out.println("다시 입력하시오.");
              }
           }
        } else if (rDto.getEventNumber() >= 49 && rDto.getEventNumber() < 54) {
           System.out.println(rDto.getEventText());
           System.out.print(">>");
           int num = sc.nextInt();

           while (true) {
              if (num == 1) {
                 System.out.println(rDto.getEventAns1());
                 dto.setHealth(dto.getHealth() - 100);
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
           System.out.println(rDto.getEventText());
           System.out.print(">>");
           int num = sc.nextInt();

           while (true) {
              if (num == 1) {
                 System.out.println(rDto.getEventAns1());
                 dto.setHealth(dto.getHealth() + 15);
                 break;
              } else if (num == 2) {
                 System.out.println(rDto.getEbentAns2());
                 dto.setHealth(dto.getHealth() - 100);
                 break;
              } else {
                 System.out.println("다시 입력하시오.");
              }
           }
        }

        else if (rDto.getEventNumber() >= 59 && rDto.getEventNumber() < 64) {
           System.out.println(rDto.getEventText());
           System.out.print(">>");
           int num = sc.nextInt();

           while (true) {
              if (num == 1) {
                 System.out.println(rDto.getEventAns1());
                 dto.setHealth(dto.getHealth() - 100);
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
           System.out.println(rDto.getEventText());
           System.out.print(">>");
           int num = sc.nextInt();

           while (true) {
              if (num == 1) {
                 System.out.println(rDto.getEventAns1());
                 dto.setHealth(dto.getHealth() - 100);
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
           System.out.println(rDto.getEventText());
           System.out.print(">>");
           int num = sc.nextInt();

           while (true) {
              if (num == 1) {
                 System.out.println(rDto.getEventAns1());
                 dto.setHealth(dto.getHealth() + 10);
                 break;
              } else if (num == 2) {
                 System.out.println(rDto.getEbentAns2());
                 dto.setHealth(dto.getHealth() - 100);
                 break;
              } else {
                 System.out.println("다시 입력하시오.");
              }
           }
        } else if (rDto.getEventNumber() >= 79 && rDto.getEventNumber() < 84) {
           System.out.println(rDto.getEventText());
           System.out.print(">>");
           int num = sc.nextInt();

           while (true) {
              if (num == 1) {
                 System.out.println(rDto.getEventAns1());
                 dto.setHealth(dto.getHealth() - 100);
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
           System.out.println(rDto.getEventText());
           System.out.print(">>");
           int num = sc.nextInt();

           while (true) {
              if (num == 1) {
                 System.out.println(rDto.getEventAns1());
                 dto.setMoney(dto.getMoney() - 50);
                 dto.setHealth(dto.getHealth() + 10);
                 break;
              } else if (num == 2) {
                 System.out.println(rDto.getEbentAns2());
                 dto.setHealth(dto.getHealth() - 100);
                 break;
              } else {
                 System.out.println("다시 입력하시오.");
              }
           }
        }

        else if (rDto.getEventNumber() >= 89 && rDto.getEventNumber() < 94) {
           System.out.println(rDto.getEventText());
           System.out.print(">>");
           int num = sc.nextInt();

           while (true) {
              if (num == 1) {
                 System.out.println(rDto.getEventAns1());
                 dto.setHealth(dto.getHealth() - 100);
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
           System.out.println(rDto.getEventText());
           System.out.print(">>");
           int num = sc.nextInt();

           while (true) {
              if (num == 1) {
                 System.out.println(rDto.getEventAns1());
                 dto.setHealth(dto.getHealth() - 100);
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
           System.out.println(rDto.getEventText());
           System.out.print(">>");
           int num = sc.nextInt();

           while (true) {
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
	}
}
