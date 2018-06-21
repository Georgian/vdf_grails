package vdf_grails

import vdf_grails.auth.Role
import vdf_grails.auth.UserRole

class VDFAthlete {

	String name
	String email 
	String password

	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	VDFAthlete (def email, def password) {
		this.email = email
		this.password = password
	}

	static mapping = {
        table "vdf_athlete"
        version false
    }
	
	static constraints = {
		name nullable: true
		email blank: false, unique: true
		password blank: false
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

//	def beforeInsert() {
//		encodePassword()
//	}
//
//	def beforeUpdate() {
//		if (isDirty('password')) {
//			encodePassword()
//		}
//	}
//
//	protected void encodePassword() {
//		password = springSecurityService.encodePassword(password)
//	}
}
