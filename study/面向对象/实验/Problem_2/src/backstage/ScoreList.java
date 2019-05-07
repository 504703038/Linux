package backstage;

import java.io.*;
import java.util.*;

public class ScoreList {
	private ArrayList<Score> scoreList = new ArrayList<Score>();
	public void init() throws NumberFormatException, IOException {
		File ScoreCsv = new File("./data/score.csv");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(ScoreCsv));
		} catch (FileNotFoundException e) {return;}
		String line ="";
		while ((line = reader.readLine()) != null) {
			StringTokenizer in = new StringTokenizer(line,",");
			Score score = new Score();
			score.set_username(in.nextToken());
			score.set_score(Integer.parseInt(in.nextToken()));
			scoreList.add(score);
		}
		reader.close();
	}
	
//	添加一个新成绩
	public void add_new_score(Score score) {
		scoreList.add(score);
	}
	
//	返回按成绩排序的列表
	public ArrayList<Score> get_scoArrayList(){
		Comparator<Score> cmp = new Comparator<Score>() {
			public int compare(Score arg0, Score arg1) {
				if (arg0.get_score()>arg1.get_score()) return 1;
				else return -1;
			}
		};
		scoreList.sort(cmp);
		return scoreList;
	}
	public void ended() throws IOException {
		File ScoreCsv = new File("./data/score.csv");
		BufferedWriter writer = new BufferedWriter(new FileWriter(ScoreCsv));
		for (int i = 0; i < scoreList.size(); i++) {
			Score score = scoreList.get(i);
			writer.write(score.get_username() + "," + score.get_score());
			writer.newLine();
		}
		writer.close();
	}
}
