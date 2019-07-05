package app.module

import app.repository.{UserRepository, UserRepositoryImpl}
import com.google.inject.{Module, Provides}
import com.twitter.inject.TwitterModule
import io.getquill.{FinagleMysqlContext, SnakeCase}
import javax.inject.Singleton

object UserModule extends TwitterModule {

  override val modules: Seq[Module] = Seq(QuillContextModule)

  @Singleton
  @Provides
  def providesUserRepository(ctx: FinagleMysqlContext[SnakeCase]): UserRepository = {
    new UserRepositoryImpl(ctx)
  }

}
