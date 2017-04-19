println 'Running dslscriptloader.groovy'

import jenkins.model.Jenkins
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.dsl.ScriptRequest
import javaposse.jobdsl.plugin.JenkinsJobManagement

def jenkinshome = Jenkins.getInstance().getRootDir().getPath()

def jobManagement = new JenkinsJobManagement(System.out, [:], new File('.'))

new FileNameFinder().getFileNames(jenkinshome + '/jobdsl',
                                  '*.groovy').each() { filename ->
  def scriptRequest = new ScriptRequest(new File(filename).text,
                                        new File('.').toURI().toURL(),
                                        true)
  new DslScriptLoader(jobManagement).runScripts([scriptRequest])
}
