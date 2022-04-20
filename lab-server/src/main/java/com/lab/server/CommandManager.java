package com.lab.server;

import com.lab.common.util.FileManager;
import com.lab.common.util.Request;
import com.lab.common.util.Response;
import com.lab.server.Commands.AddCommand;
import com.lab.server.Commands.AddIfMinCommand;
import com.lab.server.Commands.ClearCommand;
import com.lab.server.Commands.AbstractCommand;
import com.lab.server.Commands.CountGreaterThanDistanceCommand;
import com.lab.server.Commands.CountLessThanDistanceCommand;
import com.lab.server.Commands.ExitCommand;
import com.lab.server.Commands.HelpCommand;
import com.lab.server.Commands.InfoCommand;
import com.lab.server.Commands.MaxByDistanceCommand;
import com.lab.server.Commands.RemoveByIdCommand;
import com.lab.server.Commands.RemoveGreaterCommand;
import com.lab.server.Commands.RemoveLowerCommand;
import com.lab.server.Commands.SaveCommand;
import com.lab.server.Commands.ShowCommand;
import com.lab.server.Commands.UpdateCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that execute user commands
 */
public class CommandManager {
    private static Map<String, AbstractCommand> commands;

    public CommandManager(FileManager fileManager, CollectionManager collectionManager) {
        commands = new HashMap<>();
        commands.put("add", new AddCommand(collectionManager));
        commands.put("add_if_min", new AddIfMinCommand(collectionManager));
        commands.put("clear", new ClearCommand(collectionManager));
        commands.put("count_greater_than_distance", new CountGreaterThanDistanceCommand(collectionManager));
        commands.put("count_less_than_distance", new CountLessThanDistanceCommand(collectionManager));
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand(collectionManager));
        commands.put("max_by_distance", new MaxByDistanceCommand(collectionManager));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commands.put("remove_greater", new RemoveGreaterCommand(collectionManager));
        commands.put("remove_lower", new RemoveLowerCommand(collectionManager));
        commands.put("save", new SaveCommand(collectionManager, fileManager));
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("update", new UpdateCommand(collectionManager));
        commands.put("exit", new ExitCommand());
    }

    public static Map<String, AbstractCommand> getCommands() {
        return commands;
    }

    public Response executeCommand(Request request) {
        String commandName = request.getCommandName();
        AbstractCommand command = commands.get(commandName);
        return command.execute(request);
    }
}
