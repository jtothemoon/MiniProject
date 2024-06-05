package View;

import Model.PlayDTO;

public class GameOverAscii implements Runnable {
	public boolean gameOver(PlayDTO dto) {
		boolean happyEndingFlag = false;
		
		if ((dto.getExperience() / 100) == 4) {
			happyEndingFlag = true;
			System.out.println("□■■■■■■■■■■□□□□□■■■■□□□■□■□□□□□□■■■■□□□■\r\n"
					+ "□□□■■□□■■□□□□□□□■■■■□□□■□■□□□□□■■■■■■□□■\r\n" + "□□□■■□□■■□□□□□□□□□□■□□□■□■□□□□□■■□□■■□□■\r\n"
					+ "□■■■■■■■■■■□□□□□□□□■□□□■□■□□□□□■■□□■■□□■\r\n" + "□□□□□□□□□□□□□□□□■■■■□■■■□■□□□□□■□□□□■□□■\r\n"
					+ "■■■■■■■■■■■■□□□□■■■■□■■■□■□□□□□■■□□□■□□■\r\n" + "□□□□□□□□□□□□□□□□■□□□□□□■□■□□□□□■■□□■■□□■\r\n"
					+ "□□■■■■■■■■■□□□□□■□□■■■□■□■□□□□□■■■□■■□□■\r\n" + "□□□□□□□□□■■□□□□□■■■■■■□■□■□□□□□■■■■■■□□■\r\n"
					+ "□□■■■■■■■■■□□□□□□□□□□□□■□■□□□□□□□□□□□□□■\r\n" + "□□■■□□□□□□□□□□□□□□□□□□□■□■□□□□□□□□□□□□□■\r\n"
					+ "□□■■■■■■■■■□□□□□□□□□□□□■□■□□□□□□□□□□□□□■");

			System.out.println("□□□■■□□□■■□■□□□□□■■■■■■■■■□□□□□□□□■□□□□□■□□□□□□□□□■□□□□□■\r\n"
					+ "□■■■■■■□■■□■□□□□□□□□■■□□□□□□□□□□□□■□□□□□■□□□□□□□□□■□□□□□■\r\n"
					+ "■■■■■■■□■■□■□□□□□□□□■■■□□□□□□□□□□□■□□■■■■□□□□□□□□□■□□□□□■\r\n"
					+ "□□■■■■□□■■□■□□□□□□■■■■■■■□□□□□□□□□■□□■■■■□□□□□□□□□■□□■■■■\r\n"
					+ "□■■■■■■□■■■■□□□□■■■■■□■■■■■□□□□□□■■■□□□□■□□□□□□□□■■■□■■■■\r\n"
					+ "□■■□□■■□■■■■□□□□■■■□□□□□■■□□□□□□□■■■□■■■■□□□□□□□□■■■□□□□■\r\n"
					+ "□■■□□■■□■■□■□□□□□□□□□□□□□□□□□□□□■■■■■■■■■□□□□□□□■■■■■□□□■\r\n"
					+ "□■■■■■■□■■□■□□□■■■■■■■■■■■■□□□□■■■□■■■□□■□□□□□□■■■□■■■□□■\r\n"
					+ "□□■■■■□□■■□■□□□□□□□□■■□□□□□□□□■■■□□□■■□□■□□□□□■■■□□□■■■□■\r\n"
					+ "□□□□□□□□■■□■□□□□□□□□■■□□□□□□□□□□□□□□□□□□■□□□□□□□□□□□□□□□■\r\n"
					+ "□□□□□□□□■■□■□□□□□□□□■■□□□□□□□□□□□□□□□□□□■□□□□□□□□□□□□□□□■\r\n"
					+ "□□□□□□□□■■□■□□□□□□□□■■□□□□□□□□□□□□□□□□□□■□□□□□□□□□□□□□□□■\r\n"
					+ "□□□□□□□□□□□□□□□□□□□□■■□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");

			System.out.println("□■■■■■■□□■□□□□□□□□□■□□□□■□□□□□□□□□■□□□□■□□□□□□□■□□□□□□□■□□□□□□■■■■■■□□■□□\r\n"
					+ "□□□□□□■□□■□□□□□□□□□■□□□□■□□□□□■■■■■■■■□■□□□□□□□■□□□□□□□■□□□□□□■■■■■■□□■□□\r\n"
					+ "□□□□□■■□□■□□□□□□□□□■□□□□■□□□□□□□□■■■□□□■□□□□□□□■□□□□□□□■□□□□□□■□□□□□□□■□□\r\n"
					+ "□□□□□■■□□■■■□□□□□□□■□□□□■□□□□□□■■■■■■■□■■■□□□□□■□□□□□□□■□□□□□□■□□□□□□□■□□\r\n"
					+ "□□□■■■■□□■□□□□□□□□■■■□□□■■■■□□□■■□□□■■□■□□□□□□□■□□□□□□□■□□□□□□■□□□□□□□■■■\r\n"
					+ "□■■■■■□□□■□□□□□□□□■■■□□□■■■□□□□■■■■■■■□■□□□□□□□■□□□□□□□■□□□□□□■□□□□□□□■■■\r\n"
					+ "□■■■□□□□□■□□□□□□□■■■■■□□■□□□□□□□■■■■■□□■□□□□□□□■□□□□□□□■□□□□□□■□□□□□□□■□□\r\n"
					+ "□□□■■■■■■■□□□□□□■■■□■■■□■□□□□□□□□■■□□□□■□□□□□□□■■■■■■■□■□□□□□□■■■■■■■□■□□\r\n"
					+ "□□□■□□□□□■□□□□□■■■□□□■■□■□□□□□□□□■■□□□□■□□□□□□□■■■■■■■□■□□□□□□■■■■■■■□■□□\r\n"
					+ "□□□■□□□□□■□□□□□□□□□□□□□□■□□□□□□□□■■■■■■■□□□□□□□□□□□□□□□■□□□□□□□□□□□□□□■□□\r\n"
					+ "□□□■□□□□□■□□□□□□□□□□□□□□■□□□□□□□□■■□□□□■□□□□□□□□□□□□□□□■□□□□□□□□□□□□□□■□□\r\n"
					+ "□□□■■■■■■■□□□□□□□□□□□□□□■□□□□□□□□■■■■■■■□□□□□□□□□□□□□□□■□□□□□□□□□□□□□□■□□");
		} else {
			System.out.println(
					"□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□■□□□■\r\n"
							+ "□□■■■■■■□□□□□□□□□□■■■□□□□□□□□□□■■□□□□□■■□□□□□□□□■■■■■■□□□□□□□□□□□□□□□■■■■■■□□□□□■■□□□□■■■□□□□■■■■■■□□□□□■■■■■■□□□□□■□□□■\r\n"
							+ "□■■■□□■■□□□□□□□□□□■■■□□□□□□□□□□■■■□□□■■■□□□□□□□□■□□□□□□□□□□□□□□□□□□□■■■□□■■■□□□□■■□□□□■■□□□□□■□□□□□□□□□□■□□□■■□□□□□■□□□■\r\n"
							+ "□■■□□□□□□□□□□□□□□■■■■■□□□□□□□□□■■■□□□■■■□□□□□□□□■□□□□□□□□□□□□□□□□□□□■■□□□□■■□□□□□■■□□□■■□□□□□■□□□□□□□□□□■□□□□■□□□□□■□□□■\r\n"
							+ "□■■□□□□□□□□□□□□□□■■□■■□□□□□□□□□■■■■□■■■■□□□□□□□□■□□□□□□□□□□□□□□□□□□□■■□□□□■■□□□□□■■□□■■□□□□□□■□□□□□□□□□□■□□□■■□□□□□■□□□■\r\n"
							+ "□■□□■■■■□□□□□□□□■■□□■■□□□□□□□□□■□■■□■■□■□□□□□□□□■■■■■■□□□□□□□□□□□□□□■□□□□□□■□□□□□■■■□■■□□□□□□■■■■■■□□□□□■■■■■■□□□□□■□□□■\r\n"
							+ "□■■□□□□■□□□□□□□□■■■■■■■□□□□□□□□■□■■■■■□■□□□□□□□□■□□□□□□□□□□□□□□□□□□□■■□□□□■■□□□□□□■■□■■□□□□□□■□□□□□□□□□□■□□■■□□□□□□■□□□■\r\n"
							+ "□■■□□□□■□□□□□□□□■■□□□■■□□□□□□□□■□□■■■□□■□□□□□□□□■□□□□□□□□□□□□□□□□□□□■■□□□□■■□□□□□□■■■■□□□□□□□■□□□□□□□□□□■□□□■■□□□□□■□□□■\r\n"
							+ "□■■■□□■■□□□□□□□■■□□□□■■□□□□□□□□■□□■■■□□■□□□□□□□□■□□□□□□□□□□□□□□□□□□□■■■□□■■■□□□□□□□■■■□□□□□□□■□□□□□□□□□□■□□□■■□□□□□□□□□□\r\n"
							+ "□□■■■■■■□□□□□□□■■□□□□□■■□□□□□□□■□□□□□□□■□□□□□□□□■■■■■■□□□□□□□□□□□□□□□■■■■■■□□□□□□□□■■■□□□□□□□■■■■■■□□□□□■□□□■■■□□□□□□□□□\r\n"
							+ "□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□■□□□■");

			System.out.println(
					".........................................................................-+#####*=:.............\r\n"
							+ "......................................................................-%@@@@@@@@@@@@=...........\r\n"
							+ "                                                                   .:%@@@@@@@@@@@@@@@%=..       \r\n"
							+ "                                                      ...:....    .-%@@@@@@@@@@@@@@@@@@=.       \r\n"
							+ "                                                   ..*@@@@@@@%-...:#@@@@@@@@@@@@@@@@@@@@:.      \r\n"
							+ "                                                  .*@@@@@@@@@@@#:.-@@@@@@@@@@@@@@@@@@@@@*..     \r\n"
							+ "                                                .*@@@@@@@@@@@@@@@:-@@@@@@@@@@@@@@@@@@@@@#..     \r\n"
							+ "                                              .-%@@@@@@@@@@@@@@@@#-%@@@@@@@@@@@@@@@@@@@@+.      \r\n"
							+ "                                           ...*@@@@@@@@@@@@@@@@@@@:*@@@@@@@@@@@@@@@@@@@%.       \r\n"
							+ "                                          ..-%@@@@@@@@@@@@@@@@@@@@+.#@@@@@@@@@@@@@@@@@#:.       \r\n"
							+ "                                          .-@@@@@@@@@@@@@@@@@@@@@@#..-@@@@@@@@@@@@@@@*...       \r\n"
							+ "                                        ..+@@@@@@@@@@@@@@@@@@@@@@@@=. .-#%@@@@@@@%#= .          \r\n"
							+ "                                        .*@@@@@@@@@@@@@@@@@@@@@@@@@*.... ..:----....            \r\n"
							+ "                                       .+@@@@@@@@@@@@@@@@@@@@@@@@@@@-..        ....             \r\n"
							+ "                                     ..=@@@@@@@@@@@@@@@@@%:=@@@@@@@@*..  ...                    \r\n"
							+ "                                     .-@@@@@@@@@@@@@@@@@*..:#@@@@@@@@:..                        \r\n"
							+ "                                   ..:@@@@@@@@@@@@@@@@@*....*@@@@@@@@#.                         \r\n"
							+ "                                   ..#@@@@@@@@@@@@@@@@*.. ...@@@@@@@@%:..                       \r\n"
							+ "                                   .=@@@@@@@@@@@@@@@@#...  ..#@@@@@@@@*..                       \r\n"
							+ "                                   .#@@@@@@@@@@@@@@@%....   ..@@@@@@@@#:.                       \r\n"
							+ "                                  .-@@@@@@@@@@@@@@@@-. ..    .*@@@@@@@@+..                      \r\n"
							+ "                                  .*@@@@@@@@@@@@@@@*.....     -@@@@@@@@#....                    \r\n"
							+ "                                  .@@@@@@@@@@@@@@@%=.         .*@@@@@@@@-...                    \r\n"
							+ "                                 .:@@@@@@@@@@@@@@@%..         .=%@@@@@@@#...                    \r\n"
							+ "                                 .:@@@@@@@@@@@@@@@%..         ..#@@@@@@@@-..                    \r\n"
							+ "                                 .+@@@@@@@@@@@@@@@@%-....     ..+@@@@@@@@%..                    \r\n"
							+ "                                ..%@@@@@@@@@@@@@@@@@@@:...    ...@@@@@@@@%-..                   \r\n"
							+ "                                 .@@@@@@@@@@@@@@@@@@@@@#:.    ...+@@@@@@@@*..                   \r\n"
							+ "                                .:%@@@@@@@@@@@@@@@@@@@@@@%........%@@@@@@@%:.                   \r\n"
							+ "                                .=@@@@@@@@@@@@@@@@@@@@@@@@@*...  .+@@@@@@@#:.                   \r\n"
							+ "                                .*@@@@@@@@@@@%@@@@@@@@@@@@@@@+..  .+%@@@@*:..                   \r\n"
							+ "                                .#@@@@@@@@@@#:-%@@@@@@@@@@@@@@%+.   ..::....                    \r\n"
							+ "                                .#@@@@@@@@@@+...=%@@@@@@@@@@@@@@@.                              \r\n"
							+ "                               .-%@@@@@@@@@@=.....=%@@@@@@@@@@@@@+..                            \r\n"
							+ "                               .=@@@@@@@@@@@=.......=%@@@@@@@@@@@*..                            \r\n"
							+ "                             ..:*@@@@@@@@@@%-.      ..@@@@@@@@@@@=...                           \r\n"
							+ "                           ..=%@@@@@@@@@@@@#..      .:@@@@@@@@@@@- ..                           \r\n"
							+ "                        ..:#@@@@@@@@@@@@@@@*..      .-@@@@@@@@@@@:...                           \r\n"
							+ "                    ....+@@@@@@@@@@@@@@@@@%:        .*@@@@@@@@@@@...                            \r\n"
							+ "                  ...-#@@@@@@@@@@@@@@@@@%*..       ..#@@@@@@@@@@@....                           \r\n"
							+ "                 ..*%@@@@@@@@@@@@@@@@@%-..          .%@@@@@@@@@@%...                            \r\n"
							+ "              ..-%@@@@@@@@@@@@@@@@@@*..            ..%@@@@@@@@@@*....                           \r\n"
							+ "             .=@@@@@@@@@@@@@@@@@@#-...             ..%@@@@@@@@@@=.                              \r\n"
							+ "            .=%@@@@@@@@@@@@@@@@+:.....             .:%@@@@@@@@@@=  ..                           \r\n"
							+ "            .*@@@@@@@@@@@@@@#:.... ...             .-%@@@@@@@@@@-  ..                           \r\n"
							+ "            .=%@@@@@@@@@@%+.... .. ...             .+@@@@@@@@@@@-  ..                           \r\n"
							+ "            ..=@@@@@@@@*:...                       .*@@@@@@@@@@%:                               \r\n"
							+ "             ...:+#%*:...                           *@@@@@@@@@@#:                               \r\n"
							+ "                  ...  ..                          .=@@@@@@@@@@*.                               \r\n"
							+ "                                                    .=@@@@@@@@*..                               \r\n"
							+ "                                                     ..:*%%#=....                               \r\n"
							+ "                                                     .       ....                               \r\n"
							+ "");
		}
		return happyEndingFlag;

	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
