package com.gouresh.springhibernate.dao

import com.gouresh.springhibernate.entity.Instructor
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class InstructorDaoImpl @Autowired constructor(val entityManager: EntityManager) : InstructorDao {

    @Transactional
    override fun save(instructor: Instructor) {
        entityManager.persist(instructor)
    }

    override fun findAll(): List<Instructor> {
        val queryResult = entityManager.createQuery("From Instructor", Instructor::class.java)
        return queryResult.resultList
    }

    override fun findById(id: Long): Instructor? {
       return entityManager.find(Instructor::class.java, id)
//        val queryResult = entityManager.createQuery("From Instructor WHERE id=:instructorId", Instructor::class.java)
//        queryResult.setParameter("instructorId", id)
//        return queryResult.singleResult
    }

    @Transactional
    override fun delete(id: Long) {
        val instructor = entityManager.find(Instructor::class.java, id)
        entityManager.remove(instructor)
    }
}