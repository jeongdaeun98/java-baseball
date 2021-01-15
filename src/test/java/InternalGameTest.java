import static org.junit.jupiter.api.Assertions.*;

import game.GameInfo;
import game.InternalGame;
import org.junit.jupiter.api.Test;

class InternalGameTest {

    @Test
    void getStrikeCount() {
        InternalGame internalGame = new InternalGame();
        assertEquals(internalGame.getStrikeCount(new GameInfo("123"), new GameInfo("123")), 3);
        assertEquals(internalGame.getStrikeCount(new GameInfo("123"), new GameInfo("423")), 2);
        assertEquals(internalGame.getStrikeCount(new GameInfo("123"), new GameInfo("453")), 1);
        assertEquals(internalGame.getStrikeCount(new GameInfo("123"), new GameInfo("456")), 0);
    }

    @Test
    void getBallCount() {
        InternalGame internalGame = new InternalGame();
        assertEquals(internalGame.getBallCount(new GameInfo("123"), new GameInfo("123")), 0);
        assertEquals(internalGame.getBallCount(new GameInfo("123"), new GameInfo("321")), 2);
        assertEquals(internalGame.getBallCount(new GameInfo("123"), new GameInfo("312")), 3);
        assertEquals(internalGame.getBallCount(new GameInfo("123"), new GameInfo("142")), 1);
        assertEquals(internalGame.getBallCount(new GameInfo("123"), new GameInfo("456")), 0);
    }

    @Test
    void getGameResultInfo() {
        InternalGame internalGame = new InternalGame();
        assertEquals(internalGame.getGameResultInfo(3, 0), new GameInfo("", false));
        assertEquals(internalGame.getGameResultInfo(2, 0), new GameInfo("", true));
        assertEquals(internalGame.getGameResultInfo(0, 0), new GameInfo("", true));
        assertEquals(internalGame.getGameResultInfo(2, 1), new GameInfo("", true));
        assertEquals(internalGame.getGameResultInfo(0, 1), new GameInfo("", true));
    }
}