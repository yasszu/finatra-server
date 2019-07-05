package app

import app.controller.UserController
import app.module.UserModule
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter

object Main extends UserServer

class UserServer extends HttpServer {

  override val modules  = Seq(UserModule)

  override protected def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[CommonFilters]
      .add[UserController]
  }

}
