package com.gouresh.springhibernate.dao

import com.gouresh.springhibernate.entity.Course
import com.gouresh.springhibernate.entity.Instructor
import com.gouresh.springhibernate.entity.InstructorDetail


interface InstructorDao {
    fun save(instructor: Instructor)
    fun findAll(): List<Instructor>
    fun findById(id: Long): Instructor?
    fun delete(id: Long)

    // find instructor using instructor details
    fun findInstructorDetailsById(id: Long): InstructorDetail?
    fun deleteInstructorDetailsById(id: Long)

    fun findCoursesByInstructorId(id: Long):List<Course>
    fun findInstructorByJoinFetch(id: Long) : Instructor?

    fun updateInstructor(instructor: Instructor) : Instructor

    fun saveCourseWithReview(course: Course)
    fun findCourseReviewById(id: Long) : Course
    fun deleteCourse(id: Long)
    fun findCourse(id: Long) : Course

    fun findCourseAndStudentByCourseId(id : Long) : Course

}