package foodvote;

public class FoodVoteDTO {
	private int num;
	private String name;
	private int vote;
	
	public FoodVoteDTO() {
		
	}
	
	public FoodVoteDTO(int num, String name, int vote) {
		this.num = num;
		this.name = name;
		this.vote = vote;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	@Override
	public String toString() {
		return "FoodVoteDTO [num=" + num + ", name=" + name + ", vote=" + vote + "]";
	}
	
	
}
