Build Info Tag Plugin
=====================

This Grails plugin puts a build.info file in the classpath and provides a some GSP tags to display
the information in it.

For now, the build number is the date and the plugin also
collects the GIT commit number and displays it (in Windows it manually parses the refs file).

## Installation

Put in app BuildConfig:

    grails.plugin.location.'build-info-tag' = "/path/to/this/plugin"

## Tags

* build:buildInfo : provides a list of all properties
* build:buildDate: provides only the value of the buildDate property
* build:gitCommit: provides only the value of the gitCommit property
* build:systemName: provides only the value of the systemName property
* build:timezone: provides only the value of the timezone property
