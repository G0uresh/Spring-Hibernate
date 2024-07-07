package com.gouresh.springhibernate.entity

import jakarta.persistence.*

@Entity
@Table(name = "review")
class Review(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var comment: String,
) {

}