package com.gouresh.springhibernate.entity

import jakarta.persistence.*

@Entity
@Table(name = "instructor")
data class Instructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") val id: Long? = null,
    @Column(name = "first_name") val firstName: String?,
    @Column(name = "last_name") val lastName: String?,
    @Column(name = "email") val email: String?,

    @OneToOne(cascade = [CascadeType.ALL] , fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_detail_id")
    val instructorDetail: InstructorDetail
)
