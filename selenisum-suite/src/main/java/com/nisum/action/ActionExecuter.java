package com.nisum.action;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.nisum.domain.PageElement;
import com.nisum.domain.Report;
import com.nisum.domain.TestCase;
import com.nisum.util.Option;

@Service
public class ActionExecuter {
	
	Map<String,AbstractAction>  elementActionMap; 
	
	public  ActionExecuter(){
		elementActionMap = new HashMap<String,AbstractAction>();
		elementActionMap.put(Option.button.toString(), new ButtonAction());
		elementActionMap.put(Option.OBJECT_FIND.toString(), new ObjectFindAction());
		elementActionMap.put(Option.text.toString(), new TextFieldAction());
		elementActionMap.put(Option.a.toString(), new AnchorAction());
		elementActionMap.put(Option.NAVIGATE.toString(), new NavigateAction());
		elementActionMap.put(Option.OTHER.toString(), new OtherAction());
	}
	
	public boolean execute(PageElement element,Option option,WebDriver driver,TestCase testCase) throws Exception{
		if(elementActionMap.isEmpty()){
			throw new Exception("ElementAction not intialies. Call build() method to setup");
		}
		
		/*LaunchAppAction luncahApp = new LaunchAppAction();
		luncahApp.execute(element, driver);
		*/
		if("input".equals(element.getPageElementType()) && 
				"text".equals(element.getPageElementAttributeType())){
			elementActionMap.get(element.getPageElementAttributeType()).execute(element,driver,testCase);
		}else if(elementActionMap.containsKey(element.getPageElementType())){
			elementActionMap.get(element.getPageElementType()).execute(element,driver,testCase);
		}
		
		
		return true;
	}

}
