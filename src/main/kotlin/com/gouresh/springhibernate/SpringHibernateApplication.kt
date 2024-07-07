package com.gouresh.springhibernate

import com.gouresh.springhibernate.dao.InstructorDao
import com.gouresh.springhibernate.entity.*
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpringHibernateApplication  {



//    override fun run(vararg args: String?) {
//        println("started Application")
//        System.err.println("This is an error message sd")
////          createInstructor(instructorDao)
//        println("Done")
//    }

    @Bean
    fun commandLineRunner(instructorDao: InstructorDao): CommandLineRunner {
        return CommandLineRunner {
//            createInstructor(instructorDao)
//            deleteInstructorDetailById(instructorDao)

//            createInstructorWithCourses(instructorDao)
//            findInstructorWithCourse(instructorDao)
//            findInstructorWithCourseJoinFetch(instructorDao)
//            updateInstructor(instructorDao)
//            deleteInstructor(instructorDao)
//            createCourseWithReview(instructorDao)
//            findCourseReview(instructorDao)
//            createCourseAndStudent(instructorDao)
//            findCourseAndStudentByCourseId(instructorDao)
            findCourseReview(instructorDao)
        }
    }

    private fun findCourseAndStudentByCourseId(instructorDao: InstructorDao) {
        val id = 10L
        val course = instructorDao.findCourseAndStudentByCourseId(id)
        print(course.students.size)
    }

    private fun createCourseAndStudent(instructorDao: InstructorDao) {
        val course1 = Course(title = "One Many Relationship")
        val student1 = Student(firstName = "Ugres", lastName = "Naik" , email = "asd@asd.com")
        val student2 = Student(firstName = "Jitesh", lastName = "Patil" , email = "pavan@asd.com")
        course1.addStudent(student1)
        course1.addStudent(student2)
        val review2 = Review(comment = "Bad course")
        course1.addReview(review2)
        instructorDao.saveCourseWithReview(course1)
    }

    private fun findCourseReview(instructorDao: InstructorDao) {
        instructorDao.deleteCourse(12)
    }

    private fun createCourseWithReview(instructorDao: InstructorDao) {
        val course1 = Course(title = "Spring Hibernate")
        val review1 = Review(comment = "Nice course")
        val review2 = Review(comment = "Bad course")
        course1.addReview(review1)
        course1.addReview(review2)
        instructorDao.saveCourseWithReview(course1)
    }

    private fun updateInstructor(instructorDao: InstructorDao) {
        val id =1L
       val instructor = instructorDao.findById(id)
        instructor?.let {
            it.lastName = "temp"
            it.email = "temp@gouresh.com"
            instructorDao.updateInstructor(it)
        }
    }

    private fun findInstructorWithCourseJoinFetch(instructorDao: InstructorDao) {
        val id = 1L
        val instructor = instructorDao.findInstructorByJoinFetch(id)
        print(instructor?.courseList?.size)
    }

    private fun findInstructorWithCourse(instructorDao: InstructorDao) {
        val id = 1L
        val instructor = instructorDao.findById(id)

        val courses = instructorDao.findCoursesByInstructorId(2)
//        print(instructor?.courseList?.size)
        instructor?.courseList = courses.toMutableList()
        print(instructor?.courseList?.size)
    }

    private fun createInstructorWithCourses(instructorDao: InstructorDao) {
        val instructorDetail = InstructorDetail(youtubeChannel = "htps:llasd/asdasd" , hobby = "Volleyball")
        val instructor = Instructor( firstName = "tommy", lastName = "smeell", email = "asd@gmail.com", instructorDetail = instructorDetail)
        val course1 = Course(title = "Spring Boot")
        val course2 = Course(title = "Spring JPA")
        val course3 = Course(title = " JPA")

        instructor.addCourse(course1)
        instructor.addCourse(course2)
        instructor.addCourse(course3)
        instructorDao.save(instructor)
    }
}

fun main(args: Array<String>) {
    runApplication<SpringHibernateApplication>(*args)
}

fun createInstructor(instructorDao: InstructorDao) {
    val instructorDetail = InstructorDetail(youtubeChannel = "htps:llasd/asdasd" , hobby = "football")
    val instructor = Instructor( firstName = "Goua", lastName = "asd", email = "asd@gmail.com", instructorDetail = instructorDetail)
    instructorDao.save(instructor)
    getAllInstructors(instructorDao)
}

fun getAllInstructors(instructorDao: InstructorDao) {
   val instructorList = instructorDao.findAll()
    instructorList.forEach {
        println("-->  ${it}")
    }
}

fun getInstructorById(instructorDao: InstructorDao) {
    instructorDao.findById(2)?.let {
        println("-->  $it")

    }
}

fun deleteInstructor(instructorDao: InstructorDao){
    instructorDao.delete(1)
}

fun findInstructorDetails(instructorDao: InstructorDao){
    val instructorDetail = instructorDao.findInstructorDetailsById(3)?.let {
        println("-->  ${it.instructor?.lastName}")
    }
}

fun deleteInstructorDetailById(instructorDao: InstructorDao){
    findInstructorDetails(instructorDao)
    instructorDao.deleteInstructorDetailsById(3)
}
