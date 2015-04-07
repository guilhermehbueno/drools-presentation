package drools.presentation.helpers.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import drools.presentation.helpers.RuleExecutor;
import drools.presentation.helpers.RuleHelper;
import drools.presentation.models.RuleResource;
import drools.presentation.models.User;

public class HelloWorldRuleTest {
	
	private RuleExecutor executor;

	@BeforeClass
	public void loadRules() throws Exception{
		RuleResource[] rules = RuleHelper.loadRulesFromFiles("src/main/resources/rules/helloWorld.drl", "src/main/resources/rules/helloWorldWithUser.drl");
		executor = new RuleExecutor("hello-world-rules",rules);
	}
	
	@Test
	public void shouldExecuteHelloWorld() throws Exception{
		executor.execute(new Object());
	}
	
	@Test
	public void shouldExecuteHelloWorldForUser() throws Exception{
		User user = new User("Usuário 1", 27);
		executor.execute(user);
		
		user = new User("Guilherme", 27);
		executor.execute(user);
	}
}
