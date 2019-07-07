package app.controller

import com.twitter.finatra.request.{FormParam, RouteParam}

case class UserPutRequest(
    @RouteParam id: Long,
    @FormParam name: String,
    @FormParam email: String,
    @FormParam comment: String)