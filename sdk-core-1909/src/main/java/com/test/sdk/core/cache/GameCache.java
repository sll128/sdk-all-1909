package com.test.sdk.core.cache;

import com.test.sdk.common.pojo.Game;
import com.test.sdk.core.dao.GameDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 徒有琴
 */
@Component
public class GameCache extends AbstractCache {
    private static Map<String, Game> gameMap = new HashMap<>();

    @Autowired
    private GameDAO gameDAO;

    @Override
    public void update() {
        System.out.println("===========GameCache============");
        List<Game> games = gameDAO.getAllGameList();
        for (Game game : games) {
            gameMap.put(game.getCpid() + "-" + game.getSeqNum(), game);
        }
        System.out.println(gameMap);
    }

    public static Game getGameByCpidAndSeqnum(Integer cpid, Integer seqnum) {
        return gameMap.get(cpid + "-" + seqnum);
    }


}
