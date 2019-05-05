package backstage;

import java.util.ArrayList;

public class User {
	private String user_name;
	private int play_times,max_score;
	private ArrayList<Integer> history_score = new ArrayList<Integer>();
//	初始化
	public User(){
		play_times=0;
	}
	public User(String name){
		user_name=name;
		play_times=0;
	}
//	设置用户名称
	public void set_name(String name) {
		user_name=name;
	}
//	获取用户名称
	public String get_name() {
		return user_name;
	}
//	上传用户分数
	public void once_play(Score score) {
		play_times++;
		history_score.add(score.get_score());
		if (score.get_score()>max_score) {
			max_score = score.get_score();
		}
	}
//	修改游戏次数
	public void set_play_times(int times) {
		play_times=times;
	}
//	获取用户游戏次数
	public int get_play_times() {
		return play_times;
	}
//	修改最高分
	public void set_max_score(int score) {
		max_score=score;
	}
//	获取用户最高分数
	public int get_max_score() {
		return max_score;
	}
//	上传历史成绩
	public void up_score(int score) {
		history_score.add(score);
	}
//	获取用户历史成绩
	public ArrayList<Integer> get_history_score() {
		return history_score;
	}
}
