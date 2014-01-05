/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.commands.Command;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class CommandPattern {

    private int[] pattern;
    private Command command;

    public CommandPattern(int[] pattern, Command command) {
        this.pattern = pattern;
        this.command = command;
    }

    public int[] getPattern() {
        return pattern;
    }

    public Command getCommand() {
        return command;
    }        
}
