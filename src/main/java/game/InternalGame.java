package game;

import java.util.HashMap;

public class InternalGame {

    public int getStrikeCount(GameInfo realGameInfo, GameInfo userGameInfo) {
        String realNumber = realGameInfo.getNumber();
        String userNumber = userGameInfo.getNumber();
        int strikeCount = 0;
        for (int i = 0; i < realNumber.length(); i++) {
            if (realNumber.charAt(i) == userNumber.charAt(i)) {
                strikeCount++;
            }
        }
        return strikeCount;
    }

    public int getBallCount(GameInfo realGameInfo, GameInfo userGameInfo) {
        String realBall = realGameInfo.getNumber();
        String userBall = userGameInfo.getNumber();
        HashMap<Character, Integer> ballHashMap = new HashMap<>();
        int ballCount = 0;
        for (int i = 0; i < realBall.length(); i++) {
            ballHashMap.put(realBall.charAt(i), i);
        }
        for (int i = 0; i < userBall.length(); i++) {
            if (ballHashMap.containsKey(userBall.charAt(i)) && i != ballHashMap
                .get(userBall.charAt(i))) {
                ballCount++;
            }
        }
        return ballCount;
    }

    //TODO(daeun): 좀 더 나은 방식으로 할 수 없을지 고민
    public GameInfo getGameResultInfo(int strikeCount, int ballCount) {
        GameInfo gameInfo = new GameInfo();
        if (strikeCount == 3) {
            System.out.println(ConstantString.THREE_STRIKE);
            gameInfo.setGameResumption(false);
            return gameInfo;
        }
        System.out.println(Unit.printUnit(strikeCount, ballCount));
        return gameInfo;
    }

    public GameInfo getRealRandomNumInfo() {
        String ball = "";
        HashMap<Integer, Boolean> ballHashMap = new HashMap<>();
        while (ball.length() != 3) {
            double randomValue = Math.random();
            int num = (int) (randomValue * 9) + 1;
            if (ballHashMap.containsKey(num)) {
                continue;
            } else {
                ballHashMap.put(num, true);
                ball += Integer.toString(num);
            }
        }
        GameInfo gameInfo = new GameInfo(ball);
        return gameInfo;
    }

    public GameInfo startBaseBallGame(GameInfo realGameInfo) {
        RequestUser requestUser = new RequestUser();
        GameInfo userGameInfo = requestUser.askUserForSuitableNum();
        int strikeCount = getStrikeCount(realGameInfo, userGameInfo);
        int ballCount = getBallCount(realGameInfo, userGameInfo);
        GameInfo gameResultInfo = getGameResultInfo(strikeCount, ballCount);
        return gameResultInfo;
    }

    public Boolean isGameRenew(String userAnswer) {
        if (userAnswer.equals("1")) {
            return true;
        }
        return false;
    }
}