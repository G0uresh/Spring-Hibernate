package com.gouresh.springhibernate.entity

import jakarta.persistence.*

@Entity
@Table(name = "course")
class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var title: String,
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "instructor_id")
    var instructor: Instructor? = null,

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "course_id")
    var review: MutableList<Review> = mutableListOf(),

    @ManyToMany(
        cascade = [CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE]
    )
    @JoinTable(
        name="course_student",
        joinColumns = [JoinColumn(name = "course_id")],
        inverseJoinColumns = [JoinColumn(name = "student_id")]
    )
    var students: MutableList<Student> = mutableListOf()
) {

    fun addReview(tempReview: Review) {
        review.add(tempReview)
    }

    fun addStudent(student : Student){
        students.add(student)
    }
}