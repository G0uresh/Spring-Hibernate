package com.gouresh.springhibernate.dao

import com.gouresh.springhibernate.entity.Instructor


interface InstructorDao {
    fun save(instructor: Instructor)
    fun findAll(): List<Instructor>
    fun findById(id: Long): Instructor?
    fun delete(id: Long)
}