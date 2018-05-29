package myapp

import grails.gorm.transactions.Transactional

@Transactional
class DummyService {

	void updateWithExplicitFlush(String oldName, String newName) {
		Dummy dummy = Dummy.findByName oldName
		dummy.name = newName
		dummy.save flush: true
	}

	void updateWithImplicitFlush(String oldName, String newName) {
		Dummy dummy = Dummy.findByName oldName
		dummy.name = newName
	}

	Dummy findByName(String name) {
		return Dummy.findByName("updatedDummy")
	}

}
