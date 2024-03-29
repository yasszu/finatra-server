package app.repository

import app.entity.User
import app.scheme.Users
import com.twitter.util.Future
import io.getquill._
import javax.inject.{Inject, Singleton}

@Singleton
class UserRepositoryImpl @Inject()(ctx: FinagleMysqlContext[SnakeCase]) extends UserRepository {

  import ctx._

  override def findAll(page: Int, limit: Int): Future[Seq[User]] = {
    val q = quote {
      query[Users]
        .filter(u => !u.del_flg)
        .map(u => User(u.id, u.name, u.email, u.comment))
        .drop(lift(page - 1))
        .take(lift(limit))
    }
    ctx.run(q)
  }

  override def find(userId: Long): Future[Option[User]] = {
    val q = quote {
      query[Users]
        .filter(u => u.id == lift(userId))
        .map(u => User(u.id, u.name, u.email, u.comment))
    }
    ctx.run(q).map(_.headOption)
  }

  override def create(name: String, email: String, comment: String): Future[Long] = {
    val q = quote {
      query[Users].insert(
        _.name -> lift(name),
        _.email -> lift(email),
        _.comment -> lift(comment))
    }
    ctx.run(q)
  }

  override def update(userId: Long, name: String, email: String, comment: String): Future[Long] = {
    val q = quote {
      query[Users]
        .filter(u => u.id == lift(userId))
        .update(
          _.name -> lift(name),
          _.email -> lift(email),
          _.comment -> lift(comment))
    }
    ctx.run(q)
  }

  override def delete(userId: Long): Future[Long] = {
    val q = quote {
      query[Users]
        .filter(u => u.id == lift(userId))
        .update(_.del_flg -> true)
    }
    ctx.run(q)
  }

}
