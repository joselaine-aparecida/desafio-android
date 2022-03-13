package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.domain.models.UserEntity
import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.factory.createUserResponse
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Test

class UserMappersTest {

    @Test
    fun givenConvertUserResponseToDomain_whenSuccess_shouldUserModel() {
        val result = createUserResponse().toDomain()
        val expectedResult = User(
            id = 1,
            img = "www.google.com/users",
            name = "Joselaine Aparecida",
            username = "joselaine.ap"
        )
        assertThat(result, IsEqual(expectedResult))
    }

    @Test
    fun givenConvertUserResponseToEntity_whenSuccess_shouldUserEntity() {
        val result = createUserResponse().toEntity()
        val expectedResult = UserEntity(
            id = 1,
            img = "www.google.com/users",
            name = "Joselaine Aparecida",
            username = "joselaine.ap"
        )
        assertThat(result, IsEqual(expectedResult))
    }

    @Test
    fun givenConvertUserEntityToModel_whenSuccess_shouldUserModel() {
        val result = UserEntity(
            id = 1,
            img = "www.google.com/users",
            name = "Joselaine Aparecida",
            username = "joselaine.ap"
        ).toDomain()
        val expectedResult = User(
            id = 1,
            img = "www.google.com/users",
            name = "Joselaine Aparecida",
            username = "joselaine.ap"
        )
        assertThat(result, IsEqual(expectedResult))
    }

}