package View;

import java.util.Scanner;

import Controller.PlayDAO;
import Model.PlayDTO;

public class Intro {
	public void gameIntro(String userId) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("취직을 했는데 회사가 이상하다");
		System.out.println("일 잘하면 내가 사장이란다.");
		System.out.println("사장이 내게 다가와 묻는다");
		System.out.println("                    .............................................................          \r\n"
				+ "                             .............................................................          \r\n"
				+ "                             .=#*******************************************************#=.          \r\n"
				+ "                             ..#..##::: ##::::'###::::'##::::'##:'########::'#######:: #:.          \r\n"
				+ "                             ..#..###:: ##:::'## ##::: ###::'###: ##.....::'##.... ##::#:.          \r\n"
				+ "                             ..#..####: ##:' ##:. ##:: ####'####: ##::::::..::::  ##:: #:.          \r\n"
				+ "                             ..#..## ## ##:'##:::. ##: ## ### ##: ######::::::: ###::::#:.          \r\n"
				+ "                             ..#..##. ####: #########: ##. #: ##: ##...::::::: ##.:::::#:.          \r\n"
				+ "                             ..#..##:. ###: ##.... ##: ##:.:: ##: ##:::::::::...:::::::#:.          \r\n"
				+ "                             ..#..##::. ##: ##:::: ##: ##:::: ##: ########::::'##::::::#:.          \r\n"
				+ "                             ..#...::::..: :...::::::..::..:::::..::..:::::..::::::....#..          \r\n"
				+ "                             .*#*++++++++++++++++++++++:......:+++++++++++++++++++++++*#*.          \r\n"
				+ "                             .......................  .*-....#+...........................          \r\n"
				+ "                             ......................... +-..+*:............................          \r\n"
				+ "                             ......................... +-:#-..............................          \r\n"
				+ "                             ..........................*#+................................          \r\n"
				+ "                             ..........................=:.................................          \r\n"
				+ "                                          ...::::::::::...                                          \r\n"
				+ "                                      .-+*###############*+-.                                       \r\n"
				+ "                                   .-*#####*+==========++*####+.                                    \r\n"
				+ "                                 .-*###+=---------::::::::::-+#*::.                                 \r\n"
				+ "                               ..+###=-------------:::::::::::::::..                                \r\n"
				+ "                               .*##+------------:::=##*:::::::::::::..                              \r\n"
				+ "                              .###+-----=*###+---------+###*+---::+###.                             \r\n"
				+ "                              =##*----=#########=--=*########=---:+##+.                             \r\n"
				+ "                             .###=---=###=:::+########+-::=*##=---=*##.                             \r\n"
				+ "                             :###*+*####+::::::-*##*--:::::+####*+*###:                             \r\n"
				+ "                           .:*#######*=::::::::::::::::::::=*########:.                             \r\n"
				+ "                           .+##+-=*##=:--=*##*-:::::-*##*=-::=##*=-+##*.                            \r\n"
				+ "                           .+##+--+##=:*########=::=########*:=##+--=##*.                           \r\n"
				+ "                            -*##=-+##=:=*=----++::::++----=+-:=##+-=###-                            \r\n"
				+ "                            -*#######=::::+##::::==::::##+::::=#######-                             \r\n"
				+ "                           .=*#######+:::::--:::+##=:::--:::::+#######.                             \r\n"
				+ "                               .--=*##-::::::::+##+::::::::::##*=+##*.                              \r\n"
				+ "                              .+*+-+##*:::::::+##*-:::::::::+##+:+##=                               \r\n"
				+ "                              .##*==*##+-::-+##########+-::=##*--*##.                               \r\n"
				+ "                               =##+-=###*+*###*++++++*###++*###-:+##=                               \r\n"
				+ "                               .*##+--+#####+-::::::::-+#####=::+##*.                               \r\n"
				+ "                               ..+##*----==---********-::---:::*##+:.                               \r\n"
				+ "                                ..+###+------*########+:::::-+###+.                                 \r\n"
				+ "                                  .:*###+------+###*:::::::.-+##+.                                  \r\n"
				+ "                                    .:####*=------------=*####:.                                    \r\n"
				+ "                                      ..+####------------####+:.                                    \r\n"
				+ "                                    :*###########**##########*:                                     \r\n"
				+ "                             .=*###########+-##########-+###########*=.                             \r\n"
				+ "                        .-############..-####+-----:::+####-..############ =..                      \r\n"
				+ "                      .+####*+###*+###*...:+###*-:-*###*-...+###+*##*+* ####+.                      \r\n"
				+ "                    .-###*++++*##*=+####=.....*###**###*.....=####+=*## +==+*###=.                  \r\n"
				+ "                   .=###++++++###+-+####*-.....:######-.....:*####+-=# ##+===+###+.                 \r\n"
				+ "                  .=###++++++*##*--+##*##*::=*####**####*=::+##*##+-- *##*=====###=.                \r\n"
				+ "                  .+##++++++*###---+##=*#########====*########*=##+-- -###*====+##+:                \r\n"
				+ "                  :*##++++++###=---+##=-*##*+-###****###-+*##*-=##+-- -=##*+====##*-                \r\n"
				+ "                  -*##++++*###*=--+##+-----::##########::-:::-+##+-- =*###+=====*##-                \r\n"
				+ "                  -*##++++++*###*-=*##=---::###*==+###::::::=###+-* ###*+=======*##-                \r\n"
				+ "                  -*##++++++++*##*--=*##+--:::*##+===###:::::+###=--* ##*=======*##-                \r\n"
				+ "                  -*##++++++++###=---=###+-:::*##====*##::::+###=---= ###=======*##-                \r\n"
				+ "                  -*##++++++*###----=###+-:-*##====*##-::+###=----- *##+========*##-                \r\n"
				+ "                  -*##++++++*##*-------*##+-=###====*##=:+##*------- +##*=======*##-                \r\n"
				+ "                  -*##++++++*####+------*##**###====###**##*=------+* ###*======*##-                \r\n"
				+ "                  -*##++++++++*####*=----+#####*====*#####*----=*### #*++=======*##-                \r\n"
				+ "                  -*##++++++++++++*##*------+####*====*####*---*####*+ ++=======*##-                \r\n"
				+ "                  -*##++++++++++*++++++==*+-*###*+===+###*-+*####++++ *++++=====*##-                \r\n"
				+ "                  -*##++++++++##*++++++####**###++=+###**####*+===+* ##++=======*##-                \r\n"
				+ "                  -*##++++++++##*++++++++*#######**######*+======+* ##+++=======*##-                \r\n"
				+ "                  -*##++++++++##*++++++++++*###########*++++======+* ##++++=====###-                \r\n"
				+ "                  -*##++++++++##*++++++++++++++*######*++++++++===++* ##++++++++###-                \r\n"
				+ "                  -*##++++++++##*++++++++++++++++*##*++++++++++++++++++* ##+++++###-                \r\n"
				+ "                  -*##++++++++##*++++++++++++++++##*++++++++++++++++++* ##++++++###-                \r\n"
				+ "                  :*#*---------##*---------------=##=---------------* ##--------*#*-                \r\n"
				+ "                                                                                                      ");	
		
