package vdf_grails

class VDFEventController {

	def allEvents() {
		VDFEvent.findAll()
	}
	
}
