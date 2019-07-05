package app.controller

import app.repository.UserRepository
import com.twitter.finagle.http.Request
import com.twitter.finatra.http._
import javax.inject.{Inject, Singleton}

/**
  * HTTP Controller
  * https://twitter.github.io/finatra/user-guide/http/controllers.html
  */
@Singleton
class UserController @Inject()(repository: UserRepository) extends Controller {

  get("/") { request: Request =>
    response.ok.file("index.html")
  }

  get("/users/:id") { request: Request =>
    val id = request.params("id").toLong
    repository.find(id) map {
      case Some(user) => response.ok(user)
      case None => response.notFound(new Exception("Not Found"))
    } handle {
      case e: Exception => response.internalServerError(e.getMessage)
    }
  }

  get("/users") { request: Request =>
    val page = request.params("page").toInt
    val limit = request.params("limit").toInt
    repository.findAll(page, limit) map {
      users => response.ok(users)
    } handle {
      case e: Exception => response.internalServerError(e.getMessage)
    }
  }

}