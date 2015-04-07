package drools.presentation.helpers.test;

import org.junit.Assert;
import org.testng.annotations.Test;

import drools.presentation.helpers.RuleExecutor;
import drools.presentation.helpers.RuleHelper;
import drools.presentation.models.User;

public class AgendaGroupRuleTest {

	@Test
	public void shouldExecuteRulesByGroup() throws Exception{
		User user = new User("Guilherme", 19);
		RuleExecutor executor2 = new RuleExecutor("authorize-rules-by-agenda-group", RuleHelper.loadRuleFromFile("src/main/resources/rules/authorizeByCountry.drl"));
		Assert.assertFalse(user.isAuthorized());
		System.out.println("Executing rule with USA group: ");
		executor2.execute(user, "USA");
		Assert.assertFalse(user.isAuthorized());
		
		System.out.println("\n\nExecuting rule with Brazil group: ");
		executor2.execute(user, "Brazil");
		Assert.assertTrue(user.isAuthorized());
	}
}
