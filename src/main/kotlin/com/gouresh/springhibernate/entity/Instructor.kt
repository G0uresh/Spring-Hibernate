package com.gouresh.springhibernate.entity

import jakarta.persistence.*

@Entity
@Table(name = "instructor")
class Instructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") val id: Long? = null,
    @Column(name = "first_name") var firstName: String?,
    @Column(name = "last_name") var lastName: String?,
    @Column(name = "email") var email: String?,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "instructor_detail_id")
    var instructorDetail: InstructorDetail,
    @OneToMany(
        mappedBy = "instructor",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE]
    )
    var courseList: MutableList<Course> = mutableListOf()
) {

    // bidirection handling
    fun addCourse(course: Course) {
        courseList.add(course)
        course.instructor = this
    }
}

