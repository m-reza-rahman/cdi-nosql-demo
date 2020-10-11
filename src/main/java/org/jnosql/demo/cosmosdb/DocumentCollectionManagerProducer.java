package org.jnosql.demo.cosmosdb;

import java.util.Collections;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.eclipse.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;

import jakarta.nosql.Settings;
import jakarta.nosql.document.DocumentCollectionManager;
import jakarta.nosql.document.DocumentCollectionManagerFactory;
import jakarta.nosql.document.DocumentConfiguration;

@ApplicationScoped
public class DocumentCollectionManagerProducer {

	private DocumentCollectionManagerFactory managerFactory;

	@PostConstruct
	public void init() {
		DocumentConfiguration configuration = new MongoDBDocumentConfiguration();
		Map<String, Object> settings = Collections.singletonMap("document.settings.jakarta.nosql.host",
				"mongodb://azure-game-store-db-reza.mongo.cosmos.azure.com:10255/?ssl=true&replicaSet=globaldb&retrywrites=false&maxIdleTimeMS=120000&appName=@azure-game-store-db-reza@");
		managerFactory = configuration.get(Settings.of(settings));
	}

	@Produces
	public DocumentCollectionManager getManager() {
		return managerFactory.get("document");
	}
}