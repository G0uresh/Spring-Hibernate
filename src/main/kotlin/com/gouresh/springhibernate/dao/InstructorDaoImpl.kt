package com.gouresh.springhibernate.dao

import com.gouresh.springhibernate.entity.Course
import com.gouresh.springhibernate.entity.Instructor
import com.gouresh.springhibernate.entity.InstructorDetail
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
        for(courses in instructor.courseList){
            courses.instructor = null
        }
        entityManager.remove(instructor)
    }

    override fun findInstructorDetailsById(id: Long): InstructorDetail? {
        val entity = entityManager.find(InstructorDetail::class.java, id)
        return entity
    }

    @Transactional
    override fun deleteInstructorDetailsById(id: Long) {
        val entity = entityManager.find(InstructorDetail::class.java, id)
        entity.instructor = null
        entityManager.remove(entity)
    }

    override fun findCoursesByInstructorId(id: Long): List<Course> {
        val queryResult =
            entityManager.createQuery("From Course WHERE instructor.id =: instructorId", Course::class.java)
        queryResult.setParameter("instructorId", id)
        return queryResult.resultList
    }

    override fun findInstructorByJoinFetch(id: Long): Instructor? {
        val queryResult = entityManager.createQuery(
            "SELECT i From Instructor i JOIN FETCH i.courseList join FETCH i.instructorDetail where i.id = :data",
            Instructor::class.java
        )
        queryResult.setParameter("data", id)
        return queryResult.singleResult
    }

    @Transactional
    override fun updateInstructor(instructor: Instructor): Instructor {
        return entityManager.merge(instructor)
    }

    @Transactional
    override fun saveCourseWithReview(course: Course) {
        entityManager.persist(course)
    }

    override fun findCourseReviewById(id: Long): Course {
        val query = entityManager.createQuery("select c from Course c join fetch c.review where c.id = :data", Course::class.java)
        query.setParameter("data" , id)
        return query.singleResult
    }

    @Transactional
    override fun deleteCourse(id: Long) {
        val course = findCourse(id)
        entityManager.remove(course)
    }

    override fun findCourse(id: Long): Course {
        return entityManager.find(Course::class.java, id)
    }

    override fun findCourseAndStudentByCourseId(id: Long): Course {
        val query = entityManager.createQuery("select c from Course c join fetch c.students where c.id = :data", Course::class.java)
        query.setParameter("data" , id)
        return query.singleResult
    }
}