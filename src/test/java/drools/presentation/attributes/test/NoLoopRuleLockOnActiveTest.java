package drools.presentation.attributes.test;

import junit.framework.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import drools.presentation.helpers.RuleExecutor;
import drools.presentation.helpers.RuleHelper;
import drools.presentation.models.RuleResource;
import drools.presentation.models.User;

public class NoLoopRuleLockOnActiveTest {
	
	private RuleExecutor executor;
	
	@Test
	public void executeNoLoop() throws Exception{
		RuleResource[] rules = RuleHelper.loadRulesFromFiles("src/main/resources/rules/attributes/no-loop-rules.drl");
		executor = new RuleExecutor("no-loop-rules",rules);
		
		User user = new User("Usuário 1", 27);
		executor.execute(user);
		Assert.assertTrue(user.isAuthorized());
	}
	
	@Test
	public void executeLockOnActive() throws Exception{
		RuleResource[] rules = RuleHelper.loadRulesFromFiles("src/main/resources/rules/attributes/lock-on-active.drl");
		executor = new RuleExecutor("no-loop-rules",rules);
		
		User user = new User("Usuário 1", 27);
		executor.execute(user);
		Assert.assertTrue(user.isAuthorized());
	}
}
