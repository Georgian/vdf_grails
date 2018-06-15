package vdf_grails.auth

class Role {

	String authority

	static mapping = {
		cache true
	}
	
	Role (def authority) {
		this.authority = authority
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
