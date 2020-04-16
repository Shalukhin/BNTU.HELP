package command;

import static command.constant.CommandNameConstant.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import command.constant.CommandNameConstant;
import command.impl.AccountCommand;
import command.impl.ChemistryCommand;
import command.impl.ContactCommand;
import command.impl.ErrorCommand;
import command.impl.IndexCommand;
import command.impl.LoginCommand;
import command.impl.LogoutCommand;
import command.impl.RegistrationCommand;
import command.impl.SignCommand;

public class CommandProvider {
	
	private static final Logger LOGGER = LogManager.getLogger(CommandProvider.class.getName());
	
	private Map<CommandNameConstant, Command> repository = new HashMap<>();
	
	public CommandProvider() {
		repository.put(INDEX, new IndexCommand());
		repository.put(SIGN, new SignCommand());
		repository.put(LOGIN, new LoginCommand());
		repository.put(LOGOUT, new LogoutCommand());
		repository.put(REGISTRATION, new RegistrationCommand());
		repository.put(ACCOUNT, new AccountCommand());
		repository.put(CONTACT, new ContactCommand());
		repository.put(CHEMISTRY, new ChemistryCommand());		
		
		repository.put(ERROR, new ErrorCommand());
	}
	
	public Command getCommand(String name) {
		CommandNameConstant commandName = null;
		Command command = null;
		try {
			commandName = CommandNameConstant.valueOf(name.toUpperCase());
			command = repository.get(commandName);
		}catch (Exception e){
			LOGGER.error("Error_bad_command");
			command = repository.get(ERROR);
		}
		return command;
	}
}
