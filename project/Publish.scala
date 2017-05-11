import sbt._
import sbt.Keys._
import bintray.{ BintrayKeys, BintrayPlugin }

object Publish extends AutoPlugin {

  override def trigger = allRequirements
  override def requires = BintrayPlugin

  override def buildSettings = Seq(
    BintrayKeys.bintrayReleaseOnPublish := false
  )

  override def projectSettings = Seq(
    BintrayKeys.bintrayRepository := "maven",
    BintrayKeys.bintrayPackage := normalizedName.value,
    licenses += "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"),
    pomIncludeRepository := { _ => false }
  )

}

object NoPublish extends AutoPlugin {

  override def requires = Publish

  override def projectSettings = Seq(
    publish := (),
    publishLocal := (),
    publishM2 := ()
  )

}
