
import java.io.IOException;

import javax.swing.*;

public class Main extends Thread {
	
//	static UserList userList = new UserList();
//	static ScoreList scoreList = new ScoreList();
	
	public static void main(String[] args) {
		try {
			ScoreList.init();
			UserList.init(ScoreList.get_scoArrayList());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		Main_interface mainInterface = new Main_interface();
		Game_interface gameInterface = new Game_interface();
		Thread mainInterface_Thread = new Thread(mainInterface);
		
		mainInterface_Thread.start();
		while(mainInterface_Thread.isAlive())
		{
//			wait for login
			while (!mainInterface.isLoged()) {System.out.print("");}
//			if (!mainInterface_Thread.isAlive()) break;
//			获取用户名
			User user = UserList.get_user(mainInterface.get_userName());
//			切换界面
			mainInterface.setFrameViewable(false);
			Thread gameInterface_Thread = new Thread(gameInterface);
			gameInterface_Thread.start();
			
			Digital digi = new Digital(user.get_name());
			System.out.println(digi);
			while (!digi.is_finish()) {
				gameInterface.setGameLabel("请进行第"+(digi.get_try_times()+1)+"次尝试：");
//				wait for input
				while (!gameInterface.isInputed()) {System.out.print("");}
//				if (!gameInterface_Thread.isAlive()) break;
				int num = gameInterface.getInput();
				System.out.println(digi.check(num));
			}
			System.out.println("恭喜你猜对了，一共猜了"+digi.get_try_times()+"次，得分为："+digi.get_score().get_score());
			user.once_play(digi.get_score());
			UserList.update_user_info(user);
			ScoreList.add_new_score(digi.get_score());
		}
		try {
			UserList.ended();
			ScoreList.ended();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
