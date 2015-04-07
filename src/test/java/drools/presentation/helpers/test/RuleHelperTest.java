package drools.presentation.helpers.test;

import java.io.FileNotFoundException;

import org.kie.api.io.ResourceType;
import org.testng.Assert;
import org.testng.annotations.Test;

import drools.presentation.helpers.RuleHelper;
import drools.presentation.models.RuleResource;

public class RuleHelperTest {
	
	@Test
	public void shouldLoadRules() throws Exception{
		RuleResource[] loadRulesFromFiles = RuleHelper.loadRulesFromFiles("src/main/resources/rules/helloWorld.drl", "src/main/resources/rules/helloWorldWithUser.drl");
		Assert.assertNotNull(loadRulesFromFiles);
		Assert.assertTrue(loadRulesFromFiles.length == 2);
	}
	
	@Test
	public void shouldLoadRuleProperly() throws Exception{
		RuleResource ruleResource = RuleHelper.loadRuleFromFile("src/main/resources/rules/helloWorld.drl");
		Assert.assertNotNull(ruleResource);
		Assert.assertNotNull(ruleResource.getContent());
		Assert.assertEquals(ruleResource.getType(), ResourceType.DRL);
		System.out.println(ruleResource.getContent());
	}
	
	@Test(expectedExceptions=IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenFilenameIsNull() throws Exception{
		RuleHelper.loadRuleFromFile(null);
	}
	
	@Test(expectedExceptions=FileNotFoundException.class)
	public void shouldThrowFileNotFoundWhenFileDoesNotExist() throws Exception{
		RuleHelper.loadRuleFromFile("teste.drl");
	}
	
	@Test(expectedExceptions=IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenFilenameDontHaveExtension() throws Exception{
		RuleHelper.loadRuleFromFile("teste");
	}
}
