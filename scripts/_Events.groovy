import grails.util.Metadata;

import java.text.SimpleDateFormat;

// the only way I found to know the trigger.
def triggeredFromRunApp(String message) {
    return message?.startsWith("Server running.")
}

def writeBuildInfoFile(path) {
    println "Write build info file ${path}"
	def formatter = new SimpleDateFormat("ddMMyyyy-HHmmss")
	def buildDate = formatter.format(new Date(System.currentTimeMillis()))
	buildDate += '-'+Calendar.getInstance().getTimeZone().getDisplayName(false, TimeZone.SHORT)
	
	def gitCommit = System.getenv('GIT_COMMIT') ?: ''
	try {
		def proc = "git rev-parse HEAD".execute()
	    proc.waitFor()
	    if (proc.exitValue() == 0) {
		    gitCommit = proc.text
		}
	} catch (Exception e) {
        // workaround for win
        def headFile = new File(".git/HEAD")
        def refsHeadPath = ''
        if (headFile.exists()) {
            def headContents = headFile.text.trim()
            refsHeadPath = headContents.split(':')[1].trim()
            def refsHeadFile = new File(".git/${refsHeadPath}")
            if (refsHeadFile.isFile()) {
                gitCommit = refsHeadFile.text.trim()
            }
        }
    }
    if (!gitCommit) {
        println 'Neither Git files nor "GIT_COMMIT" env var found...'
    }
	
	Metadata build = Metadata.getInstance(new File(path));
	build.'app.buildDate' = buildDate
	build.'app.gitCommit' = gitCommit
	build.'app.systemName' = InetAddress.getLocalHost().getHostName()
	build.'app.timezone' = Calendar.getInstance().getTimeZone().getID()
	build.persist()
 
	println "Compile Starting on build #${buildDate}, git commit: ${gitCommit}"
}

eventStatusFinal = { message ->
    def isRunApp = triggeredFromRunApp(message)
    if (isRunApp) {
        writeBuildInfoFile("${classesDir}/build.info")
    }
}

eventCreateWarStart = { warName, stagingDir ->
    writeBuildInfoFile("${stagingDir}/WEB-INF/classes/build.info")
}
