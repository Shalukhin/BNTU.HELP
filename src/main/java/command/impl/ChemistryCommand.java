package command.impl;

import javax.servlet.http.HttpServletRequest;

import command.Command;
import command.PageManager;
import util.Parser;

import static command.constant.AttributeNameConstant.*;
import static command.constant.AttributeValueConstant.*;
import static command.constant.ParameterNameConstant.*;

public class ChemistryCommand implements Command{

	@Override
	public PageManager execute(HttpServletRequest request) {
		boolean isTextbookPage = Boolean.valueOf(Parser.getStringParameterByName(request, TEXTBOOK));
		String kindOfChemistry = Parser.getStringParameterByName(request, KIND_OF_CHEMISTRY);
		
		if (kindOfChemistry == null) {
			return PageManager.ERROR_PAGE;
		}
		
		request.setAttribute(CHEMISTRY_MAIN_MENU_POSITION, ACTIVE);		//for indicate main menu current item		
				
		if (!isTextbookPage) {
			request.setAttribute(kindOfChemistry, ACTIVE);					//for indicate current tab
			return PageManager.CHEMISTRY_PAGE;
		}		
		
		PageManager resultPage;
		try {
			resultPage = PageManager.valueOf(kindOfChemistry.toUpperCase().concat("_PAGE"));
		} catch (IllegalArgumentException e) {
			resultPage = PageManager.ERROR_PAGE;
		}
		
		return resultPage;	
		
	}
}
