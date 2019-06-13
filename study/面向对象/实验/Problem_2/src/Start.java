
import java.awt.Font;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Backstage.Digital;
import Backstage.ScoreList;
import Backstage.User;
import Backstage.UserList;

import GUI.Game_interface;
import GUI.Main_interface;

public class Start extends Thread {

	public static void main(String[] args) {
		Main_interface mainInterface = new Main_interface();
		Game_interface gameInterface = new Game_interface();
		Thread mainInterface_Thread = new Thread(mainInterface);
		/* 加载用户列表和成绩列表 */
		try {
			ScoreList.init();
			UserList.init(ScoreList.get_scoArrayList());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		/* 加载进程 */
		mainInterface_Thread.start();
		while (true) {
//			System.out.print("开始前："+mainInterface.isLoged());

			/* wait for login */
			while (!mainInterface.isLoged()) {
				System.out.print("");
			}

			/* 获取用户名 */
			User user = UserList.get_user(mainInterface.get_userName());

			/* 切换界面并加载游戏进程 */
			mainInterface.setFrameViewable(false);
			Thread gameInterface_Thread = new Thread(gameInterface);
			gameInterface_Thread.start();

			/* 生成随机数 */
			Digital digi = new Digital(user.get_name());
			System.out.println(digi);
			/* 如果没有才对就继续 */
			while (!digi.is_finish()) {

				/* wait for input */
				gameInterface.setGameLabel("请进行第" + (digi.get_try_times() + 1) + "次尝试：");
				try {
					Start.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//				System.out.println("Testing print 1.");
				while (!gameInterface.isInputed() && gameInterface.isVisible()) {
					System.out.print("");
				}
//				System.out.println("Testing print 2.");

				/* 判断用户是否关掉了游戏界面 */
				if (!gameInterface.isVisible()) {
					gameInterface = null;
					break;
				}
//				System.out.println("Testing print 3.");

				/* 获取玩家输入并验证结果 */
				int num = gameInterface.getInput();
				String result = digi.check(num);
//				System.out.println(digi.check(num));
//				System.out.println("Testing print 4.");

				/* 返回玩家猜测结果 */
				if (!digi.is_finish()) {
					JLabel content = new JLabel("第" + (digi.get_try_times()) + "次尝试结果：" + result);
					content.setFont(new Font("宋体", Font.BOLD, 18));
					JOptionPane.showMessageDialog(null, content, "提示", 2, new ImageIcon("./img/False.png"));
				}
			}
//			System.out.println("Testing print 5.");

			/* 游戏结束，返回玩家成绩 */
			if (digi.is_finish()) {
				JLabel content = new JLabel(
						"恭喜你猜对了，一共猜了" + digi.get_try_times() + "次，得分为：" + digi.get_score().get_score());
				content.setFont(new Font("宋体", Font.BOLD, 18));
				JOptionPane.showMessageDialog(null, content, "提示", 2, new ImageIcon("./img/True.png"));
				/* 记录成绩 */
				user.once_play(digi.get_score());
				UserList.update_user_info(user);
				ScoreList.add_new_score(digi.get_score());
			} else {
				JLabel content = new JLabel("您未完成游戏，所以不记录成绩。");
				content.setFont(new Font("宋体", Font.BOLD, 18));
				JOptionPane.showMessageDialog(null, content, "提示", 2, new ImageIcon("./img/False.png"));
			}

			/* 切换界面 */
//			System.out.println("Testing print 6.");
			if (gameInterface != null)
				gameInterface.close();
//			System.out.println("Testing print 7.");
			mainInterface.setFrameViewable(true);

			/* 导出成绩 */
			try {
				UserList.ended();
				ScoreList.ended();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
