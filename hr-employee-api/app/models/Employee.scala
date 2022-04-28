package models

import org.joda.time.DateTime
import play.api.libs.json.{Format, Json}
import reactivemongo.play.json._
import reactivemongo.bson.BSONObjectID
import reactivemongo.bson._
import play.api.libs.json.JodaWrites._
import play.api.libs.json.JodaReads._

/** the case class contains all the attribute information of the employee
 */

case class Employee(
                  _id:Option[BSONObjectID],
                  _creationDate: Option[DateTime],
                  _updateDate: Option[DateTime],
                  jobtitle:String,
                  description:String
                )

/** companion object that contains the implicit JSON/BSON serializers
 * for JSON serializers we're using automated mapping
 * for external types we should provide their serializers as IMPLICIT just like the case for DateTime
 * for BSON we're implimenting our custom serializers
 */

object Employee{
  implicit val fmt : Format[Employee] = Json.format[Employee]
  implicit object EmployeeBSONReader extends BSONDocumentReader[Employee] {
    def read(doc: BSONDocument): Employee = {
      Employee(
        doc.getAs[BSONObjectID]("_id"),
        doc.getAs[BSONDateTime]("_creationDate").map(dt => new DateTime(dt.value)),
        doc.getAs[BSONDateTime]("_updateDate").map(dt => new DateTime(dt.value)),
        doc.getAs[String]("jobtitle").get,
        doc.getAs[String]("description").get)
    }
  }

  implicit object EmployeeBSONWriter extends BSONDocumentWriter[Employee] {
    def write(employee: Employee): BSONDocument = {
      BSONDocument(
        "_id" -> employee._id,
        "_creationDate" -> employee._creationDate.map(date => BSONDateTime(date.getMillis)),
        "_updateDate" -> employee._updateDate.map(date => BSONDateTime(date.getMillis)),
        "jobtitle" -> employee.jobtitle,
        "description" -> employee.description

      )
    }
  }
}