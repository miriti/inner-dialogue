/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.commands.Command;
import java.awt.Point;
import java.util.HashMap;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class CommandInterpreter {

    public static final HashMap<String, Integer> tokens = new HashMap<>();
    public static final HashMap<String, Integer> numerics = new HashMap<>();

    // Static initializations
    static {
        numerics.put("one", 1);
        numerics.put("two", 2);
        numerics.put("three", 3);
        numerics.put("four", 4);
        numerics.put("five", 5);
        numerics.put("six", 6);
        numerics.put("seven", 7);
        numerics.put("eight", 8);
        numerics.put("nine", 9);
        numerics.put("ten", 10);
        numerics.put("eleven", 11);
        numerics.put("twelve", 12);
        numerics.put("thirteen", 13);
        numerics.put("fourteen", 14);
        numerics.put("fifteen", 15);
        numerics.put("sixteen", 16);
        numerics.put("seventeen", 17);
        numerics.put("eighteen", 18);
        numerics.put("nineteen", 19);
        numerics.put("twenty", 20);

        tokens.put("to", Command.T_UNINFORMATIVE);
        tokens.put("the", Command.T_UNINFORMATIVE);

        tokens.put("go", Command.T_MOVE_CMD);
        tokens.put("run", Command.T_MOVE_CMD);
        tokens.put("move", Command.T_MOVE_CMD);
        tokens.put("walk", Command.T_MOVE_CMD);

        tokens.put("step", Command.T_MOVE_STEP_CMD);
        tokens.put("steps", Command.T_MOVE_STEP_CMD);

        tokens.put("nearest", Command.T_MOVE_PROP);
        tokens.put("next", Command.T_MOVE_PROP);
        tokens.put("previous", Command.T_MOVE_PROP);

        tokens.put("east", Command.T_MOVE_DIRECTION);
        tokens.put("west", Command.T_MOVE_DIRECTION);
        tokens.put("south", Command.T_MOVE_DIRECTION);
        tokens.put("north", Command.T_MOVE_DIRECTION);

        tokens.put("hi", Command.T_GREETING);
        tokens.put("hello", Command.T_GREETING);
        tokens.put("hey", Command.T_GREETING);

        tokens.put("help", Command.T_HELP);

        tokens.put("stop", Command.T_BREAK);
        tokens.put("break", Command.T_BREAK);

        tokens.put("exit", Command.T_QUIT);
        tokens.put("quit", Command.T_QUIT);

        tokens.put("force", Command.T_FORCE);
        tokens.put("totaly", Command.T_FORCE);
    }

    public static int defineToken(String tokenString) {
        if (tokens.containsKey(tokenString)) {
            return tokens.get(tokenString);
        } else {
            if (numerics.containsKey(tokenString)) {
                return Command.T_NUMERIC;
            } else {
                if (isNumeric(tokenString)) {
                    return Command.T_NUMERIC;
                } else {
                    return Command.T_UNKNOWN;
                }
            }
        }
    }

    public static void exec(String cmd) {

        String[] cmdSplit = cmd.split(" ");
        int[] cmdTokens = new int[cmdSplit.length];

        for (int i = 0; i < cmdTokens.length; i++) {
            cmdTokens[i] = defineToken(cmdSplit[i]);
        }

        if (!Command.e(cmdTokens, cmdSplit)) {
            cmdError("That's doesn't make any sens...");
        }
    }

    public static Point translateDirection(String dir) {
        if (defineToken(dir) == Command.T_MOVE_DIRECTION) {
            Point pDir = new Point();

            switch (dir) {
                case "east":
                    pDir.x = 1;
                    break;
                case "west":
                    pDir.x = -1;
                    break;
                case "south":
                    pDir.y = 1;
                    break;
                case "north":
                    pDir.y = -1;
                    break;
            }

            return pDir;
        } else {
            return null;
        }
    }

    private static void cmdError(String errorTxt) {
        Player.getInstance().say(errorTxt);
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static int numeric(String num) {
        if (numerics.containsKey(num)) {
            return numerics.get(num);
        }
        return -1;
    }

    public static int numericToInt(String num) {
        int ret;

        try {
            ret = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            ret = numeric(num);
        }

        return ret;
    }
}
