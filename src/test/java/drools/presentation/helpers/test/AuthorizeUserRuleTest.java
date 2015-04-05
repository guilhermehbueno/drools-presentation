package drools.presentation.helpers.test;

import org.junit.Assert;
import org.testng.annotations.Test;

import drools.presentation.helpers.RuleExecutor;
import drools.presentation.helpers.RuleHelper;
import drools.presentation.models.User;

public class AuthorizeUserRuleTest {
	
	@Test
	public void shouldAuthorizeIfUserIsMoreThan18() throws Exception{
		User user = new User("Guilherme", 27);
		RuleExecutor executor2 = new RuleExecutor("authorize-rules", RuleHelper.loadRuleFromFile("authorizeUser.drl"));
		Assert.assertFalse(user.isAuthorized());
		executor2.execute(user);
		Assert.assertTrue(user.isAuthorized());
	}
	
	
	@Test
	public void shouldNotAuthorizeIfUserIsLessThan18() throws Exception{
		User user = new User("Guilherme", 17);
		RuleExecutor executor2 = new RuleExecutor("authorize-rules", RuleHelper.loadRuleFromFile("authorizeUser.drl"));
		Assert.assertFalse(user.isAuthorized());
		executor2.execute(user);
		Assert.assertFalse(user.isAuthorized());
	}

}
