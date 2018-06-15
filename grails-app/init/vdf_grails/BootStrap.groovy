package vdf_grails

import vdf_grails.auth.Role
import vdf_grails.auth.VDFAthleteRole

class BootStrap {

    def init = { servletContext ->
		
		def athlete_1 = new VDFAthlete(username: 'em', password: '123').save(true)
		def userRole = new Role('ROLE_USER').save(true)
		
		//VDFAthleteRole.create athlete_1, userRole
    }
    def destroy = {
    }
}
