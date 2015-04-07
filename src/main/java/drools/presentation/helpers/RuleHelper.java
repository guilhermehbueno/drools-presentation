package drools.presentation.helpers;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.kie.api.io.ResourceType;

import drools.presentation.models.RuleResource;

public class RuleHelper {
	
	public static RuleResource[] loadRulesFromFiles(String...fileNames) throws Exception{
		List<RuleResource> rules = new ArrayList<RuleResource>();
		for (String file : fileNames) {
			RuleResource ruleResource = loadRuleFromFile(file);
			rules.add(ruleResource);
		}
		return rules.toArray(new RuleResource[rules.size()]);
	}
	
	public static RuleResource loadRuleFromFile(String fileName) throws Exception{
		if(fileName==null){
			throw new IllegalArgumentException("Filename cannot be null");
		}
		String extension = FilenameUtils.getExtension(fileName);
		
		if(StringUtils.isEmpty(extension)){
			throw new IllegalArgumentException("Filename must have a extension");
		}
		
		String content = IOUtils.toString(new FileReader(fileName));
		RuleResource resource = new RuleResource();
		resource.setContent(content);
		resource.setResourceName(fileName);
		ResourceType type = ResourceType.getResourceType(extension.toUpperCase());
		resource.setType(type);
		return resource;
	}
}