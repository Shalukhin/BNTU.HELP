package controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.command.Command;
import controller.command.CommandName;
import controller.command.impl.Account;
import controller.command.impl.Chemistry;
import controller.command.impl.Contact;
import controller.command.impl.Error;
import controller.command.impl.Index;
import controller.command.impl.Inorganic;
import controller.command.impl.Login;
import controller.command.impl.Logout;
import controller.command.impl.Registration;
import controller.command.impl.Sign;

import static controller.command.CommandName.*;

public class CommandProvider {
	
	private static final Logger LOGGER = LogManager.getLogger(CommandProvider.class.getName());
	
	private Map<CommandName, Command> repository = new HashMap<>();
	
	public CommandProvider() {
		repository.put(INDEX, new Index());
		repository.put(SIGN, new Sign());
		repository.put(LOGIN, new Login());
		repository.put(LOGOUT, new Logout());
		repository.put(REGISTRATION, new Registration());
		repository.put(ACCOUNT, new Account());
		repository.put(CONTACT, new Contact());
		repository.put(CHEMISTRY, new Chemistry());
		repository.put(INORGANIC, new Inorganic());
		
		
		repository.put(ERROR, new Error());
	}
	
	public Command getCommand(String name) {
		CommandName commandName = null;
		Command command = null;
		try {
			commandName = CommandName.valueOf(name.toUpperCase());
			command = repository.get(commandName);
		}catch (Exception e){
			LOGGER.error("Error_bad_command");
			command = repository.get(ERROR);
		}
		return command;
	}
}
