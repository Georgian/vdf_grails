package vdf_grails

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.codehaus.groovy.util.HashCodeHelper
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class VDFAthleteRole implements Serializable {

	private static final long serialVersionUID = 1

	VDFAthlete VDFAthlete
	Role role

	@Override
	boolean equals(other) {
		if (other instanceof VDFAthleteRole) {
			other.VDFAthleteId == VDFAthlete?.id && other.roleId == role?.id
		}
	}

    @Override
	int hashCode() {
	    int hashCode = HashCodeHelper.initHash()
        if (VDFAthlete) {
            hashCode = HashCodeHelper.updateHash(hashCode, VDFAthlete.id)
		}
		if (role) {
		    hashCode = HashCodeHelper.updateHash(hashCode, role.id)
		}
		hashCode
	}

	static VDFAthleteRole get(long VDFAthleteId, long roleId) {
		criteriaFor(VDFAthleteId, roleId).get()
	}

	static boolean exists(long VDFAthleteId, long roleId) {
		criteriaFor(VDFAthleteId, roleId).count()
	}

	private static DetachedCriteria criteriaFor(long VDFAthleteId, long roleId) {
		VDFAthleteRole.where {
			VDFAthlete == VDFAthlete.load(VDFAthleteId) &&
			role == Role.load(roleId)
		}
	}

	static VDFAthleteRole create(VDFAthlete VDFAthlete, Role role, boolean flush = false) {
		def instance = new VDFAthleteRole(VDFAthlete: VDFAthlete, role: role)
		instance.save(flush: flush)
		instance
	}

	static boolean remove(VDFAthlete u, Role r) {
		if (u != null && r != null) {
			VDFAthleteRole.where { VDFAthlete == u && role == r }.deleteAll()
		}
	}

	static int removeAll(VDFAthlete u) {
		u == null ? 0 : VDFAthleteRole.where { VDFAthlete == u }.deleteAll() as int
	}

	static int removeAll(Role r) {
		r == null ? 0 : VDFAthleteRole.where { role == r }.deleteAll() as int
	}

	static constraints = {
	    VDFAthlete nullable: false
		role nullable: false, validator: { Role r, VDFAthleteRole ur ->
			if (ur.VDFAthlete?.id) {
				if (VDFAthleteRole.exists(ur.VDFAthlete.id, r.id)) {
				    return ['userRole.exists']
				}
			}
		}
	}

	static mapping = {
		id composite: ['VDFAthlete', 'role']
		version false
	}
}
