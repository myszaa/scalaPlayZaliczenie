package controllers

import javax.inject._
import model.{User, UserDB}
import play.api.db.Database
import play.api.mvc._
import play.api.Logger

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */


@Singleton
class HomeController @Inject()(db: Database, cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport{

  def index = Action {
    Ok(views.html.index())
  }

  def login = Action {
    Ok(views.html.login())
  }

  def register = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.register(User.form))
  }

  def doRegister = Action { implicit request: Request[AnyContent] =>
    User.form.bindFromRequest.fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        BadRequest(views.html.register(formWithErrors))
      },
      formData => {
        var name = formData.username
        var password = formData.password
        val user = new UserDB
        user.userName = name
        user.password = password
        user.save()
        Logger.info("DUPA " + formData.username.toString)
        Redirect("/")
      }
    )
  }
}
