package vdf_grails

import vdf_grails.auth.Role
import vdf_grails.auth.UserRole

class BootStrap {
	
	def springSecurityService

    def init = { servletContext ->
		
		def athlete_1 = new VDFAthlete(email: "covfefe", password: springSecurityService.encodePassword("lol")).save(flush: true, failOnError: true)
		def userRole = new Role('ROLE_USER').save(true)
		
		UserRole.create athlete_1, userRole
    }
    def destroy = {
    }
}
