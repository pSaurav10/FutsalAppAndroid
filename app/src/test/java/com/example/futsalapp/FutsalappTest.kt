package com.example.futsalapp

import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.model.Comment
import com.example.futsalapp.model.FutsalBook
import com.example.futsalapp.model.Player
import com.example.futsalapp.model.Post
import com.example.futsalapp.repository.FutsalRepository
import com.example.futsalapp.repository.PostRepository
import com.example.futsalapp.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class FutsalappTest {

    private lateinit var userRepository: UserRepository
    private lateinit var FutsalRepository: FutsalRepository
    private lateinit var PostRepository: PostRepository
    // -----------------------------Login Testing-----------------------------
    @Test
    fun checkLogin() = runBlocking {
        userRepository = UserRepository()
        val response = userRepository.userLogin("saurav1", "password")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }

    // -----------------------------Register Testing-----------------------------
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


    // -----------------------------Booking Testing-----------------------------
    @Test
    fun futsalBooking() = runBlocking {
        FutsalRepository = FutsalRepository()
        val futsalbook = FutsalBook(futsalname = "Jadibuti Futsal", futsalid = "465646546",
                date = "02/02/2021", time = "06 am", username = "saurav1", userid = "54684653")
        val response = FutsalRepository.bookFutsal(futsalbook)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)

    }


    // -----------------------------PostAdd Testing-----------------------------
    @Test
    fun postADD() = runBlocking {
        PostRepository = PostRepository()
        userRepository = UserRepository()

        ServiceBuilder.token= "Bearer" + userRepository.userLogin("saurav1", "password").token
        val comments = Comment(id = "606ca5f7581fdac702474d96", comment = "This is a comment")

        val response = PostRepository.addComment(comments)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }


    // -----------------------------Booking Delete Testing-----------------------------
    @Test
    fun bookingDelete() = runBlocking {
        FutsalRepository = FutsalRepository()
        userRepository = UserRepository()
        ServiceBuilder.token= "Bearer" + userRepository.userLogin("saurav1", "password").token
        val response = FutsalRepository.deleteBookings("60687e0ec673da3b647cff9f")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)

    }

}