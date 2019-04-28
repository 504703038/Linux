package backstage;

public class Score {
	private int score;
	private  String user_name;
	
	public Score() {
		user_name="";
		score=0;
	}
	public Score(String name,int s) {
		user_name=name;
		score=s;
	}
	//	修改用户名
	public void set_username(String name) {
		user_name=name;
	}
//	修改分数
	public void set_score(int s) {
		score=s;
	}
//	获取用户名
	public String get_username() {
		return user_name;
	}
//	获取分数
	public int get_score() {
		return score;
	}
}
