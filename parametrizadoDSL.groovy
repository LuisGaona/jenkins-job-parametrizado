job('ejemplo-job-DSL') {
 	description('Job DSL de ejemplo para el Curso de Jenkins')
    scm {
      git('https://github.com/macloujulian/jenkins.job.parametrizado.git', 'main') { node -> 
      node / gitConfigName('macloujulian')
      node / gitConfigEmail('macloujulian@gmail.com')
       }
     }
    parameters {
  		stringParam('Nombre', defaultValue='Luis',description='Parametro de cadena para el job booleano')
        choiceParam('Planeta',['Mercurio','Venus', 'Tierra', 'Marte', 'Saturno', 'Urano', 'Neptuno'])
        booleanParam('Agente',false)
    }
    triggers {
  	 cron('H/7 * * * *')
    }
    steps {
        shell("bash jobscript.sh")
     }
    publishers {
        mailer('betdrago@gmail.com', true, true)
        slackNotifier {
         	notifyAborted(true)
            notifyEveryFailure(true)
            notifyNotBuilt(false)
            notifyUnstable(false)
            notifyBackToNormal(true)
            notifySuccess(false)
            notifyRepeatedFailure(false)
            startNotification(false)
            includeTestSummary(false)
            includeCustomMessage(false)
            customMessage(null)
            sendAs(null)
            commitInfoChoice('NONE')
            teamDomain(null)
            authToken(null)
        }
    }
}
