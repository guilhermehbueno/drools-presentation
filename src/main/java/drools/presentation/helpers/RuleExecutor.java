package drools.presentation.helpers;

import java.util.List;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Message.Level;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

import drools.presentation.models.RuleResource;

public class RuleExecutor {

	private KieServices kieServices;

	public RuleExecutor(String resourceBaseName, RuleResource...resources) {
		kieServices = KieServices.Factory.get();
		KieFileSystem kfs = kieServices.newKieFileSystem();
		
		for(RuleResource resource : resources) {
			if(resource.getContent()==null){
				kfs.write("src/main/resources/"+resourceBaseName+"/"+resource.getResourceName(), ResourceFactory.newClassPathResource(resource.getResourceName()));
			}else{
				kfs.write("src/main/resources/"+resourceBaseName+"/"+resource.getResourceName(), resource.getContent());
			}
		}
		
		KieBuilder kieBuilder = kieServices.newKieBuilder(kfs);
		List<Message> messages = kieBuilder.getResults().getMessages(Level.ERROR);
		for (Message message : messages) {
			System.err.println(message);
		}
		kieServices.newKieBuilder(kfs).buildAll();
	}

	public void execute(Object model) {
		KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
		KieBase kieBase = kieContainer.getKieBase();
		KieSession newKieSession = kieBase.newKieSession();
		newKieSession.insert(model);
		newKieSession.fireAllRules();
		newKieSession.dispose();
	}
	
	public void execute(Object model, String group) {
		KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
		KieBase kieBase = kieContainer.getKieBase();
		KieSession newKieSession = kieBase.newKieSession();
		
		newKieSession.getAgenda().getAgendaGroup(group).setFocus();
		newKieSession.insert(model);
		newKieSession.fireAllRules();
		newKieSession.dispose();
	}
}
