package org.chai.buildinfo

import grails.util.GrailsUtil;
import grails.util.Metadata;

class BuildInfoTagLib {

	static namespace = "build"
	
	def grailsApplication
	
	def buildInstance
	def buildInitialized = false

	def getBuild() {
		if (!buildInitialized) {
			def resource = grailsApplication.parentContext.getResource("classpath:build.info")
			if (resource.exists()) buildInstance = Metadata.getInstance(resource.inputStream)
			else buildInstance = null
			buildInitialized = true
		}
		return buildInstance
	}
    
    def getBuildProperty(String propertyName) {
        return (build ? build[propertyName] : '')
    }
		
	def buildInfo = {attrs, body ->
        if (build == null) {
            out << "no build info found"
        } else {
            out << render(plugin:'buildInfoTag', template:'/templates/buildInfo', model:[
                buildDate: build.'app.buildDate',
                gitCommit: build.'app.gitCommit',
                systemName: build.'app.systemName',
                timezone: build.'app.timezone'
            ])
        }
	}

	def buildDate = {attrs, body ->
        out << getBuildProperty('app.buildDate')
	}
	def gitCommit = {attrs, body ->
        out << getBuildProperty('app.gitCommit')
	}
	def systemName = {attrs, body ->
        out << getBuildProperty('app.systemName')
	}
	def timezone = {attrs, body ->
        out << getBuildProperty('app.timezone')
	}
}
