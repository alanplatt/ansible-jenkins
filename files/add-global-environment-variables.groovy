println 'Running add-global-environment-variables.groovy'

import hudson.slaves.EnvironmentVariablesNodeProperty
import jenkins.model.Jenkins

instance = Jenkins.getInstance()
globalNodeProperties = instance.getGlobalNodeProperties()
envVarsNodePropertyList = globalNodeProperties.getAll(EnvironmentVariablesNodeProperty.class)

newEnvVarsNodeProperty = null
envVars = null

if ( envVarsNodePropertyList == null || envVarsNodePropertyList.size() == 0 ) {
  newEnvVarsNodeProperty = new EnvironmentVariablesNodeProperty();
  globalNodeProperties.add(newEnvVarsNodeProperty)
  envVars = newEnvVarsNodeProperty.getEnvVars()
} else {
  envVars = envVarsNodePropertyList.get(0).getEnvVars()
}

//load config file
Properties properties = new Properties()
File propertiesFile = new File('/etc/default/microdc')
propertiesFile.withInputStream {
    properties.load(it)
}

//add env vars
properties.each{ k, v ->
  envVars.put("${k}", "${v}")
}

instance.save()
