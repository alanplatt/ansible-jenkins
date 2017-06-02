// 'seed.groovy'.


def groovy='''import jenkins.model.Jenkins
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.dsl.ScriptRequest
import javaposse.jobdsl.plugin.JenkinsJobManagement

def jenkinshome = Jenkins.getInstance().getRootDir().getPath()

def jobManagement = new JenkinsJobManagement(System.out, [:], new File('.'))

new FileNameFinder().getFileNames(jenkinshome + '/jobdsl',
                                  '*.groovy').each() { filename ->
  println "Processing ${filename}"
  def scriptRequest = new ScriptRequest(new File(filename).text,
                                        new File('.').toURI().toURL(),
                                        true)
  def dslScriptLoader =
    new DslScriptLoader(jobManagement).runScripts([scriptRequest])
  dslScriptLoader.jobs.each { e -> println e.jobName }
  dslScriptLoader.views.each { e -> println e.name }
}
'''


job('seed') {
  steps {
    systemGroovyCommand(groovy)
  }
}


def scriptApproval = jenkins.model.Jenkins.instance.getExtensionList('org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval')[0]
scriptApproval.approveScript(scriptApproval.hash(groovy, 'groovy'))
