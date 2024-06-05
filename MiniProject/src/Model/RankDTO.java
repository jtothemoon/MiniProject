package Model;

public class RankDTO {
	private int rank;
	private String id;
	private String nickName;
	private int playDays;
	private int money;
	
	public RankDTO(int rank, String id, String nickName, int playDays, int money) {
		this.rank = rank;
		this.id = id;
		this.nickName = nickName;
		this.playDays = playDays;
		this.money = money;
	}
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getPlayDays() {
		return playDays;
	}

	public void setPlayDays(int playDays) {
		this.playDays = playDays;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	
	
	
}
