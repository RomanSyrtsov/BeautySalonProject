package ua.kharkiv.rsyrtsov.controller.Commands;

import org.apache.log4j.Logger;
import ua.kharkiv.rsyrtsov.controller.Commands.AdminCommands.AdminChangeTimeSlotFormProcessCommand;
import ua.kharkiv.rsyrtsov.controller.Commands.AdminCommands.AdminChangeTimeSlotFormView;
import ua.kharkiv.rsyrtsov.controller.Commands.AdminCommands.AdminRecordsProcessCommand;
import ua.kharkiv.rsyrtsov.controller.Commands.AdminCommands.AdminRecordsViewCommand;
import ua.kharkiv.rsyrtsov.controller.Commands.LoginLogoutCommands.*;
import ua.kharkiv.rsyrtsov.controller.Commands.MasterCommands.MasterListProcessCommand;
import ua.kharkiv.rsyrtsov.controller.Commands.MasterCommands.MastersListViewCommand;
import ua.kharkiv.rsyrtsov.controller.Commands.RecordCommands.NewRecordCommand;
import ua.kharkiv.rsyrtsov.controller.Commands.RecordCommands.RecordMasterScheduleProcessCommand;
import ua.kharkiv.rsyrtsov.controller.Commands.RecordCommands.RecordViewCommand;
import ua.kharkiv.rsyrtsov.controller.Commands.ScheduleCommands.ScheduleChangeStatusCommand;
import ua.kharkiv.rsyrtsov.controller.Commands.ScheduleCommands.ScheduleViewCommand;
import ua.kharkiv.rsyrtsov.controller.Commands.ServicesCommands.ServicesListProcessCommand;
import ua.kharkiv.rsyrtsov.controller.Commands.ServicesCommands.ServicesListViewCommand;
import ua.kharkiv.rsyrtsov.controller.Commands.ServicesCommands.ServicesMakeRecordCommand;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static final Logger log = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {

        commands.put("/", new MainCommand());

        commands.put("app_localization", new AppLocalizationCommand());
        // client commands
        commands.put("services", new ServicesListViewCommand());
        commands.put("processservices", new ServicesListProcessCommand());
        commands.put("record_service", new ServicesMakeRecordCommand());

        commands.put("masters", new MastersListViewCommand());
        commands.put("processmasters", new MasterListProcessCommand());

        commands.put("login", new LoginViewCommand());
        commands.put("processlogin", new LoginProcessCommand());

        commands.put("logout", new LogoutCommand());

        commands.put("register", new RegisterViewCommand());
        commands.put("registerprocess", new RegisterProcessCommand());

        commands.put("record", new RecordViewCommand());
        commands.put("selectmaster", new RecordMasterScheduleProcessCommand());
        commands.put("makeRecord",new NewRecordCommand());

        commands.put("schedule", new ScheduleViewCommand());
        commands.put("changeStatus", new ScheduleChangeStatusCommand());

        commands.put("adminRecords", new AdminRecordsViewCommand());
        commands.put("changeTimeSlot", new AdminRecordsProcessCommand());
        commands.put("changeTimeSlotForm", new AdminChangeTimeSlotFormView());
        commands.put("changeTimeSlotFormProcess", new AdminChangeTimeSlotFormProcessCommand());
        commands.put("cancelStatus", new AdminRecordsProcessCommand());
        commands.put("submitPayment", new AdminRecordsProcessCommand());

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
