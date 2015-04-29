package drools.presentation.attributes.test;

import junit.framework.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import drools.presentation.helpers.RuleExecutor;
import drools.presentation.helpers.RuleHelper;
import drools.presentation.models.RuleResource;
import drools.presentation.models.User;

public class TimerRulesTest {

	private RuleExecutor executor;
	
	@BeforeClass
	public void loadRules() throws Exception{
		RuleResource[] rules = RuleHelper.loadRulesFromFiles("src/main/resources/rules/attributes/timer-rules.drl");
		executor = new RuleExecutor("no-loop-rules",rules);
	}
	
	@Test
	public void shouldExecuteRuleByTimer() throws Exception{
		User user = new User("Usuário 1", 27);
		executor.execute(user);
		
		int count = 60;
		while(count>0){
			count--;
			System.out.println("Sleeping...");
			Thread.sleep(1000);
		}
	}
}
