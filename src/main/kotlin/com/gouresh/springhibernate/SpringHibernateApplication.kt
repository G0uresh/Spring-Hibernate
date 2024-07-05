package com.gouresh.springhibernate

import com.gouresh.springhibernate.dao.InstructorDao
import com.gouresh.springhibernate.entity.Instructor
import com.gouresh.springhibernate.entity.InstructorDetail
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
            getInstructorById(instructorDao)
        }
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
    deleteInstructor(instructorDao)
}

fun deleteInstructor(instructorDao: InstructorDao){
    instructorDao.delete(2)
}
