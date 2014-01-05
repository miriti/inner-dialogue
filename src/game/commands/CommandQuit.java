/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.commands;

import game.Player;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class CommandQuit extends Command {

    private static int cnt = 0;

    @Override
    public void execute(int[] tokens, String[] words) {
        if ((cnt == 1) || (tokens[0] == T_FORCE)) {
            System.exit(0);
        } else {
            Player.getInstance().say("Quit game? Next time you say \"" + words[0] + "\" game will be closed!");
            cnt++;
        }
    }
}
