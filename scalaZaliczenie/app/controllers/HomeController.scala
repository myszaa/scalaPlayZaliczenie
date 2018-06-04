package controllers

import javax.inject._
import model.{User, UserDB, UserLogin,UserEmail, LuckyNumber}
import play.api.mvc._
import play.api.Logger
import play.api.i18n.{I18nSupport, MessagesApi}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */


@Singleton
class HomeController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport{

  def index = Action { implicit request: Request[AnyContent] =>
    var user: UserDB = new UserDB
    var userList: List[UserDB] = user.all()
    Ok(views.html.index(request,user,userList))
  }

  def login = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.login(UserLogin.form))
  }

  def details(name: String) = Action { implicit request: Request[AnyContent] =>
    var userdb : UserDB = new UserDB
    var userList = userdb.findByName(name)
    var user = userList(0)
    var luckynumber = new LuckyNumber
    var luckynumbers = luckynumber.findByUserId(user.id)
    Ok(views.html.details(UserEmail.form,user, luckynumbers))
 }

  def changeEmail(username : String) = Action { implicit request: Request[AnyContent] =>
    UserEmail.form.bindFromRequest.fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        Redirect("/")
      },
      formData => {
        var email = formData.email
        var userdb : UserDB = new UserDB
        var userList = userdb.findByName(username)
        var user = userList(0)
        user.email = email
        user.save()
        Redirect("/")
      }
    )
  }

  def logout = Action {
    Redirect("/").withSession("mysession"-> "")
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
        var email = formData.email
        val user = new UserDB
        var users:List[UserDB] = user.findByName(name)
        if(users.length > 0 )
        {
          Logger.info("User exists: "  + name)
          Redirect("/")
        } else {
          user.userName = name
          user.password = password
          user.email = email
          val r = scala.util.Random
          var luckynumber = new LuckyNumber
          luckynumber.number = r.nextInt(100)
          luckynumber.user = user
          luckynumber.save()
          user.lucky += luckynumber
          luckynumber = new LuckyNumber
          luckynumber.number = r.nextInt(100);
          luckynumber.user = user
          luckynumber.save()
          user.lucky += luckynumber
          user.save()
          Redirect("/").withSession("mysession"-> name)
        }

      }
    )
  }

  def doLogin = Action { implicit request: Request[AnyContent] =>
    UserLogin.form.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.login(formWithErrors))
      },
      formData => {
        var name = formData.username
        var password = formData.password
        val user = new UserDB
        var users:List[UserDB] = user.findByName(name)
        if(users.length > 0 )
        {
          if(users(0).password.toString == password.toString) {
            Redirect("/").withSession("mysession" -> name)
          }
          else {
            Logger.info("Bad login or Password")
            Redirect("/")
          }
        } else {
          Logger.info("Bad Login or password")
          Redirect("/")
        }
      }
    )
  }
}
