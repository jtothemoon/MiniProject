package Model;

public class PlayDTO {
	private String id;
	private String nickName;
	private int position;
	private int experience;
	private int money;
	private int health;
	private int addExperience;
	private int addMoney;
	private int addHealth;
	private int playDays;
	
	public PlayDTO(String id, String nickName, int position, int experience, int money, int health, int addExperience,
			int addMoney, int addHealth, int playDays) {
		this.id = id;
		this.nickName = nickName;
		this.position = position;
		this.experience = experience;
		this.money = money;
		this.health = health;
		this.addExperience = addExperience;
		this.addMoney = addMoney;
		this.addHealth = addHealth;
		this.playDays = playDays;
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

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getAddExperience() {
		return addExperience;
	}

	public void setAddExperience(int addExperience) {
		this.addExperience = addExperience;
	}

	public int getAddMoney() {
		return addMoney;
	}

	public void setAddMoney(int addMoney) {
		this.addMoney = addMoney;
	}

	public int getAddHealth() {
		return addHealth;
	}

	public void setAddHealth(int addHealth) {
		this.addHealth = addHealth;
	}

	public int getPlayDays() {
		return playDays;
	}

	public void setPlayDays(int playDays) {
		this.playDays = playDays;
	}
	
	
	
}
