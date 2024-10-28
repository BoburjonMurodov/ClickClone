package uz.gita.boboor.bankingappcompose.data.remote.mappers

import uz.gita.data.remote.request.AuthRequestModel
import uz.gita.common.models.request.AuthRequestParam


internal fun AuthRequestParam.SignUpRequest.toRequest() =
    AuthRequestModel.SignUpRequest(phone, password, firstName, lastName, birthDate, gender)

internal fun AuthRequestParam.SignInRequest.toRequest() = AuthRequestModel.SignInRequest(phone, password)
internal fun AuthRequestParam.SignUpVerify.toRequest(token: String) = AuthRequestModel.SignUpVerify(token, code)
internal fun AuthRequestParam.SignInVerify.toRequest(token: String) = AuthRequestModel.SignInVerify(token, code)
internal fun AuthRequestParam.SignUpResend.toRequest(token: String) = AuthRequestModel.SignUpResend(token)
internal fun AuthRequestParam.SignInResend.toRequest(token: String) = AuthRequestModel.SignInResend(token)
internal fun AuthRequestParam.UpdateToken.toRequest(refreshToken: String) = AuthRequestModel.UpdateToken(refreshToken)