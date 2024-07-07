package com.gouresh.springhibernate.entity

import jakarta.persistence.*

@Entity
@Table(name = "student")
class Student (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(name = "first_name")
    var firstName: String? = null,
    @Column(name = "last_name")
    var lastName: String? = null,
    @Column(name = "email")
    var email: String? = null,

    @ManyToMany(
        cascade = [CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE]
    )
    @JoinTable(
        name="course_student",
        joinColumns = [JoinColumn(name = "student_id")],
        inverseJoinColumns = [JoinColumn(name = "course_id")]
    )
    var courses : MutableList<Course> = mutableListOf()
){
    fun addCourse(course : Course){
        courses.add(course)
    }
}