package vdf_grails.auth

import org.apache.commons.lang.builder.HashCodeBuilder

import vdf_grails.VDFAthlete

class UserRole implements Serializable {

	private static final long serialVersionUID = 1

	VDFAthlete user
	Role role

	boolean equals(other) {
		if (!(other instanceof UserRole)) {
			return false
		}

		other.user?.id == user?.id &&
				other.role?.id == role?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (user) builder.append(user.id)
		if (role) builder.append(role.id)
		builder.toHashCode()
	}

	static UserRole get(long userId, long roleId) {
		UserRole.where {
			user == User.load(userId) &&
					role == Role.load(roleId)
		}.get()
	}

	static UserRole create(VDFAthlete user, Role role, boolean flush = false) {
		new UserRole(user: user, role: role).save(flush: flush, insert: true)
	}

	static boolean remove(VDFAthlete u, Role r, boolean flush = false) {

		int rowCount = UserRole.where {
			user == User.load(u.id) &&
					role == Role.load(r.id)
		}.deleteAll()

		rowCount > 0
	}

	static void removeAll(VDFAthlete u) {
		UserRole.where {
			user == User.load(u.id)
		}.deleteAll()
	}

	static void removeAll(Role r) {
		UserRole.where {
			role == Role.load(r.id)
		}.deleteAll()
	}

	static mapping = {
		id composite: ['role', 'user']
		version false
	}
}
