package Background;

import java.util.ArrayList;

public class User {
	private String user_name;
	private int play_times,max_score;
	private ArrayList<Integer> array = new ArrayList<Integer>();
//	初始化
	User(){
		play_times=0;
	}
	User(String name){
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
	public void once_play(int score) {
		play_times++;
		array.add(score);
		if (score>max_score) {
			max_score = score;
		}
	}
//	获取用户游戏次数
	public int get_play_times() {
		return play_times;
	}
//	获取用户最高分数
	public int get_max_score() {
		return max_score;
	}

}
