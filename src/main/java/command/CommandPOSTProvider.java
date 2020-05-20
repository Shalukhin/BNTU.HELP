package command;


import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static command.constant.POSTCommandNameConstant.*;
import command.constant.POSTCommandNameConstant;
import command.impl.post.ChangeLanguageCommand;
import command.impl.post.ChangeTaskCommand;
import command.impl.post.ChangeUserCommand;
import command.impl.post.ErrorPOSTCommand;
import command.impl.post.LoginCommand;
import command.impl.post.LogoutCommand;
import command.impl.post.OrderCommand;
import command.impl.post.OrderCompleteCommand;
import command.impl.post.OrderConfirmCommand;
import command.impl.post.OrderDeleteCommand;
import command.impl.post.OrderPayCommand;
import command.impl.post.OrderProcessCommand;
import command.impl.post.PersonalDataCommand;
import command.impl.post.PrepareOrderCommand;
import command.impl.post.RegistrationCommand;

public class CommandPOSTProvider {

	private static final Logger LOGGER = LogManager.getLogger(CommandPOSTProvider.class.getName());

	private Map<POSTCommandNameConstant, CommandPOST> repository = new HashMap<>();
	
	public CommandPOSTProvider() {
		repository.put(LOGIN, new LoginCommand());
		repository.put(REGISTRATION, new RegistrationCommand());
		repository.put(PREPAREORDER, new PrepareOrderCommand());
		repository.put(ORDER, new OrderCommand());
		repository.put(ORDERPROCESS, new OrderProcessCommand());
		repository.put(ORDERCONFIRM, new OrderConfirmCommand());
		repository.put(ORDERPAY, new OrderPayCommand());
		repository.put(ORDERCOMPLETE, new OrderCompleteCommand());
		repository.put(ORDERDELETE, new OrderDeleteCommand());
		repository.put(PERSONAL, new PersonalDataCommand());
		repository.put(CHANGEUSER, new ChangeUserCommand());
		repository.put(CHANGETASK, new ChangeTaskCommand());
		repository.put(CHANGELANGUAGE, new ChangeLanguageCommand());
		repository.put(LOGOUT, new LogoutCommand());
		repository.put(ERRORPOST, new ErrorPOSTCommand());				
	}
	
	public CommandPOST getCommand(String name) {
		POSTCommandNameConstant commandName = null;
		CommandPOST command = null;
		try {
			commandName = POSTCommandNameConstant.valueOf(name.toUpperCase());
			command = repository.get(commandName);
		}catch (Exception e){
			LOGGER.error("Error_bad_command");
			command = repository.get(ERRORPOST);
		}
		return command;
	}

}
