package app.controller

import com.twitter.finatra.request.QueryParam
import com.twitter.finatra.validation.Min

case class UsersGetRequest(
    @Min(1) @QueryParam page: Int = 1,
    @QueryParam limit: Int = 20)
