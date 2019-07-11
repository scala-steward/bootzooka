package com.softwaremill.bootzooka.passwordreset

import com.softwaremill.bootzooka.BaseModule
import com.softwaremill.bootzooka.email.{EmailScheduler, EmailTemplates}
import com.softwaremill.bootzooka.infrastructure.Http
import com.softwaremill.bootzooka.security.Auth
import doobie.util.transactor.Transactor
import monix.eval.Task

trait PasswordResetModule extends BaseModule {
  lazy val passwordResetService =
    new PasswordResetService(emailScheduler, emailTemplates, passwordResetCodeAuth, idGenerator, config.passwordReset, clock, xa)
  lazy val passwordResetApi = new PasswordResetApi(http, passwordResetService)

  def http: Http
  def passwordResetCodeAuth: Auth[PasswordResetCode]
  def emailScheduler: EmailScheduler
  def emailTemplates: EmailTemplates
  def xa: Transactor[Task]
}
