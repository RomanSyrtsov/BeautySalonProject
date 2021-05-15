package ua.kharkiv.rsyrtsov.controller.Commands;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static final Logger log = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        // common commands
        //commands.put("login", new LoginCommand());
        //commands.put("logout", new LogoutCommand());
        //commands.put("noCommand", new NoCommand());
        //commands.put("viewSettings", new ViewSettingsCommand());
        //commands.put("updateSettings", new UpdateSettingsCommand());
        commands.put("/", new MainCommand());
        // client commands
        commands.put("services", new ServicesListCommand());
        commands.put("masters", new MastersListCommand());
        commands.put("login", new LoginViewCommand());
        commands.put("processlogin", new LoginProcessCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("register", new RegisterViewCommand());
        commands.put("registerprocess", new RegisterProcessCommand());

        // admin commands
        //commands.put("listOrders", new ListOrdersCommand());

        log.debug("Command container was successfully initialized");
        log.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName
     *            Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            log.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }

}
