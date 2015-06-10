package controller.commands;

import javax.servlet.http.HttpServletRequest;


/**
 * The factory of commands.
 */
public class CommandFactory {
    
    
    public static final String JOIN = "join";
    public static final String REGISTRATION = "regist";
    
    
    /**
     * Get Command by input request
     * @param req input request
     * @return command
     */
    public Command getCommand(HttpServletRequest req) {
        Command command = null;
        
        String actionJoin = req.getParameter(CommandFactory.JOIN);
        String actionRegister = req.getParameter(CommandFactory.REGISTRATION);
        
        if(actionJoin != null) {
            EnumCommands commands = EnumCommands.valueOf(actionJoin.toUpperCase());
            command = commands.getCurrentCommand();
        } else if (actionRegister != null){
            EnumCommands commands = EnumCommands.valueOf(actionRegister.toUpperCase());
            command = commands.getCurrentCommand();
        }
        return command;
    }
}
