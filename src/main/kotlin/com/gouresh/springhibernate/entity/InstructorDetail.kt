package com.gouresh.springhibernate.entity

import jakarta.persistence.*

@Entity
@Table(name = "instructor_detail")
class InstructorDetail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") val id: Long? = null,
    @Column(name = "youtube_channel") val youtubeChannel: String,
    @Column(name = "hobby") val hobby: String,
    @OneToOne(
        mappedBy = "instructorDetail",
        cascade = [CascadeType.ALL]
    )
    var instructor: Instructor? = null
)


