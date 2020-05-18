package command;

import static command.constant.CommandNameConstant.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import command.constant.CommandNameConstant;
import command.impl.AboutCommand;
import command.impl.AccountCommand;
import command.impl.ChangeLanguageCommand;
import command.impl.ChemistryCommand;
import command.impl.ContactCommand;
import command.impl.EnginCommand;
import command.impl.ErrorCommand;
import command.impl.IndexCommand;
import command.impl.InformaticCommand;
import command.impl.LoginCommand;
import command.impl.LogoutCommand;
import command.impl.MathCommand;
import command.impl.OrderConfirmCommand;
import command.impl.OrderDeleteCommand;
import command.impl.OrderPayCommand;
import command.impl.OrderProcessCommand;
import command.impl.OrderCommand;
import command.impl.OrderCompleteCommand;
import command.impl.PayCommand;
import command.impl.PersonalDataCommand;
import command.impl.RegistrationCommand;
import command.impl.SignCommand;
import command.impl.TestCommand;
import command.impl.ViewOrderCommand;
import command.impl.administrator.AdministratorCommand;
import command.impl.administrator.ChangeTaskCommand;
import command.impl.administrator.ChangeUserCommand;

public class CommandProvider {
	
	private static final Logger LOGGER = LogManager.getLogger(CommandProvider.class.getName());
	
	private Map<CommandNameConstant, Command> repository = new HashMap<>();
	
	public CommandProvider() {
		repository.put(INDEX, new IndexCommand());
		repository.put(ABOUT, new AboutCommand());
		repository.put(CONTACT, new ContactCommand());
		repository.put(PAY, new PayCommand());
		repository.put(SIGN, new SignCommand());
		repository.put(LOGIN, new LoginCommand());
		repository.put(LOGOUT, new LogoutCommand());
		repository.put(REGISTRATION, new RegistrationCommand());
		repository.put(ADMINISTRATOR, new AdministratorCommand());
		repository.put(CHANGEUSER, new ChangeUserCommand());
		repository.put(CHANGETASK, new ChangeTaskCommand());
		repository.put(ACCOUNT, new AccountCommand());
		repository.put(PERSONAL, new PersonalDataCommand());
		repository.put(ORDER, new OrderCommand());
		repository.put(ORDERCONFIRM, new OrderConfirmCommand());
		repository.put(ORDERDELETE, new OrderDeleteCommand());
		repository.put(ORDERPROCESS, new OrderProcessCommand());
		repository.put(ORDERPAY, new OrderPayCommand());
		repository.put(ORDERCOMPLETE, new OrderCompleteCommand());
		repository.put(VIEWORDER, new ViewOrderCommand());
		repository.put(CHEMISTRY, new ChemistryCommand());
		repository.put(MATH, new MathCommand());
		repository.put(ENGIN, new EnginCommand());
		repository.put(INFORMATIC, new InformaticCommand());
		repository.put(CHANGELANGUAGE, new ChangeLanguageCommand());
		
		repository.put(TEST, new TestCommand());
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
