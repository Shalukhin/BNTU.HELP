package command;

import static command.constant.GETCommandNameConstant.*;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import command.constant.GETCommandNameConstant;
import command.impl.get.AboutCommand;
import command.impl.get.AccountCommand;
import command.impl.get.AdministratorCommand;
import command.impl.get.ChemistryCommand;
import command.impl.get.ContactCommand;
import command.impl.get.EnginCommand;
import command.impl.get.ErrorCommand;
import command.impl.get.IndexCommand;
import command.impl.get.InformaticCommand;
import command.impl.get.MathCommand;
import command.impl.get.PayCommand;
import command.impl.get.SignCommand;
import command.impl.get.TestCommand;
import command.impl.get.ViewOrderCommand;
import command.impl.post.LogoutCommand;

public class CommandGETProvider {
	
	private static final Logger LOGGER = LogManager.getLogger(CommandGETProvider.class.getName());
	
	private Map<GETCommandNameConstant, CommandGET> repository = new HashMap<>();
	
	public CommandGETProvider() {
		repository.put(ADMINISTRATOR, new AdministratorCommand());
		repository.put(INDEX, new IndexCommand());
		repository.put(ABOUT, new AboutCommand());
		repository.put(CONTACT, new ContactCommand());
		repository.put(PAY, new PayCommand());
		repository.put(SIGN, new SignCommand());		
		repository.put(ACCOUNT, new AccountCommand());
		repository.put(VIEWORDER, new ViewOrderCommand());
		repository.put(CHEMISTRY, new ChemistryCommand());
		repository.put(MATH, new MathCommand());
		repository.put(ENGIN, new EnginCommand());
		repository.put(INFORMATIC, new InformaticCommand());
		repository.put(TEST, new TestCommand());
		repository.put(ERROR, new ErrorCommand());
	}
	
	public CommandGET getCommand(String name) {
		GETCommandNameConstant commandName = null;
		CommandGET command = null;
		try {
			commandName = GETCommandNameConstant.valueOf(name.toUpperCase());
			command = repository.get(commandName);
			
		}catch (Exception e){
			LOGGER.error("Error_bad_command");
			command = repository.get(ERROR);
		}
		return command;
	}
}
