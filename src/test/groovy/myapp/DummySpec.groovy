package myapp

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class DummySpec extends Specification implements DataTest, ServiceUnitTest<DummyService> {

	@Override
	Class[] getDomainClassesToMock() {
		return Dummy
	}

	void "test find after update with explicit flush from service"() {
		Dummy dummy = new Dummy(name: "dummyExplicit").save flush: true

		when:
		service.updateWithExplicitFlush "dummyExplicit", "updatedDummyExplicit"
		Dummy dummyWithOldName = Dummy.findByName "dummyExplicit"
		Dummy dummyWithNewName = Dummy.findByName "updatedDummyExplicit"
		Dummy dummyWithOldNameFromService = service.findByName "dummyExplicit"
		Dummy dummyWithNewNameFromService = service.findByName "updatedDummyExplicit"

		then:
		verifyAll {
			dummy.name == "updatedDummyExplicit"
			!dummyWithOldName
			dummyWithNewName.name == "updatedDummyExplicit"
			!dummyWithOldNameFromService
			dummyWithNewNameFromService.name == "updatedDummyExplicit"
		}
	}

	void "test find after update with implicit flush from service"() {
		Dummy dummy = new Dummy(name: "dummyImplicit").save flush: true

		when:
		service.updateWithImplicitFlush "dummyImplicit", "updatedDummyImplicit"
		Dummy dummyWithOldName = Dummy.findByName "dummyImplicit"
		Dummy dummyWithNewName = Dummy.findByName "updatedDummyImplicit"
		Dummy dummyWithOldNameFromService = service.findByName "dummyImplicit"
		Dummy dummyWithNewNameFromService = service.findByName "updatedDummyImplicit"

		then:
		verifyAll {
			dummy.name == "updatedDummyImplicit"
			!dummyWithOldName
			dummyWithNewName.name == "updatedDummyImplicit"
			!dummyWithOldNameFromService
			dummyWithNewNameFromService.name == "updatedDummyImplicit"
		}
	}

}
