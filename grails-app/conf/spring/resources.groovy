import vdf_grails.VDFAthletePasswordEncoderListener
import com.giri.security.AppUserPasswordEncoderListener
package spring;

// Place your Spring DSL code here
beans = {
    VDFAthletePasswordEncoderListener(VDFAthletePasswordEncoderListener, ref('hibernateDatastore'))
    appUserPasswordEncoderListener(AppUserPasswordEncoderListener, ref('hibernateDatastore'))
}
