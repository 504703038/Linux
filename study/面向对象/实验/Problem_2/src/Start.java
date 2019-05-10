
import java.io.IOException;

import javax.swing.*;

import Backstage.Digital;
import Backstage.ScoreList;
import Backstage.User;
import Backstage.UserList;
import GUI.Game_interface;
import GUI.Main_interface;

public class Start extends Thread {

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
		while (true) {
//			System.out.print("开始前："+mainInterface.isLoged());
//			wait for login
			while (!mainInterface.isLoged()) {
				System.out.print("");
			}
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
				gameInterface.setGameLabel("请进行第" + (digi.get_try_times() + 1) + "次尝试：");
//				wait for input
				try {
					Start.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				while (!gameInterface.isInputed() && gameInterface.isVisible()) {
					System.out.print("");
				}
				if (!gameInterface.isVisible())
					break;
				int num = gameInterface.getInput();
				System.out.println(digi.check(num));
			}
			System.out.println("恭喜你猜对了，一共猜了" + digi.get_try_times() + "次，得分为：" + digi.get_score().get_score());
			gameInterface.close();
			mainInterface.setFrameViewable(true);
			user.once_play(digi.get_score());
			UserList.update_user_info(user);
			ScoreList.add_new_score(digi.get_score());
			try {
				UserList.ended();
				ScoreList.ended();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