		makeNickName(userId);
		
		System.out.println("게임을 시작하기에 앞서 튜토리얼을 보겠습니까?");
		System.out.println("[1] 본다 [2] 안본다");
		int num = sc.nextInt();
		
		if(num != 2) {
			System.out.println("튜토리얼을 시작합니다.");
			System.out.println("게임의 목적은 '사원부터 사장까지 진급하기' 입니다.");
			System.out.println("단, 피로도가 100이 넘거나 돈이 0이 되면 게임은 종료됩니다.");
			System.out.println();
			System.out.println("게임을 시작하면 6개의 항목에서 자신의 행동을 선택 할 수 있습니다");
			System.out.println("[1] 업무 에서는 업무를 진행하며 경험치와 돈, 피로도가 증가합니다.");
			System.out.println("[2] 교육 에서는 퀴즈를 풀고 정답을 맞출 경우 얻는 경험치가 영구적으로 증가합니다.");
			System.out.println("[3] 출장 에서는 출장을 가게되며 경험치와 돈, 피로도가 증가합니다.");
			System.out.println("출장에서의 피로도는 업무에 비해 2배 더 증가하고 돈과 경험치는 1.5배 증가됩니다");
			System.out.println("업무, 교육, 출장 후에는 랜덤이벤트가 확률적으로 발생합니다");
			System.out.println("[4] 휴식 에서는 집에서 휴식과 여행가기가 있습니다.");
			System.out.println("두 선택지 모두 피로도를 감소시키지만 여행은 돈도 줄어들게 됩니다");
			System.out.println("[5] 상점 에서는 게임을 플레이 하면서 필요한 물품들을 구매 할 수 있습니다.");
			System.out.println("다양한 상품중에 복권은 게임을 빠르게 끝낼 기회를 얻을 수 있습니다! 자신의 운을 시험하세요!");
			System.out.println("[6] 종료는 지금까지의 진행 상황을 저장하고 종료합니다.");
			System.out.println("그럼 사장까지 무사히 진급하시길 바라며 즐거운 게임되세요.");
		}
	}
	
	private void makeNickName(String userId) {
		Scanner sc = new Scanner(System.in);
		
		int row = 0;
		
		System.out.print("닉네임을 입력하세요 : ");
		String nickName = sc.next();
		
		PlayDTO dto = new PlayDTO(userId, nickName, 0, 0, 1000, 0, 0, 0, 0, 1);
		PlayDAO dao = new PlayDAO();
		
		row = dao.insertPlayInfo(dto);
		if(row > 0) System.out.println(nickName + "사원 어서오게.");
	}
	
}
