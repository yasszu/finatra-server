package app.controller

import com.twitter.finatra.request.FormParam

case class UserFormRequest(
    @FormParam name: String,
    @FormParam email: String,
    @FormParam comment: String)