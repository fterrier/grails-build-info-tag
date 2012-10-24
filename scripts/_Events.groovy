
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

    def gitCommit = ''
    try {
        def proc = "git rev-parse HEAD".execute()
        gitCommit = proc.text
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
        } else {
            println "No Git files found... It seems this project is not using Git."
        }
    }

    Ant.propertyfile(file: path) {
        entry(key: 'app.buildDate', value: buildDate)
        entry(key: 'app.gitCommit', value: gitCommit)
        entry(key: 'app.systemName', value: InetAddress.getLocalHost().getHostName())
        entry(key: 'app.timezone', value: Calendar.getInstance().getTimeZone().getID())
    }
 
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