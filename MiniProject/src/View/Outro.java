package View;

public class Outro implements Runnable {

	String[] str = { "\t\t\t  [Ending Credit]", 
			"\t\t\t\tCast",
			"\t\t[PM] 프로젝트 관리자, PPT 및 발표\t송 창 희", 
			"\t\t[PL] 프로젝트 리더, 메인 프로그래머\t임 현 진", 
			"\t\t[PE] 개발자, 이벤트 설계 및 구현\t류 형 욱",
			"\t\t[PE] 개발자, 상점 설계 및 구현\t배 지 환",
			"\t\t[PE] 개발자, 퀴즈 설계 및 구현\t김 기 백", 
			"\t\t[QA] 품질관리자, 기획 및 알파테스터\t조 근 하", 
			"\t\t\t제작 지원\t 스마트인재개발원", 
			"\t\t\t장소 협찬\t 클라우드 3차 6교육실",
			"\t\tThanks to 채 수 민, 조 준 용, 서 보 경, 이 도 연", 
			"\t\t\t 제작\t사장과 전쟁", };

	@Override
	public void run() {
		for (int i = 0; i < 12; i++) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(str[i]);
			System.out.println();
			System.out.println();
		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
