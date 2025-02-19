package com.example.catube

interface ActionBar {
    fun setTitle(title: String)
    fun setupBar()
    fun goUp(): Boolean
}