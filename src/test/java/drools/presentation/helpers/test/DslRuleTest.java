package drools.presentation.helpers.test;

import org.junit.Assert;
import org.testng.annotations.Test;

import drools.presentation.helpers.RuleExecutor;
import drools.presentation.helpers.RuleHelper;
import drools.presentation.models.RuleResource;
import drools.presentation.models.User;

public class DslRuleTest {

	@Test
	public void shouldCallRuleWithDSL() throws Exception{
		RuleResource[] rules = RuleHelper.loadRulesFromFiles("src/main/resources/rules/authorize-functions.drl", "src/main/resources/rules/authorize.dsl","src/main/resources/rules/authorize.dslr");
		User user = new User("Guilherme", 27);
		RuleExecutor executor2 = new RuleExecutor("authorize-rules-dsl", rules);
		Assert.assertFalse(user.isAuthorized());
		executor2.execute(user, "Brazil");
		Assert.assertTrue(user.isAuthorized());
	}
}
