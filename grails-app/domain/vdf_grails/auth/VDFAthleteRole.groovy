package vdf_grails.auth

import org.apache.commons.lang.builder.HashCodeBuilder
import vdf_grails.VDFAthlete

class VDFAthleteRole implements Serializable {

	private static final long serialVersionUID = 1

	VDFAthlete user
	Role role

	boolean equals(other) {
		if (!(other instanceof VDFAthleteRole)) {
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

	static VDFAthleteRole get(long userId, long roleId) {
		VDFAthleteRole.where {
			user == User.load(userId) &&
					role == Role.load(roleId)
		}.get()
	}

	static VDFAthleteRole create(VDFAthlete user, Role role, boolean flush = false) {
		new VDFAthleteRole(user: user, role: role).save(flush: flush, insert: true)
	}

	static boolean remove(VDFAthlete u, Role r, boolean flush = false) {

		int rowCount = VDFAthleteRole.where {
			user == User.load(u.id) &&
					role == Role.load(r.id)
		}.deleteAll()

		rowCount > 0
	}

	static void removeAll(VDFAthlete u) {
		VDFAthleteRole.where {
			user == User.load(u.id)
		}.deleteAll()
	}

	static void removeAll(Role r) {
		VDFAthleteRole.where {
			role == Role.load(r.id)
		}.deleteAll()
	}

	static mapping = {
		id composite: ['role', 'user']
		version false
	}
}
