package com.jpongsick.game.Util;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Szymon on 29.01.2017.
 */
public class BotUtils {
    public enum BotNickname {
        Albert, Allen, Bert, Bob, Cecil,
        Clarence, Elliot, Elmer, Ernie,
        Eugene, Fergus, Ferris, Frank,
        Frasier, Fred, George, Graham,
        Harvey, Irwin, Larry, Lester,
        Marvin, Neil, Niles, Oliver,
        Opie, Ryan, Toby, Ulric, Ulysses,
        Uri, Waldo, Wally, Walt, Wesley,
        Yanni, Yogi, Yuri
    }

    public enum Difficulty {
            EASY, MEDIUM, HARD
    }

    private static final List<BotNickname> VALUES =
            Collections.unmodifiableList(Arrays.asList(BotNickname.values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static BotNickname randomBotNickname()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public static String randomNickname(){
        return "Bot " + randomBotNickname().toString();
    }

}
