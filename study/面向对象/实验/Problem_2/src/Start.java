
import backstage.*;
import gui.*;

import java.io.*;
import java.util.*;



public class Start {
	
	static UserList userList = new UserList();
	static ScoreList scoreList = new ScoreList();	
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		userList.init();
		scoreList.init();
//		System.out.println(userList.get_userList());
//		System.out.println(scoreList.get_scoArrayList());
		for(int i=1;i<=3;i++)
		{
			System.out.print("请输入用户名：");
			String name = in.next();
			User user = userList.get_user(name);
			System.out.println(user.get_max_score()+"   "+user.get_play_times());
			Digital digi = new Digital(user.get_name());
			while (!digi.is_finish()) {
				System.out.print("请进行第"+(digi.get_try_times()+1)+"次尝试：");
				int num = in.nextInt();
				System.out.println(digi.check(num));
			}
			System.out.println("恭喜你猜对了，一共猜了"+digi.get_try_times()+"次，得分为："+digi.get_score().get_score());
			user.once_play(digi.get_score());
			userList.update_user_info(user);
			scoreList.add_new_score(digi.get_score());
		}
		
		
		userList.ended();
		scoreList.ended();
	}

}
