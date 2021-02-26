package com.example.futsalapp

import com.example.futsalapp.model.Player
import com.example.futsalapp.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class FutsalappTest {

    private lateinit var userRepository: UserRepository
    // -----------------------------User Testing-----------------------------
    @Test
    fun checkLogin() = runBlocking {
        userRepository = UserRepository()
        val response = userRepository.userLogin("saurav1", "password")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }
    @Test
    fun registerUser() = runBlocking {
        val player =
                Player(fname = "test", lname = "test", username = "zxxcxcx", password = "testpassword", email = "test@gmail.com")
        userRepository = UserRepository()
        val response = userRepository.userRegister(player)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }
    // -----------------------------Student Testing-----------------------------
//    @Test
//    fun addStudent() = runBlocking {
//        userRepository = UserRepository()
//        studentRepository = StudentRepository()
//        val student =
//                Student(fullname = "fullName", age = 33, gender = "gender", address = "address")
//        ServiceBuilder.token ="Bearer " + userRepository.checkUser("kiran","kiran123").token
//        val expectedResult = true
//        val actualResult = studentRepository.insertStudent(student).success
//        Assert.assertEquals(expectedResult, actualResult)
//    }
}