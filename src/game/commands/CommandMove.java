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
public class CommandMove extends Command {

    @Override
    public void execute(int[] tokens, String[] words) {
        Player p = Player.getInstance();
        //p.cmdMove(words[0], words[1], null);
        p.move(tokens, words);
    }
}
