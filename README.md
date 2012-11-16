Build Info Tag Plugin
=====================

This Grails plugin puts a build.info file in the classpath and provides some GSP tags to display
the information in it:

* Build date
* Git commit hash
* System name
* Timezone

The Git commit hash is resolved from:

* the result of `git rev-parse HEAD`
* the manual parsing of the refs file (`.git/HEAD`): useful for environments where Git is not in the path (eg. Windows)
* the value of the environment variable `GIT_COMMIT`

The last option is useful for environments such as continuous integration servers or cloud where the application has no access to Git files.

If you use Hudson or Jenkins, you should have the variable settled for free.

    
## Installation

Put in app BuildConfig plugin dependencies:

    compile ":build-info-tag:0.3.1"

## Tags

* build:buildInfo : provides a list of all properties
* build:buildDate: provides only the value of the buildDate property
* build:gitCommit: provides only the value of the gitCommit property
* build:systemName: provides only the value of the systemName property
* build:timezone: provides only the value of the timezone property

