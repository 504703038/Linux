package Backstage;


import java.io.*;
import java.util.*;

public class UserList {
	private static ArrayList<User> userList = new ArrayList<User>();
//	读入所有用户信息
	public static void init(ArrayList<Score> scoreList) throws NumberFormatException, IOException {
		File UserCsv = new File("./data/user.csv");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(UserCsv));
		} catch (FileNotFoundException e) {return;}
		String line ="";
		while ((line = reader.readLine()) != null) {
			StringTokenizer in = new StringTokenizer(line,",");
			User user = new User();
			user.set_name(in.nextToken());
			user.set_play_times(Integer.parseInt(in.nextToken()));
			user.set_max_score(Integer.parseInt(in.nextToken()));
			userList.add(user);
		}
		reader.close();
		for (int i=0;i<scoreList.size();++i) {
			Score score=scoreList.get(i);
			User user = get_user(score.get_username());
			user.up_score(score.get_score());
			update_user_info(user);
		}	
	}
//	添加一个新用户
	private static User add_new_user(String name) {
		User user = new User();
		user.set_name(name);
		user.set_max_score(0);
		user.set_play_times(0);
		userList.add(user);
		return user;
	}
//	通过用户名查找并返回用户，若没有则添加一个新用户。
	public static User get_user(String name) {
		User user=null;
		for(int i=0;i<userList.size();i++)
		{
			if(userList.get(i).get_name().equals(name)) {
				user =userList.get(i);
				break;
			}
		}
		if (user == null) return add_new_user(name);
		else return user;
	}
//	通过用户名更新用户成绩
	public static void update_user_info(User user) {
		for (int i = 0; i < userList.size(); i++) {
			if(userList.get(i).get_name().equals(user.get_name())) {
				userList.set(i, user);
				break;
			}
		}
	}
//	返回按照用户最高成绩排序后的列表
	public static ArrayList<User> get_userList(){
		Comparator<User> cmp = new Comparator<User>() {
			public int compare(User arg0, User arg1) {
				if (arg0.get_max_score()==arg1.get_max_score()) {
					if (arg0.get_play_times()>arg1.get_play_times())
						return 1;
					else return -1;
				}else {
					if (arg0.get_max_score()<arg1.get_max_score()) return 1;
					else return -1;
				}
			}
		};
		userList.sort(cmp);
		return userList;
	}
//	将用户所有信息以文件形式保存本地
	public static void ended() throws IOException {
		File UserCsv = new File("./data/user.csv");
		BufferedWriter writer = new BufferedWriter(new FileWriter(UserCsv));
		for (int i = 0; i < userList.size(); i++) {
			User user = userList.get(i);
			writer.write(user.get_name()+"," + user.get_play_times() + "," + user.get_max_score());
			writer.newLine();
		}
		writer.close();
	}
	
}
