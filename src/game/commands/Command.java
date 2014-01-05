/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.commands;

import game.CommandPattern;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class Command {

    public static final int T_UNKNOWN = 0;
    public static final int T_UNINFORMATIVE = 1;
    public static final int T_NUMERIC = 2;
    public static final int T_MOVE_CMD = 3;
    public static final int T_MOVE_STEP_CMD = 4;
    public static final int T_MOVE_PROP = 5;
    public static final int T_MOVE_TARGET = 6;
    public static final int T_MOVE_DIRECTION = 7;
    public static final int T_GREETING = 8;
    public static final int T_HELP = 9;
    public static final int T_BREAK = 10;
    public static final int T_QUIT = 11;
    public static final int T_FORCE = 12;
    
    private static final CommandPattern[] patterns;

    static {
        CommandMove moveCommand = new CommandMove();
        Command quitCommand = new CommandQuit();

        patterns = new CommandPattern[]{
            new CommandPattern(new int[]{T_MOVE_CMD, T_MOVE_DIRECTION}, moveCommand),
            new CommandPattern(new int[]{T_MOVE_STEP_CMD, T_MOVE_DIRECTION}, moveCommand),
            new CommandPattern(new int[]{T_NUMERIC, T_MOVE_STEP_CMD, T_MOVE_DIRECTION}, moveCommand),
            new CommandPattern(new int[]{T_FORCE, T_QUIT}, quitCommand),
            new CommandPattern(new int[]{T_QUIT}, quitCommand)
        };
    }

    public static boolean compirePatterns(int[] ptr1, int[] ptr2) {
        if (ptr1.length == ptr2.length) {
            for (int i = 0; i < ptr1.length; i++) {
                if (ptr1[i] != ptr2[i]) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean e(int[] tokens, String[] words) {
        for (int i = 0; i < patterns.length; i++) {
            if (compirePatterns(tokens, patterns[i].getPattern())) {
                patterns[i].getCommand().execute(tokens, words);
                return true;
            }
        }
        return false;
    }

    public void execute(int[] tokens, String[] words) {
    }
}
