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

  get("/") { req: Request =>
    response.ok.file("index.html")
  }

  get("/users/:id") { req: Request =>
    val id = req.params("id").toLong
    repository.find(id) map {
      case Some(user) => response.ok(user)
      case None => response.notFound(new Exception("Not Found"))
    }
  }

  get("/users") { req: UsersQueryRequest =>
    repository.findAll(req.page, req.limit) map { users =>
      response.ok(users)
    }
  }

  post("/users") { req: UserFormRequest =>
    repository.create(req.name, req.email, req.comment) map { id =>
      response.ok("ok")
    }
  }

}