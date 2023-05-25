package tj.mangotask.domain.mapper

import tj.mangotask.data.local.entities.TokenEntity
import tj.mangotask.data.local.entities.UserAvatarEntity
import tj.mangotask.data.local.entities.UserEntity
import tj.mangotask.data.model.*
import tj.mangotask.data.remote.dtos.*

fun PhoneDto.asExternalModel() = PhoneSuccess(isSuccess = isSuccess)

fun PhoneWithCodeDto.asEntity() =
    TokenEntity(
        userId = userId,
        refreshToken = refreshToken,
        accessToken = accessToken,
        isUserExists = isUserExists
    )

fun TokenEntity.asExternalModel() =
    UserToken(
        userId = userId,
        refreshToken = refreshToken ?: "",
        accessToken = accessToken ?: "",
        isUserExists = isUserExists
    )

fun RegisterDto.asEntity() =
    TokenEntity(
        userId = userId,
        refreshToken = refreshToken,
        accessToken = accessToken
    )

fun TokenEntity.asExternalModel2() =
    Register(
        userId = userId,
        refreshToken = refreshToken ?: "",
        accessToken = accessToken ?: ""
    )

fun UserDto.asEntity() = UserEntity(
    name = name,
    phone = phone,
    username = username,
    city = city,
    birthday = birthday,
    id = id,
    avatar = avatar,
    vk = vk,
    instagram = instagram,
    avatars = UserAvatarEntity(
        avatar = avatars?.avatar,
        bigAvatar = avatars?.bigAvatar,
        miniAvatar = avatars?.miniAvatar
    )
)

fun AvatarDto.asExternalModel() = Avatar(
    avatar = avatar,
    bigAvatar = bigAvatar,
    miniAvatar = miniAvatar
)

fun UserEntity.asExternalModel() = Profile(
    id = id,
    name = name,
    phone = phone,
    username = username,
    city = city,
    birthday = birthday,
    avatar = avatar,
    vk = vk,
    instagram = instagram,
    avatars = ProfileAvatar(avatar = avatars?.avatar)
)





