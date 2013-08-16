package demostration;

import java.util.ArrayList;

public class Gender {
public static void main(String[] args) {
//	ArrayList openingGameList = new ArrayList();
//	Object a = new Object();
//	
//	openingGameList.add("ggd");
//	openingGameList.remove(0);
//	boolean hasOpenGame = (openingGameList.size()!=0);
//	
//	System.out.println(hasOpenGame);
	System.out.println(getScoreByVotes(3, 6, 3)); 
}

public static float getScoreByVotes(int goodVote, int normVote, int badVote) {
	float score = 0.0f;
	int totalVote = goodVote + normVote + badVote;
	if (totalVote >= 10) {
		float dividend = goodVote +(float) normVote / 2;
		float divisor = totalVote;
		System.out.println(dividend);
		System.out.println(divisor); 
		score = dividend / divisor * 10;
	}
	return score;
}
}
