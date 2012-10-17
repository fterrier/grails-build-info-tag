class BuildInfoTagGrailsPlugin {
    // the plugin version
    def version = "0.3.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.7 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp",
		"grails-app/views/*",
		"web-app/css/*",
		"web-app/js/*",
		"web-app/images/*"
    ]

    def title = "Build Info Tag Plugin" // Headline display name of the plugin
    def author = "François Terrier"
    def authorEmail = "fterrier@gmail.com"
    def description = '''\
Puts a build.info file in the generated WAR file and provides a few GSP tags to display
the information in it. For now, the build number is the date and the plugin also
collects the GIT commit number and displays it.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/build-info-tag"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
    def organization = [ name: "CHAI", url: "http://www.clintonhealthaccess.org/" ]

    // Any additional developers beyond the author specified above.
    def developers = [ [ name: "Enrico Comiti", email: "enrico@comiti.name" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "http://github.com/fterrier/grails-build-info-tag" ]

}
