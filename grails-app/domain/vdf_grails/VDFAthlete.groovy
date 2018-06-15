package vdf_grails

import vdf_grails.auth.Role

class VDFAthlete {

	transient springSecurityService
	
	String name
	String username // email
	String password

	boolean enabled = true
	
	VDFAthlete (def username, def password) {
		this.username = username
		this.password = password
	}

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
